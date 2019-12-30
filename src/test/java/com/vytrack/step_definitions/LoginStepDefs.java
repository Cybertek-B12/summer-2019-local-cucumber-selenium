package com.vytrack.step_definitions;

import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefs {

    @Then("the user should be able to login")
    public void the_user_should_be_able_to_login() {
        System.out.println("Verifying that user is logged in");
        BrowserUtils.waitFor(2);
        String actualTitle = Driver.get().getTitle();
        Assert.assertEquals("Dashboard", actualTitle);
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        System.out.println("Opening the login page");
        // Driver.get() --> this gets the webdriver
        // Driver.get()  === driver
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
    }

    @When("the users enters the driver information")
    public void the_users_enters_the_driver_information() {
        System.out.println("Entering driver login info");
        String dUsername = ConfigurationReader.get("driver_username");
        String dPassword = ConfigurationReader.get("driver_password");

        LoginPage loginPage = new LoginPage();
        loginPage.login(dUsername, dPassword);

    }

    @When("the user enters the sales manager information")
    public void the_user_enters_the_sales_manager_information() {
        System.out.println("Entering sales manager login info");
        String sUsername = ConfigurationReader.get("sales_manager_username");
        String sPassword = ConfigurationReader.get("sales_manager_password");

        LoginPage loginPage = new LoginPage();
        loginPage.login(sUsername, sPassword);

    }


    @When("the user enters the store manager information")
    public void the_user_enters_the_store_manager_information() {
        System.out.println("Entering store manager login info");

        String sUsername = ConfigurationReader.get("store_manager_username");
        String sPassword = ConfigurationReader.get("store_manager_password");

        LoginPage loginPage = new LoginPage();
        loginPage.login(sUsername, sPassword);

    }

    @Given("the user logs in as a {string}")
    public void the_user_logs_in_as_a(String userType) {
        Driver.get().get(ConfigurationReader.get("url"));
        System.out.println("userType = " + userType);
        String username= null, password = null;
        switch (userType) {
            case "driver":
                username = ConfigurationReader.get("driver_username");
                password = ConfigurationReader.get("driver_password");
                break;
            case "sales manager":
                username = ConfigurationReader.get("sales_manager_username");
                password = ConfigurationReader.get("sales_manager_password");
                break;
            case "store manager":
                username = ConfigurationReader.get("store_manager_username");
                password = ConfigurationReader.get("store_manager_password");
                break;
            default:
                // Assert.fail --> just fails the test
                Assert.fail("Wrong user type provided");
        }
        new LoginPage().login(username, password);

    }

}
