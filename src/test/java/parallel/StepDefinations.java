package parallel;

import java.io.IOException;

import org.testng.Assert;

import base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CalendarPage;


public class StepDefinations extends Base {	
	
	CalendarPage calendar = new CalendarPage(getDriver());
	
	
	@Given("I navigate to {string} page and page title should be {string}")
	public void i_navigate_to_page_and_page_title_should_be(String expectedUrl, String expectedPageTitle) {
	    
		getDriverNavigate(expectedUrl); 
		getPageTitle(expectedPageTitle);
	}

	@When("I find all the links on the page and get response code of each link")
	public void i_find_all_the_links_on_the_page_and_get_response_code_of_each_link() {
		
		verifyBrokenLinks();
	   
	}

	@Then("I print the broken link in the console output")
	public void i_print_the_broken_link_in_the_console_output() {
	    
		
	}
	
	@When("I click on calendar and select {string}, {string} & {string}")
	public void i_click_on_calendar_and_select(String expectedDay, String expectedMonth, String expectedYear) {
		calendar.selectDateInCalendar(expectedDay, expectedMonth, expectedYear);
	}

	@Then("Selected date is visible in text box")
	public void selected_date_is_visible_in_text_box() {
	    
	}
		
	
	}

	
	
	
	
	
	
	
