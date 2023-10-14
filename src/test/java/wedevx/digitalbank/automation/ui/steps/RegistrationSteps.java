package wedevx.digitalbank.automation.ui.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import wedevx.digitalbank.automation.ui.utils.ConfigReader;
import wedevx.digitalbank.automation.ui.utils.DBUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class RegistrationSteps {


     RegistrationPage registrationPage = new RegistrationPage(getDriver());
    List<Map<String, Object>> nextValList = new ArrayList<>();


    @Given("User navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpageurl"));
        Assertions.assertEquals("Digital Bank", getDriver().getTitle(), "Registration page Title mismatch");

    }

    @When("User creates account with following fields")
    public void user_creates_account_with_following_fields(List<Map<String, String>> registrationTestDataListMap) {
        registrationPage.fillOutTheRegistrationPage(registrationTestDataListMap);

    }

    @Then("User should be displayed with the message {string}")
    public void user_should_be_displayed_with_the_message(String expectedSuccessMessage) {
        Assertions.assertEquals(expectedSuccessMessage, registrationPage.getMessage(),"SuccessMessage Mismatch");

    }

    @When("user creates account with following fields")
    public void user_creates_account_with_following_fields(io.cucumber.datatable.DataTable dataTable) {

    }


    @Then("the User should see the {string} required field error message {string}")
    public void theUserShouldSeeTheRequiredFieldErrorMessage(String fieldName, String expectedErrorMessage ) {
       String actualErrorMessage = registrationPage.getRequiredFieldErrorMessage(fieldName);
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "the error message of required " + fieldName + " field mismatch");
       // Assertions.assertEquals(expectedErrorMessage, actualErrorMessage,
                //String.format("The error message for required '%s' field mismatch. Expected: '%s', but got: '%s'",fieldName, expectedErrorMessage, actualErrorMessage));
    }
    @Then("the following user info should be saved in the db")
    public void theFollowingUserInfoShouldBeSavedInTheDb(List<Map<String, String>> expectedUserProfileInfoInDBList)  {
        Map<String, String> expectedUserInfoMap =  expectedUserProfileInfoInDBList.get(0);
        String queryUserTable = String.format("select * from users where username='%s'", expectedUserInfoMap.get("email"));
        String queryUserProfile = String.format("select * from user_profile where email_address='%s'", expectedUserInfoMap.get("email"));


        List<Map<String, Object>> actualUserInfoList = DBUtils.runSQLSelectQuery(queryUserTable);
        List<Map<String, Object>> actualUserProfileInfoList = DBUtils.runSQLSelectQuery(queryUserProfile);


        Assertions.assertEquals(1, actualUserInfoList.size(), "registration generated unexpected numbers of users");
        Assertions.assertEquals(1, actualUserProfileInfoList.size(), "registration generated unexpected numbers of  users profiles");

        Map<String, Object> actualUserInfoMap =  actualUserInfoList.get(0);
        Map<String, Object> actualUserProfileInfoMap =  actualUserProfileInfoList.get(0);

        Assertions.assertEquals(expectedUserInfoMap.get("title"), actualUserProfileInfoMap.get("title"),"registration generated wrong title");
        Assertions.assertEquals(expectedUserInfoMap.get("firstName"), actualUserProfileInfoMap.get("first_name"),"registration generated wrong firstName");
        Assertions.assertEquals(expectedUserInfoMap.get("lastName"), actualUserProfileInfoMap.get("last_name"),"registration generated wrong lastName");
        Assertions.assertEquals(expectedUserInfoMap.get("gender"), actualUserProfileInfoMap.get("gender"),"registration generated wrong gender");
        //assertEquals(expectedUserInfoMap.get("dob"), actualUserProfileInfoMap.get("dob"),"registration generated wrong dob");
        Assertions.assertEquals(expectedUserInfoMap.get("ssn"), actualUserProfileInfoMap.get("ssn"),"registration generated wrong ssn");
        Assertions.assertEquals(expectedUserInfoMap.get("email"), actualUserProfileInfoMap.get("email_address"),"registration generated wrong email");
        Assertions.assertEquals(expectedUserInfoMap.get("address"), actualUserProfileInfoMap.get("address"),"registration generated wrong address");
        Assertions.assertEquals(expectedUserInfoMap.get("locality"), actualUserProfileInfoMap.get("locality"),"registration generated wrong locality");
        Assertions.assertEquals(expectedUserInfoMap.get("region"), actualUserProfileInfoMap.get("region"),"registration generated wrong region");
        Assertions.assertEquals(expectedUserInfoMap.get("postalCode"), actualUserProfileInfoMap.get("postal_code"),"registration generated wrong postalCode");
        Assertions.assertEquals(expectedUserInfoMap.get("country"), actualUserProfileInfoMap.get("country"),"registration generated wrong country");
        Assertions.assertEquals(expectedUserInfoMap.get("homePhone"), actualUserProfileInfoMap.get("home_phone"),"registration generated wrong home phone");
        Assertions.assertEquals(expectedUserInfoMap.get("mobilePhone"), actualUserProfileInfoMap.get("mobile_phone"),"registration generated wrong mobile phone");
        Assertions.assertEquals(expectedUserInfoMap.get("workPhone"), actualUserProfileInfoMap.get("work_phone"),"registration generated wrong work phone");



        //validate users table
        Assertions.assertEquals(expectedUserInfoMap.get("accountNonExpired"), String.valueOf(actualUserInfoMap.get("account_non_expired")), "accountNonExpired mismatch upon registration");
        Assertions.assertEquals(expectedUserInfoMap.get("accountNonLocked"), String.valueOf(actualUserInfoMap.get("account_non_locked")), "accountNonLocked mismatch upon registration");
        Assertions.assertEquals(expectedUserInfoMap.get("credentialsNonExpired"), String.valueOf(actualUserInfoMap.get("credentials_non_expired")), "credentialsNonExpired mismatch upon registration");
        Assertions.assertEquals(expectedUserInfoMap.get("enabled"), String.valueOf(actualUserInfoMap.get("enabled")), "account enabled mismatch upon registration");
        Assertions.assertEquals(expectedUserInfoMap.get("email"), actualUserInfoMap.get("username"), "account username mismatch upon registration");

        Assertions.assertEquals(nextValList.get(0).get("next_val"), actualUserInfoMap.get("id"), "ID mismatch");
        long expectedUserProfileId = Integer.parseInt(String.valueOf(nextValList.get(0).get("next_val")));
        Assertions.assertEquals(++expectedUserProfileId, actualUserProfileInfoMap.get("id"), "ID mismatch");
    }

    @Given("The user with {string} is not in DB")
    public void theUserWithIsNotInDB(String email) {
        String queryForUserProfile = String.format("DELETE from user_profile where email_address='%s'", email);
        String queryForUsers = String.format("DELETE from users  where username='%s'", email);

        String queryToGetNextValInHibernateSeqTable = String.format("select * from hibernate_sequence");
        nextValList =  DBUtils.runSQLSelectQuery(queryToGetNextValInHibernateSeqTable);

        DBUtils.runSQLUpdateQuery(queryForUserProfile);
        DBUtils.runSQLUpdateQuery(queryForUsers);
    }


