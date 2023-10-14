package wedevx.digitalbank.automation.ui.steps;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wedevx.digitalbank.automation.ui.pages.LoginPage;
import wedevx.digitalbank.automation.ui.utils.Driver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginPageSteps {

    WebDriver driver = Driver.getDriver();
    private LoginPage loginPage = new LoginPage(driver);

    @Given("the user visits {string}")
   public void the_user_visits(String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @When("user entered valid username {string} and valid password {string}")
    public void user_entered_valid_username_and_valid_password(String username, String password) {

       WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);



    }

    @When("user clicks submit button")
    public void user_clicks_submit_button() {
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
    }

    @Then("user should successfully log in")
    public void user_should_successfully_log_in() {

    }

    @Then("user should be directed to {string}")
    public void user_should_be_directed_to(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("User is not on the expected page.", expectedUrl, currentUrl);

    }

    @When("user entered invalid username {string} and invalid password {string}")
    public void user_entered_invalid_username_and_invalid_password(String invalidUsername, String invalidPassword) {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(invalidUsername);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(invalidPassword);
    }

    @Then("user should not be able to log in")
    public void user_should_not_be_able_to_log_in() {

    }

    @Then("user should see an error message {string}")
    public void user_should_see_an_error_message(String string) {
        WebElement errorMessage = driver.findElement(By.xpath("//div/span/.."));
        Assert.assertTrue(errorMessage.isDisplayed());
        System.out.println(errorMessage.getText());

    }


    @When("remember me button is present on the page")
    public void remember_me_button_is_present_on_the_page() {
        WebElement rememberMeButton = driver.findElement(By.id("remember-me"));
        Assert.assertTrue("Remember me button should be present", rememberMeButton.isDisplayed());
    }

    @Then("user clicks remember me button")
    public void user_clicks_remember_me_button() {
        WebElement rememberMeButton = driver.findElement(By.id("remember-me"));
        rememberMeButton.click();
    }


    @When("Sign up here Link is present on the page")
    public void sign_up_here_link_is_present_on_the_page() {
        WebElement signUpLink = driver.findElement(By.xpath("//a[contains(text(), 'Sign Up Here')]"));
        Assert.assertTrue("Sign up link should be present", signUpLink.isDisplayed());
    }

    @When("user clicks on sign up here Link")
    public void user_clicks_on_sign_up_here_link() {
        WebElement signUpLink = driver.findElement(By.xpath("//a[contains(text(), 'Sign Up Here')]"));
        signUpLink.click();
    }

    @Then("user should be taken to the registration page")
    public void user_should_be_taken_to_the_registration_page() {
        String expectedUrl = "https://dbank-qa.wedevx.co/bank/signup";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Then("user should be able to register")
    public void user_should_be_able_to_register() {

    }



}
