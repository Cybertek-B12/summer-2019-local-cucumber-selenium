package com.vytrack.step_definitions;

import com.vytrack.pages.ContactsPage;
import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.DbUtility;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @When("the user logs in using following credentials")
    public void the_user_logs_in_using_following_credentials(Map<String, String> userData) {
        System.out.println(userData);
        System.out.println("Firstname: " + userData.get("fname"));
        System.out.println("Lastname: " + userData.get("lname"));
        System.out.println("Username: " + userData.get("username"));
        System.out.println("Password: " + userData.get("password"));

        LoginPage loginPage = new LoginPage();

        loginPage.login(userData.get("username"), userData.get("password"));
    }


    @When("the user selects following filter option:")
    public void the_user_selects_following_filter_option(List<List<String>> filterOptions) {
        ContactsPage contactsPage = new ContactsPage();
        BrowserUtils.waitFor(2);
        System.out.println("asdfasfd");

        // click on the filter option
        contactsPage.showHideFiltersBtn.click();

        // iterating through the filters
        for (List<String> filterOption : filterOptions) {
            // enterFilterAndSearch() --> takes list of filter iformation and enters it on the ui
            contactsPage.enterFilterAndSearch(filterOption);
        }

    }

    @Then("main table display correct values")
    public void main_table_display_correct_values() {
        // GET THE ACTUAL FROM DATABASE
        String url = ConfigurationReader.get("qa3_db_url");
        String username = ConfigurationReader.get("qa3_db_username");
        String password = ConfigurationReader.get("qa3_db_password");
        DbUtility.createConnection(url, username, password);

        String sql = "select c.first_name,  c.last_name, p.phone\n" +
                "from orocrm_contact c\n" +
                "join orocrm_contact_phone p\n" +
                "on c.id = p.owner_id\n" +
                "where first_name= 'Omar';";

        List<Object> queryResultList = DbUtility.getRowList(sql);
        System.out.println(queryResultList);




    }

}
