package com.vytrack.step_definitions;

import com.vytrack.pages.DashboardPage;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationMenuStepDefintions {
    private DashboardPage dashboardPage = new DashboardPage();

    @When("the user goes to Fleet, Vehicles")
    public void the_user_goes_to_Fleet_Vehicles() {
        dashboardPage.navigateToModule("Fleet", "Vehicles");
    }

    @When("the user goes to Marketing, Campaigns")
    public void the_user_goes_to_Marketing_Campaigns() {
        dashboardPage.navigateToModule("Marketing", "Campaigns");

    }

    @Then("the url should be {string}")
    public void the_url_should_be(String url) {
        WebDriverWait webDriverWait = new WebDriverWait(Driver.get(), 5);
        Assert.assertTrue(webDriverWait.until(ExpectedConditions.urlToBe(url)));
    }


    @When("the user goes to Activities, Calendar Events")
    public void the_user_goes_to_Activities_Calendar_Events() {
        dashboardPage.navigateToModule("Activities", "Calendar Events");
    }

}
