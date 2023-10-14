package wedevx.digitalbank.automation.ui.steps;

import io.cucumber.java.*;
import wedevx.digitalbank.automation.ui.utils.DBUtils;
import wedevx.digitalbank.automation.ui.utils.Driver;

import java.util.concurrent.TimeUnit;

import static wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class Hooks {

    @Before("@Registration")
    public static void establishConnectionToDB(){
        DBUtils.establishConnection();

    }
    @Before("not @Registration")
    public void the_user_is_on_the_home_page_of_dbank() {
        //getDriver().get("https://dbank-qa.wedevx.co/bank/login");
        getDriver().get("http://dianachiorescu.mydevx.com/bank/login");

    }
    @After("not @NegativeRegistrationCases")
    public void afterEachScenario(Scenario scenario){
        Driver.takeScreenShot(scenario);
        Driver.closeDriver();
    }
    @After()
    public static void closeConnectionToDB(){
        DBUtils.closeConnection();
    }

}
