package parallel;



import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(

		features = { "@failedScenario/failedScenarioReRun.txt" },
		glue =		{ "parallel" },
		//glue = "stepDefinations", 
		//tags = "@smoke", 
		monochrome = true, 
		publish = true, 
		dryRun = false, 
		plugin = {
				"pretty", "html:target/HtmlReports/cucumber.html", 
				"json:target/JsonReports/cucumber.json", 
				"junit:target/XmlReports/cucumber.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:target/ThreadReports/",
				"rerun:failedScenario/failedScenarioReRun.txt"
				}

)

public class TestngRunnerForFailed extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
	
}
