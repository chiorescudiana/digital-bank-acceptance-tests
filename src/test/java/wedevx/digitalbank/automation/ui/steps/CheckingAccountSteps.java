package wedevx.digitalbank.automation.ui.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import wedevx.digitalbank.automation.ui.models.AccountCard;
import wedevx.digitalbank.automation.ui.models.BankTransaction;
import wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import wedevx.digitalbank.automation.ui.pages.CreateNewCheckingPage;
import wedevx.digitalbank.automation.ui.pages.LoginPage;
import wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import wedevx.digitalbank.automation.ui.utils.Driver;


import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountSteps {

    WebDriver driver = Driver.getDriver();
        private LoginPage loginPage = new LoginPage(driver);
        private CreateNewCheckingPage createCheckingPage = new CreateNewCheckingPage(driver);
        private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);

      //  @BeforeAll
       // public static void setup(){
           // WebDriverManager.firefoxdriver().setup();
        //}

//        @Before
//        public void the_user_is_on_the_home_page_of_dbank() {
//            driver.get("https://dbank-qa.wedevx.co/bank/login");
//            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//
//        }
        @Given("the user logged in as {string} {string}")
        public void the_user_logged_in_as(String username, String password) {
//      WebElement usernameTxt =  driver.findElement(By.id("username"));
//      WebElement passwordTxt = driver.findElement(By.id("password"));
//      WebElement submitButton = driver.findElement(By.id("submit"));
//      usernameTxt.sendKeys(username);
//      passwordTxt.sendKeys(password);
//      submitButton.click();
            loginPage.login(username, password);




        }
//    @Given("the user clicks on the checking button")
//    public void the_user_clicks_on_the_checking_button() {
//        WebElement checkingButton =  driver.findElement(By.id("checking-menu"));
//        checkingButton.click();
//
//    }
//   @Given("the user clicks on the new checking button")
//    public void the_user_clicks_on_the_new_checking_button() {
//        WebElement newCheckingButton =  driver.findElement(By.id("new-checking-menu-item"));
//        newCheckingButton.click();
//
//        String expectedUrl = "https://dbank-qa.wedevx.co/bank/account/checking-add";
//        assertEquals(expectedUrl, driver.getCurrentUrl(), "new Checking Button didnt take to the " + expectedUrl);
//
//
        // }
//    @When("the user selects {string} account type")
//    public void the_user_selects_account_type(String idOfRadioButton) {
//        WebElement accountTypeRadioButton =  driver.findElement(By.id(idOfRadioButton));
//        accountTypeRadioButton.click();
//    }
//    @When("the user selects {string} Account Ownership")
//    public void the_user_selects_account_ownership(String idOfOwnershipRadioButton) {
//        WebElement accountOwnershipRadioButton =  driver.findElement(By.id(idOfOwnershipRadioButton));
//        accountOwnershipRadioButton.click();
//
//    }
//    @When("the user names the account {string}")
//    public void the_user_names_the_account(String accountName) {
//        WebElement accountNameTxt =  driver.findElement(By.id("name"));
//        accountNameTxt.sendKeys(accountName);
//    }
//    @When("the user makes the initial deposit of ${double}")
//    public void the_user_makes_the_initial_deposit_of_$(Double openingBalance) {
//        WebElement initialDepositTxt =  driver.findElement(By.id("openingBalance"));
//        initialDepositTxt.sendKeys(String.valueOf(openingBalance));
//    }
//    @When("the user clicks on submit")
//    public void the_user_clicks_on_submit() {
//        WebElement submit =  driver.findElement(By.id("newCheckingSubmit"));
//        submit.click();


        @When("the user creates a new checking account with the following data")
        public void the_user_creates_a_new_checking_account_with_the_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList) {
            createCheckingPage.createNewChecking(checkingAccountInfoList);


        }
        @Then("the user should see the green {string} message")
        public void the_user_should_see_the_green_message(String expectedConfMessage) {
//            WebElement newAccountMessageDiv = driver.findElement(By.id("new-account-conf-alert"));
           expectedConfMessage = "Confirmation " + expectedConfMessage + "\n×";
//
//            //String actualConfMessage = newAccountMessageDiv.getText();
//            //System.out.println(actualConfMessage.substring(0, actualConfMessage.indexOf("\n")));
         assertEquals(expectedConfMessage, viewCheckingAccountPage.getActualCreateAccountConfirmationMessage());
        }
        @Then("the user should see newly added account card")
        public void the_user_should_see_newly_added_account_card(List<AccountCard> accountCardList) {

            Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();
            AccountCard expectedResult = accountCardList.get(0);

            assertEquals(expectedResult.getAccountName(), actualResultMap.get("actualAccountName"));
            assertEquals("Account: " + expectedResult.getAccountType(), actualResultMap.get("actualAccountType"));
            assertEquals("Ownership: " + expectedResult.getOwnership(), actualResultMap.get("actualOwnership"));
            assertEquals("Interest Rate: " + expectedResult.getInterestRate(), actualResultMap.get("actualInterestRate"));

            String expectedBalance = String.format("%.2f", expectedResult.getBalance());
            assertEquals("Balance: $" + expectedBalance, actualResultMap.get("actualBalance"));


        }

        @Then("the user should see the following transaction")
        public void the_user_should_see_the_following_transaction(List<BankTransaction> expectedTransactions) {
           Map<String, String> actualResultMap =  viewCheckingAccountPage.getNewlyAddedCheckingAccountTransactionInfoMap();

            BankTransaction expectedTransaction = expectedTransactions.get(0);

            assertEquals(expectedTransaction.getCategory(),actualResultMap.get("actualCategory"), "transaction category mismatch");
            //assertEquals(expectedTransaction.getDescription(), actualDescription, "transaction description mismatch");
            assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualResultMap.get("actualAmount")), "transaction amount mismatch");
            assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualResultMap.get("actualBalance")), "transaction balance mismatch");

        }
    }
