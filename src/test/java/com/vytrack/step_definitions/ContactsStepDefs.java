package com.vytrack.step_definitions;

import com.vytrack.pages.DashboardPage;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactsStepDefs {
    @Then("the user should see following menu options")
    public void the_user_should_see_following_menu_options(List<String> list) {
        System.out.println("list.size() = " + list.size());
        System.out.println(list);


        // get the actual values from UI
        DashboardPage dashboardPage = new DashboardPage();

        List<String> actualList = new ArrayList<>();
        BrowserUtils.waitFor(2);

        for (WebElement option : dashboardPage.menuOptions) {
            actualList.add(option.getText());
        }

        // compare actual list to the expected list
        Assert.assertEquals(list, actualList);

    }

}
