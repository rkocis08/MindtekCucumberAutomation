@regression @UI @ONT-114
Feature: Validating Etsy application search and filter functionalities

  Background:Repeated first steps in scenario
    Given user navigates to Etsy application
    When user searches for "carpet"

# @Before method will run
  Scenario: Validating price range filter functionality for search items
    And user applies filter over 1000
    Then user validates that items prices are over 1000
# @After method will run

# @Before method will run
  Scenario: Validating search results
    Then user validates search result items contain keyword "carpet"
    # @After method will run

