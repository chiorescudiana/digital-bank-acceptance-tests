package wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewSavingAccountPage extends BasePage{
    public NewSavingAccountPage(WebDriver driver) {
        super(driver);

    }
    @FindBy(xpath = "//img[@src = '/bank/images/logo.png']")
    private WebElement imageForHomePage;

    @FindBy(id = "savings-menu")
    private WebElement savingMenuButton;

    @FindBy(id = "new-savings-menu-item")
    private WebElement newSavingMenuButton;

    @FindBy(xpath = "//input[@type='radio' and @value='Savings']")
    private WebElement savingsRadioButton;

    @FindBy(xpath = "//input[@type='radio' and @value='Individual']")
    private WebElement individualRadioButton;

    @FindBy(id = "name")
    private WebElement accountName;

    @FindBy(id = "openingBalance")
    private WebElement initialDepositTxtButton;

    @FindBy(id = "newSavingsSubmit")
    private WebElement submitButton;

    @FindBy(id = "newSavingsReset")
    private WebElement newSavingResetButton;


    @FindBy(id = "new-account-conf-alert")
    private WebElement successfullyConfirmationAlertMessage;


    @FindBy(xpath = "//div[@class='card-body']")
    private WebElement cardBody;

    @FindBy(id = "transactionTable")
    private WebElement transactionTable;

    public void createSavingsAccount(){

        savingMenuButton.click();
        newSavingMenuButton.click();
        savingsRadioButton.click();
        individualRadioButton.click();
        accountName.sendKeys("name");
        initialDepositTxtButton.sendKeys("openingBalance");
        submitButton.click();






    }


















}
