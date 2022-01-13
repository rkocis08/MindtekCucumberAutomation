@regression @smoke @MB3-101 @UI
Feature: Validating Calculate Functionality i TestSheep


  Scenario Outline: Validating calculate functionality with valid inputs
    Given  user navigates toTestSheepApplication
    When   user enters "<Number1>" as first number
    And    user enters "<Number2>" as second number
    And    user selects "<Operation>" operator
    And    user clicks on Calculate Button
    Then   user validates output is <Answer>
    Examples:
      | Number1 | Number2 | Operation   | Answer |
      | 6       | 2       | Add         | 8      |
      | 10      | 5       | Subtract    | 5      |
      | 2       | 3       | Multiply    | 6      |
      | 4       | 1       | Divide      | 4      |
      | 1       | 2       | Concatenate | 12     |

  Scenario Outline: Validating calculate functionality with invalid inputs
    Given  user navigates toTestSheepApplication
    When   user enters "<Number1>" as first number
    And    user enters "<Number2>" as second number
    And    user selects "<Operation>" operator
    And    user clicks on Calculate Button
    Then  user validates "<Message>" error message
    Examples:
      | Number1 | Number2 | Operation | Message               |
      | abc     | abc     | Add       | Number 1 is not a number |
      | 5       | abc     | Add       | Number 2 is not a number |
      | 5       | 0       | Divide    | Divide by zero error!     |