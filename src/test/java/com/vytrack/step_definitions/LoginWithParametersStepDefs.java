package com.vytrack.step_definitions;

import com.vytrack.pages.ContactsPage;
import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginWithParametersStepDefs {

    private DashboardPage dashboardPage = new DashboardPage();
    private LoginPage loginPage = new LoginPage();

    @When("user logs in using {string} and {string}")
    public void user_logs_in_using_and(String username, String password) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        loginPage.login(username, password);

    }

    @Then("the title should contain {string}")
    public void the_title_should_contain(String title) {
        System.out.println("title = " + title);
        BrowserUtils.waitFor(3);
        Assert.assertTrue("Actual title: " + Driver.get().getTitle(), Driver.get().getTitle().contains(title));
    }


    @Given("a driver is logged in")
    public void a_driver_is_logged_in() {
        Driver.get().get(ConfigurationReader.get("url"));
        String username = ConfigurationReader.get("driver_username");
        String password = ConfigurationReader.get("driver_password");
        loginPage.login(username, password);
    }


    @When("the user goes to {string} {string}")
    public void the_user_goes_to(String tab, String module) {
        dashboardPage.navigateToModule(tab, module);

    }

    @Given("I login as a {string}")
    public void i_login_as_a(String user) {
        Driver.get().get(ConfigurationReader.get("url"));
        String username = null, password = null;
        switch (user) {
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
        }
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        loginPage.login(username, password);
    }

    @When("I navigate to {string} {string}")
    public void i_navigate_to(String tab, String module) {
        dashboardPage.navigateToModule(tab, module);

    }


}
