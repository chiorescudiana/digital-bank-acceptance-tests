Feature:  Making a Deposit

  Scenario: Making a new deposit
    Given the user logged in as "johnsmith@gmail.com" "Wedevxschool1!"
    When the user makes a new deposit
      | checkingAccountType | accountOwnership | accountName                | initialDepositAmount|
      | Standard Checking   | Individual       | John Smith Second Checking | 100000.00           |
    #And the user clicks on the checking button
    #And the user clicks on the new checking button
    #When the user selects "Standard Checking" account type
    #And the user selects "Individual" Account Ownership
    #And the user names the account "John Smith Second Checking"
    #And the user makes the initial deposit of $100000.00
   # And the user clicks on submit
    Then the user should see the green "Successfully created new Standard Checking account named John Smith Second Checking" message
    And the user should see newly added account card
      |accountName                 |accountType       |ownership   |accountNumber |interestRate |balance    |
      |John Smith Second Checking  |Standard Checking |Individual  |486131565     |0.0%         |100000.00  |
    And the user should see the following transaction
      |date            |category|description              |amount    |balance   |
      |2023-09-12 20:32|Income  |845322270 (DPT) - Deposit|100000.00 | 100000.00|