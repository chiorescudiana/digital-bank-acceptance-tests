package wedevx.digitalbank.automation.ui.steps;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import wedevx.digitalbank.automation.ui.models.NewSavingAccountInfo;
import wedevx.digitalbank.automation.ui.pages.NewSavingAccountPage;
import wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import wedevx.digitalbank.automation.ui.utils.Driver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class newSavingAccountSteps {
    WebDriver driver = Driver.getDriver();

    NewSavingAccountPage newSavingAccount = new NewSavingAccountPage(driver);

    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @When("the user creates a new saving account with the following data")
    public void the_user_creates_a_new_saving_account_with_the_following_data(List<NewSavingAccountInfo> savingsAccountInfoList) {
       NewSavingAccountInfo testDataForOneSavingsAccount = savingsAccountInfoList.get(0);

    }
}