//
//    @Given("the user is on the login page")
//    public void the_user_is_on_the_login_page() {
//        driver.get("https://dbank-qa.wedevx.co/bank/login");
//
//
//    }
//
//    @Given("the user clicks the {string} link")
//    public void the_user_clicks_the_link(String signUpHereLink) {
//        WebElement signUpHere = driver.findElement(By.xpath("//a[contains(text(), 'Sign Up Here')]"));
//        signUpHere.click();
//    }
//
//    @When("the user enters the following registration details")
//    public void the_user_enters_the_following_registration_details(List<RegistrationPageInfo> registrationPageList) {
//        RegistrationPageInfo dataForRegistrationPage = registrationPageList.get(0);
//
//        // Handling title dropdown separately
//        WebElement titleField = driver.findElement(By.id("title"));
//        new Select(titleField).selectByVisibleText(dataForRegistrationPage.getTitle());
//
//        // mapping of field locators to their respective data retrieval methods
//        Map<By, Supplier<String>> fieldDataMap = new HashMap<>();
//        fieldDataMap.put(By.id("firstName"), dataForRegistrationPage::getFirstName);
//        fieldDataMap.put(By.id("lastName"), dataForRegistrationPage::getLastName);
//        fieldDataMap.put(By.id("dob"), dataForRegistrationPage::getDateOfBirth);
//        fieldDataMap.put(By.id("ssn"), dataForRegistrationPage::getSsn);
//        fieldDataMap.put(By.id("emailAddress"), dataForRegistrationPage::getEmailAddress);
//        fieldDataMap.put(By.id("password"), dataForRegistrationPage::getPassword);
//        fieldDataMap.put(By.id("confirmPassword"), dataForRegistrationPage::getConfirmPassword);
//
//        for (Map.Entry<By, Supplier<String>> entry : fieldDataMap.entrySet()) {
//            driver.findElement(entry.getKey()).sendKeys(entry.getValue().get());
//        }
//
//        // Handling gender selection
//        String gender = dataForRegistrationPage.getGender();
//        if ("M".equalsIgnoreCase(gender)) {
//            driver.findElement(By.xpath("//input[@type='radio' and @value='M']")).click();
//        } else if ("F".equalsIgnoreCase(gender)) {
//            driver.findElement(By.xpath("//input[@value='F']")).click();
//        } else {
//            System.out.println("Invalid gender input");
//        }
//
////      WebElement titleField = driver.findElement(By.id("title"));
////     WebElement firstNameField = driver.findElement(By.id("firstName"));
////      WebElement lastNameField = driver.findElement(By.id("lastName"));
////      WebElement maleRadioButton = driver.findElement(By.xpath("//input[@type='radio' and @value='M'] "));
////       WebElement femaleRadioButton = driver.findElement(By.xpath("//input[@value='F']"));
////      WebElement dobField = driver.findElement(By.id("dob"));
////       WebElement ssnField = driver.findElement(By.id("ssn"));
////       WebElement emailAddressField = driver.findElement(By.id("emailAddress"));
////       WebElement passwordField = driver.findElement(By.id("password"));
////        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
////              //WebElement existingAccountSignIn = driver.findElement(By.xpath("//a[@href='/bank/login/']"));
////       WebElement nextButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
////
////      Select selectTitle = new Select(titleField);
////      selectTitle.selectByVisibleText(dataForRegistrationPage.getTitle());
////       firstNameField.sendKeys(dataForRegistrationPage.getFirstName());
////        lastNameField.sendKeys(dataForRegistrationPage.getLastName());
////     String gender = dataForRegistrationPage.getGender();
////   if ("M".equalsIgnoreCase(gender)) {
////          maleRadioButton.click();
////      } else if ("F".equalsIgnoreCase(gender)) {
////          femaleRadioButton.click();
////       } else {
////       System.out.println("Invalid gender input");
////  }
////      dobField.sendKeys(dataForRegistrationPage.getDateOfBirth());
////      ssnField.sendKeys(dataForRegistrationPage.getSsn());
////      emailAddressField.sendKeys(dataForRegistrationPage.getEmailAddress());
////      passwordField.sendKeys(dataForRegistrationPage.getPassword());
////     confirmPasswordField.sendKeys(dataForRegistrationPage.getConfirmPassword());
////       nextButton.click();
//
//
//    }
//
//    @When("the user clicks {string}")
//    public void the_user_clicks(String string) {
//        WebElement nextButton = driver.findElement(By.xpath(("//button[@type = 'submit']")));
//        nextButton.click();
//    }
//
//    @Then("the user enters the following address details")
//    public void the_user_enters_the_following_address_details(List<RegistrationPageInfo> secondPageRegistrationList) {
//        RegistrationPageInfo dataForRegistrationPage = secondPageRegistrationList.get(0);
//
//        Map<String, String> fieldDataMap = new HashMap<>();
//        fieldDataMap.put("address", dataForRegistrationPage.getAddress());
//        fieldDataMap.put("locality", dataForRegistrationPage.getLocality());
//        fieldDataMap.put("region", dataForRegistrationPage.getRegion());
//        fieldDataMap.put("postalCode", dataForRegistrationPage.getPostalCode());
//        fieldDataMap.put("country", dataForRegistrationPage.getCountry());
//        fieldDataMap.put("homePhone", dataForRegistrationPage.getHomePhone());
//        fieldDataMap.put("mobilePhone", dataForRegistrationPage.getMobilePhone());
//        fieldDataMap.put("workPhone", dataForRegistrationPage.getWorkPhone());
//
//        for (Map.Entry<String, String> entry : fieldDataMap.entrySet()) {
//            driver.findElement(By.id(entry.getKey())).sendKeys(entry.getValue());
//        }
//
//        driver.findElement(By.xpath("//button[text() = 'Register']")).click();
//
//    }
//
//
//    @Then("the user agree to the term and policies")
//    public void the_user_agree_to_the_term_and_policies() {
//        WebElement agreeToTermsCheckBox = driver.findElement(By.xpath("//input[@name = 'agree-terms']"));
//        agreeToTermsCheckBox.click();
//    }
//
//    @Then("the user clicks the {string} button")
//    public void the_user_clicks_the_button(String string) {
//        WebElement registerButton = driver.findElement(By.xpath("//button[text() = 'Register']"));
//        registerButton.click();
//    }
//
//    @Then("a {string} alert should be displayed in green")
//    public void a_alert_should_be_displayed_in_green(String messageAlert) {
//        WebElement successfulAlertRegistration = driver.findElement(By.xpath("//span[text()='Registration Successful. Please Login.']"));
//        Assert.assertEquals("Registration Successful. Please Login.", successfulAlertRegistration.getText());


        //driver.quit();

    }

