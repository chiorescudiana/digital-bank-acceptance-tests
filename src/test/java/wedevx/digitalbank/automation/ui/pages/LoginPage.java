package wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

    public class LoginPage extends BasePage {

        public LoginPage (WebDriver driver){
            super(driver);

        }

        @FindBy(id ="username")
        private WebElement usernameTxtBox;

        @FindBy(id ="password")
        private  WebElement passwordTxtBtn;

        @FindBy(id ="remember-me")
        private WebElement remember_meCheckBox;

        @FindBy(xpath ="//button[@type='submit']")
        private WebElement submitBtn;

        @FindBy(xpath = "//a[contains(text(), 'Sign Up Here')]")
        private WebElement signUpHereLink;


        //create action methods is the last step
        public void login(String username, String password){
            usernameTxtBox.sendKeys(username);
            passwordTxtBtn.sendKeys(password);

            submitBtn.click();
        }
        public void clickToSignUp(){
            signUpHereLink.click();


        }

    }


