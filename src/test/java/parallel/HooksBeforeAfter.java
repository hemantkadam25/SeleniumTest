package parallel;

import java.io.IOException;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.Scenario;
import base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class HooksBeforeAfter extends Base {	
	
	/*
	@Before(value="@SkipScenario", order=0)
	public void scenarioSkip(Scenario scenario) throws IOException {		
		
		System.out.println(scenario.getName());
		Assume.assumeTrue(false);
	}
	*/

	@Before(order=1)
	public void browserInitialization() throws IOException {
		
		initialization();		
	}
	
	/*

	@After(order=1)
	public void getScreenshot() {
		
			
	}
	
	*/
	
	@After(order=0)
	public void browserQuit(Scenario scenario) {		
		
		if (scenario.isFailed()) {

			String screenshotName = scenario.getName().replaceAll(" ", "_");
			//TakesScreenshot ts = (TakesScreenshot) driver;
			TakesScreenshot ts = (TakesScreenshot) getDriver();
			byte[] source = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(source, "image/png", screenshotName);
		}
		
		log.info("Scenario \""+scenario.getName()+ "\" has been -->>  "+scenario.getStatus());
		getDriverQuit();
	}

}
