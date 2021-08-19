 @Sanity
Feature: To select date in calendar. 

  Scenario Outline: To test expected date in selected in the calendar
    Given I navigate to "seleniumPractiseCalendar" page and page title should be "Selenium Practise: How to handle calendar in Selenium Webdriver"
    When I click on calendar and select "<Day>", "<Month>" & "<Year>"
    Then Selected date is visible in text box
    
    Examples:
    | Day | Month  | Year |
    | 31  | August | 2025 |