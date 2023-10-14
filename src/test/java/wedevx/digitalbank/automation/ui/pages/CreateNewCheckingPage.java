package wedevx.digitalbank.automation.ui.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import wedevx.digitalbank.automation.ui.utils.ConfigReader;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class CreateNewCheckingPage extends BaseMenuPage{
    public CreateNewCheckingPage(WebDriver driver) {
       super(driver);

    }


    @FindBy(id = "Standard Checking")
    private WebElement standardCheckingAccountTypeRadioButton;

    @FindBy(id = "Interest Checking")
    private WebElement interestCheckingAccountTypeRadioButton;

    @FindBy(id = "Individual")
    private WebElement individualOwnershipTypeRadioButton;

    @FindBy(id = "Joint")
    private WebElement jointOwnershipTypeRadioButton;

    @FindBy(id = "name")
    private WebElement accountNameTxt;

    @FindBy(id = "openingBalance")
    private WebElement openingBalanceTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private WebElement submitBtn;

    @FindBy(id = "new-account-conf-alert")
    private WebElement newAccountMessageAlertDiv;


    public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        NewCheckingAccountInfo testDataForOneCheckingAccount = checkingAccountInfoList.get(0);

        //users clicks on the ckeching button
        checkingMenu.click();

        //users clicks on the new checking button
        newCheckingButton.click();

        assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"), getDriver().getCurrentUrl(), "new Checking Button didn't take to the " + ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"));

        //users selects account type
        if (testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")) {
            standardCheckingAccountTypeRadioButton.click();
        } else if (testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking")) {
            interestCheckingAccountTypeRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid checking account type option");
        }

        //user selectes account ownership

        if (testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")) {
            individualOwnershipTypeRadioButton.click();
        } else if (testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")) {
            jointOwnershipTypeRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid ownership type option. Only supports Individual Checking and Joint Checking");
        }


        //user names the account
        accountNameTxt.sendKeys(testDataForOneCheckingAccount.getAccountName());

        //user makes an initial deposit
        openingBalanceTxtBox.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));

        //user clicks on submit
        submitBtn.click();

    }



}

