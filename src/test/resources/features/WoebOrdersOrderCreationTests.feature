@regressions @UI @MB2P-120
Feature: Validating calculate and order creation functionalities

  Scenario Outline:Validating Calculate Functionality
    Given user navigates to the weborders application
    When user provides username "Tester" and password "test" clicks on login button
    And user click on order module
    And user selects "<product>" with <quantity> quantity
    And user validates total is calculated correctly for quantity <quantity>

    Examples:

      | product     | quantity |
      | MyMoney     | 1        |
      | FamilyAlbum | 100      |
      | ScreenSaver | 55       |
      | MyMoney     | 20       |

  @MB2-413
  Scenario Outline: Validating create order functionality
    Given user navigates to the weborders application
    When user provides username "Tester" and password "test" clicks on login button
    And user counts number of orders in table
    And user click on order module
    And user creates order with data
      | order   | quantity   | name   | street   | city   | state   | zip   | cc   | expire date   |
      | <order> | <quantity> | <name> | <street> | <city> | <state> | <zip> | <cc> | <expire date> |
    Then user validates success message "New order has been successfully added."
    And user validates that the added to list of Orders
    Examples:
      | order        | quantity | name     | street      | city        | state    | zip   | cc              | expire date |
      | MyMoney      | 1        | John Doe | 123 My Road | Chicago     | Illinois | 12345 | 123456789123456 | 12/21       |
      | FamilyAlbum | 5        | Patel    | 123 My Road | New York    | NY       | 00000 | 333356789123456 | 12/21       |
      | ScreenSaver | 50       | Kim      | 123 My Road | Los Angeles | CA       | 11111 | 444456789123456 | 12/21       |