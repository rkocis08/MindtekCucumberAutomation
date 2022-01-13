@regression @smoke @UI @ONT-115
Feature: Validating WebOrders application login functionality
  @OMT-3117
  Scenario: Validating login functionality with valid credentials
    Given user navigates to the weborders application
    When user provides username "Tester" and password "test" clicks on login button
    Then user validates application is logged in
@OMT-3117
  Scenario: Validating login functionality with invalid credentials
    Given user navigates to the weborders application
    When user provides username "Invalid" and password "invalid" clicks on login button
    Then user validates error message "Invalid Login or Password."


