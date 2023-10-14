@regression
@Registration
Feature: Digital Bank Registration Page

  Background:
    Given The user with "johnsmith@yahoo.com" is not in DB
    Given User navigates to Digital Bank signup page

    #Given url "https://dbank-qa.wedevx.co/bank/login"
    #When  user enters url
    #And   clicks on Sign Up Here
@Test
  Scenario: Positive Case. As a user, I want to successfully create Digital Bank account
    When  User creates account with following fields


    |title |firstName |lastName |gender |dob         |ssn        |email               |password     |confirmPassword |address           |locality  |region |postalCode |country |homePhone      |mobilePhone    |workPhone      |termsCheckMark  |accountNonExpired |accountNonLocked |credentialsNonExpired|enabled |
    |Mr.   |John      |Smith    |M      |12/18/1987  |123-23-1234|johnsmith@yahoo.com |School034!   |School034!      |222 Las Vegas blv |Las Vegas |NV     |89138      |US      |(702)-111-2222 |(702)-222-1111 |(702)-222-3333 |true            |true              |true             |true                 |true    |


    #And the user agree to the term and policies
    #And the user clicks the "Register" button
    Then User should be displayed with the message "Success Registration Successful. Please Login."
    Then the following user info should be saved in the db
      |title |firstName |lastName |gender |dob         |ssn        |email               |password      |confirmPassword |address           |locality  |region |postalCode |country |homePhone       |mobilePhone    |workPhone      |termsCheckMark  |accountNonExpired |accountNonLocked |credentialsNonExpired|enabled   |
      |Mr.   |John      |Smith    |M      |12/18/1987  |123-23-1234|johnsmith@yahoo.com |School034!    |School034!      |222 Las Vegas blv |Las Vegas |NV     |89138      |US      |(702)-111-2222  |(702)-222-1111 |(702)-222-3333 |true            |true              |true             |true                 |true      |


  @NegativeRegistrationCases
    Scenario Outline: Negative test Cases. As a Digital Bank Admin i want to make sure users can not register without providing all valid data

     When user creates account with following fields

        |title   |firstName   |lastName   |gender     |dob      |ssn      |email    |password    |confirmPassword     |address      |locality   |region   |postalCode   |country   |homePhone    |mobilePhone   |workPhone    |termsCheckMark  |
        |<title> |<firstName> |<lastName> |<gender>   |<dob>    |<ssn>    |<email>  |<password>  |<confirmPassword>   |<address>    |<locality> |<region> |<postalCode> |<country> |<homePhone>  |<mobilePhone> |<workPhone>  |<termsCheckMark>|

   Then the User should see the "<fieldWithErrorMessage>" required field error message "<errorMessage>"


      Examples:
        |title |firstName |lastName |gender |dob         |ssn     |email  |password    |confirmPassword |address              |locality  |region     |postalCode |country      |homePhone      |mobilePhone    |workPhone      |termsCheckMark |fieldWithErrorMessage |errorMessage                                 |
        |      |          |         |       |            |        |       |            |                |                     |          |           |           |             |               |               |               |               |title                 |Please select an item in the list.           |
        |Mr.   |          |         |       |            |        |       |            |                |                     |          |           |           |             |               |               |               |               |firstName             |Please fill out this field.                  |
        |Mr.   |John      |         |       |            |        |       |            |                |                     |          |           |           |             |               |               |               |               |lastName              |Please fill out this field.                  |
        |Mr.   |John      |Smith    |       |            |        |       |            |                |                     |          |           |           |             |               |               |               |               |gender                |Please select one of these options.          |
        |Mr.   |John      |Smith    | M     |            |        |       |            |                |                     |          |           |           |             |               |               |               |               |dob                   |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |        |       |            |                |                     |          |           |           |             |               |               |               |               |ssn                   |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |       |            |                |                     |          |           |           |             |               |               |               |               |email                 |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |            |                |                     |          |           |           |             |               |               |               |               |password              |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School034!  |                |                     |          |           |           |             |               |               |               |               |confirmPassword       |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School0345! |School0345!     |                     |          |           |           |             |               |               |               |               |address               |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School034!  |School034!      |222 Las Vegas blv    |          |           |           |             |               |               |               |               |locality              |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School034!  |School034!      |222 Las Vegas blv    |Las Vegas |           |           |             |               |               |               |               |region                |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School034!  |School034!      |222 Las Vegas blv    |Las Vegas |NV         |           |             |               |               |               |               |postalCode            |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School034!  |School034!      |222 Las Vegas blv    |Las Vegas |NV         |89138      |             |               |               |               |               |country               |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School034!  |School034!      |222 Las Vegas blv    |Las Vegas |NV         |89138      |US           |               |               |               |               |homePhone             |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School034!  |School034!      |222 Las Vegas blv    |Las Vegas |NV         |89138      |US           |(702)-111-2222 |               |               |               |mobilePhone           |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School034!  |School034!      |222 Las Vegas blv    |Las Vegas |NV         |89138      |US           |(702)-111-2222 |(702)-111-2222 |               |               |workPhone             |Please fill out this field.                  |
        |Mr.   |John      |Smith    | M     |12/17/1987  |random  |random |School034!  |School034!      |222 Las Vegas blv    |Las Vegas |NV         |89138      |US           |(702)-111-2222 |(702)-111-2222 |(702)-222-3333 | true          |termsCheckMark        |Please check this box if you want to proceed.|


#




















