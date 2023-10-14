@regression
Feature: Login

  Background:
    Given the user visits "https://dbank-qa.wedevx.co/bank/login"


  Scenario: Verify user can login with valid username and valid password

    When user entered valid username "Johnsmith@gmail.com" and valid password "Wedevxschool1!"
    And user clicks submit button
    Then user should successfully log in
    And user should be directed to "https://dbank-qa.wedevx.co/bank/home"

  Scenario Outline: Verify user cannot login with invalid username and invalid password

    When user entered invalid username "<username>" and invalid password "<password>"
    And user clicks submit button
    Then user should not be able to log in
    And user should see an error message "Error Invalid credentials or access not granted due to user account status or an existing user session."

    Examples:
      | username       | password          |
      | notRealName    | notRealPassword$  |
      | Name12345      | passwordIncorrect |
      | password12345  | passwordEmpty1    |
      |test@gmail.com  | test$             |
      |NotBlankUsername|                   |
      |                |NotBlankPassword   |



  Scenario: Verify user can click the remember me button

    When remember me button is present on the page
    Then  user clicks remember me button




  Scenario: User doesn't have an account

    When Sign up here Link is present on the page
    And  user clicks on sign up here Link
    Then user should be taken to the registration page
    And  user should be able to register




