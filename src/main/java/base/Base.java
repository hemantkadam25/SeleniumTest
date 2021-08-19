package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public Properties properties;

	public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();

	public static Logger log = Logger.getLogger(Base.class.getName());

	public String getConfigValue(String key) {

		try {

			String projectPath = System.getProperty("user.dir");
			properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(
					projectPath + "\\src\\main\\java\\config\\config.properties");
			properties.load(fileInputStream);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return (String) properties.get(key);

	}

	public WebDriver initialization() throws IOException {

		String browserName = getConfigValue("Browser");

		if (browserName.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			threadLocal.set(new ChromeDriver());
			log.info("Execution start on " + browserName + " browser");

		} else if (browserName.equalsIgnoreCase("Firefox")) {

			WebDriverManager.firefoxdriver().setup();
			threadLocal.set(new FirefoxDriver());
			log.info("Execution start on " + browserName + " browser");

		} else if (browserName.equalsIgnoreCase("Edge")) {

			WebDriverManager.edgedriver().setup();
			threadLocal.set(new EdgeDriver());
			log.info("Execution start on " + browserName + " browser");

		} else if (browserName.equalsIgnoreCase("IE")) {

			WebDriverManager.iedriver().setup();
			threadLocal.set(new InternetExplorerDriver());
			log.info("Execution start on " + browserName + " browser");

		} else if (browserName.equalsIgnoreCase("Chrome_Headless")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--headless");
			options.addArguments("disable-gpu");
			options.addArguments("window-size=1400,2100");
			threadLocal.set(new ChromeDriver(options));
			log.info("Execution start on " + browserName + " browser");

		} else if (browserName.equalsIgnoreCase("Edge_Headless")) {

			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--headless");
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--headless");
			options.addArguments("disable-gpu");
			options.addArguments("window-size=1400,2100");

			threadLocal.set(new EdgeDriver(options));
			log.info("Execution start on " + browserName + " browser");

		} else if (browserName.equalsIgnoreCase("Firefox_Headless")) {

			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("--headless");
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--headless");
			options.addArguments("disable-gpu");
			options.addArguments("window-size=1400,2100");
			threadLocal.set(new FirefoxDriver(options));
			log.info("Execution start on " + browserName + " browser");

		}

		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return threadLocal.get();
	}

	public void getDriverNavigate(String webURL) {
		getDriver().get(getConfigValue(webURL));
	}

	public void getDriverQuit() {

		log.info("Browser Quit");
		getDriver().quit();
	}

	public WebDriverWait getWebDriverWait() {

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		return wait;
	}

	public String getPageTitle(String expectedPageTitle) {

		getWebDriverWait().until(ExpectedConditions.titleContains(expectedPageTitle));
		return getDriver().getTitle();
	}

	public void dropdownSelect(WebElement webelement, String selectVisibleText) {

		Select select = new Select(webelement);
		select.selectByVisibleText(selectVisibleText);
	}

	public static void verifyBrokenLinks() {

		List<WebElement> links = getDriver().findElements(By.tagName("a"));
		System.out.println("Number of links are --->> " + links.size());

		List<String> urlList = new ArrayList<String>();

		for (WebElement e : links) {
			String url = e.getAttribute("href");

			urlList.add(url);
			// verifyBrokenLinks(url);
		}

		long startTime = System.currentTimeMillis();
		urlList.parallelStream().forEach(e -> verifyBrokenLinks(e));
		long endTime = System.currentTimeMillis();
		System.out.println("Total time taken: " + (endTime - startTime));

	}

	public static void verifyBrokenLinks(String linkUrl) {
		
		try {

			URL url = new URL(linkUrl);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setConnectTimeout(5000);
			httpUrlConnection.connect();

			if (httpUrlConnection.getResponseCode() >= 400) {

				System.out.println("Broken link --->> " + linkUrl + " ----->> " + httpUrlConnection.getResponseMessage());
			} else {

				//System.out.println("Working link --->> " + linkUrl + " ----->> " + httpUrlConnection.getResponseMessage());

			}

		} catch (Exception e) {

		}

	}
}
