package parallel;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = { "src/test/resources/parallel" },
		glue =		{ "parallel" },
		//glue = "stepDefinations", 
		tags = "@Sanity and not @Skip",
		monochrome = true, 
		//publish = true, 
		dryRun = false, 
		plugin = {
				"pretty", 
				
		//		"pretty:target/MyReports/Cucumber.txt",
		//		"usage:target/MyReports/Cucumber-usage.json",
		//		"html:target/MyReports/Cucumber.html", 
				"json:target/MyReports/Cucumber.json", 
		//		"junit:target/MyReports/Cucumber.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",		       
		//		"timeline:target/MyReports/ThreadReports/",
		//		"rerun:failedScenario/failedScenarioReRun.txt",
				
				}

)

public class TestngRunner extends AbstractTestNGCucumberTests{

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	


}
