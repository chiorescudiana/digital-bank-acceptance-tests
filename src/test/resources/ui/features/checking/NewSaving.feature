@regression
Feature: Creating a new saving account

Scenario: Create a individual saving account
  Given the user logged in as "johnsmith@gmail.com" "Wedevxschool1!"
  When the user creates a new saving account with the following data
    | savingsAccountType  | accountOwnership | accountName           | initialDepositAmount|
    | Savings             | Individual       | John Smith            | 100000.00           |
  Then the user should see the green "Confirmation Successfully created new Savings account named John Smith" message
  And the user should see newly added account card
    |accountName  |accountType     |ownership   |accountNumber |interestRate  |balance    |
    |John Smith   |Savings         |Individual  |486132013     |1.85%         |10000.00  |
  And the user should see the following transaction
    |date            |category|description              |amount    |balance   |
    |2023-09-21 18:45|Income  |845322720 (DPT) - Deposit|10000.00  |10000.00  |
