 @Sanity
Feature: To verify broken links on the page.  

  Scenario: To test broken links on the page
    Given I navigate to "Google" page and page title should be "Google"
    When I find all the links on the page and get response code of each link
    Then I print the broken link in the console output