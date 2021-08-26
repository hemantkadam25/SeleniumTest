package pages;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalendarPage {

	WebDriver driver;

	public CalendarPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='datepicker']")
	WebElement calendarTextBox;

	@FindBy(xpath = "//span[@class='ui-datepicker-year']")
	WebElement calendarYear;

	@FindBy(xpath = "//span[@class='ui-datepicker-month']")
	WebElement calendarMonth;

	@FindBy(xpath = "//a[@class='ui-state-default']")
	List<WebElement> calendarDay;

	@FindBy(xpath = "//span[@class='ui-icon ui-icon-circle-triangle-e']")
	WebElement nextButton;

	@FindBy(xpath = "//span[@class='ui-icon ui-icon-circle-triangle-w']")
	WebElement prevButton;

	public void selectDateInCalendar(String day, String month, String year) {	
		
		if (month.equalsIgnoreCase("February") && Integer.parseInt(day)>29) {
			
			System.out.println("Wrong date: "+month+ " : "+day);
			return;
		}
		
       if (Integer.parseInt(day)>31) {
			
			System.out.println("Wrong date: "+month+ " : "+day);
			return;
		}
		
		calendarTextBox.click();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int expectedYear = Integer.parseInt(year);	
		
		while (!(calendarYear.getText().equals(year) && calendarMonth.getText().equals(month)))
		{
			if (currentYear <= expectedYear) {
				nextButton.click();
			} else {
				prevButton.click();
			}
			
		}
		
		
		/*
				
		// To select year and month
		if (currentYear <= expectedYear) {

			 {

				nextButton.click();
			}
		} else {

			while (!(calendarYear.getText().equals(year) && calendarMonth.getText().equals(month))) {

				prevButton.click();
			}
		}
		
		
		
		*/
		
		// To select day
		List<WebElement> element = calendarDay;
		
		for(int i=0; i<element.size(); i++) {
			
			if(element.get(i).getText().equalsIgnoreCase(day)) {
				
				element.get(i).click();
			}
		}
		
		

	}

}
