package wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCheckingAccountPage extends BaseMenuPage {
    private WebDriver driver;

    public ViewCheckingAccountPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(id = "new-account-conf-alert")
    private WebElement newAccountMessageAlertDiv;

    @FindBy(xpath = "//div[@id='firstRow']/div")
    private List<WebElement> allFirstRowDivs;

    @FindBy(xpath = "//table[@id='transactionTable']/tbody/tr")
    private WebElement firstRowOfTransaction;


    public Map<String, String> getNewlyAddedCheckingAccountTransactionInfoMap() {
        List<WebElement> firstRowColumns = firstRowOfTransaction.findElements(By.xpath("td"));


        Map<String, String> actualResultMap = new HashMap<>();
        actualResultMap.put("actualCategory", firstRowColumns.get(1).getText());
        actualResultMap.put("actualDescription", firstRowColumns.get(2).getText());
        actualResultMap.put("actualAmount", firstRowColumns.get(3).getText().substring(1));
        actualResultMap.put("actualBalance", firstRowColumns.get(3).getText().substring(1));

        return actualResultMap;

    }
public Map<String, String> getNewlyAddedCheckingAccountInfoMap(){
    WebElement lastAccountCard = allFirstRowDivs.get(allFirstRowDivs.size()-1);
    String actualResult = lastAccountCard.getText();

    Map<String, String> actualResultMap = new HashMap<>();
    actualResultMap.put("actualAccountName", actualResult.substring(0, actualResult.indexOf("\n")).trim());
    actualResultMap.put("actualAccountType", actualResult.substring(actualResult.indexOf("Account"), actualResult.indexOf("Ownership")).trim());
    actualResultMap.put("actualOwnership", actualResult.substring(actualResult.indexOf("Ownership:"), actualResult.indexOf("Account Number")).trim());
    actualResultMap.put("actualAccountNumber", actualResult.substring(actualResult.indexOf("Account Number:"), actualResult.indexOf("Interest Rate")).trim());
    actualResultMap.put("actualInterestRate", actualResult.substring(actualResult.indexOf("Interest Rate:"), actualResult.indexOf("Balance")).trim());
    actualResultMap.put("actualBalance", actualResult.substring(actualResult.indexOf("Balance:")).trim());

return actualResultMap;
}


    //List<WebElement> allFirstRowDivs = driver.findElements(By.xpath("//div[@id='firstRow']/div"));
    //for(WebElement card : allFirstRowDivs) {
    //System.out.println(card.getText());
    //WebElement lastAccountCard = allFirstRowDivs.get(allFirstRowDivs.size()-1);
    //String actualResult = lastAccountCard.getText();
    //String actualAccountName = actualResult.substring(0, actualResult.indexOf("\n")).trim();
    //String actualAccountType = actualResult.substring(actualResult.indexOf("Account"), actualResult.indexOf("Ownership")).trim();
    //String actualOwnership = actualResult.substring(actualResult.indexOf("Ownership:"), actualResult.indexOf("Account Number")).trim();
    //String actualAccountNumber = actualResult.substring(actualResult.indexOf("Account Number:"), actualResult.indexOf("Interest Rate")).trim();
    //String actualInterestRate = actualResult.substring(actualResult.indexOf("Interest Rate:"), actualResult.indexOf("Balance")).trim();
    //String actualBalance = actualResult.substring(actualResult.indexOf("Balance:")).trim();

//    AccountCard expectedResult = accountCardList.get(0);

    public String getActualCreateAccountConfirmationMessage() {
        return newAccountMessageAlertDiv.getText();

    }
}
