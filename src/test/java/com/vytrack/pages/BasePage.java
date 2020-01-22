package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {

    @FindBy(css = "span.title-level-1")
    public List<WebElement> menuOptions;

    @FindBy(css = "div[class='loader-mask shown']")
    @CacheLookup
    protected WebElement loaderMask;

    @FindBy(css = "h1[class='oro-subtitle']")
    public WebElement pageSubTitle;

    @FindBy(css = "#user-menu > a")
    public WebElement userName;

    @FindBy(linkText = "Logout")
    public WebElement logOutLink;

    @FindBy(linkText = "My User")
    public WebElement myUser;

    @FindBy(className = "add-filter-button")
    public WebElement manageFilters;


    @FindBy(className = "filter-criteria-selector")
    public List<WebElement> availableFilters;

    @FindBy(css = ".choice-filter button.dropdown-toggle")
    public WebElement filterOptionDropdown;

    @FindBy(css = ".choice-filter input[name='value']")
    public WebElement filterInput;

    @FindBy(css = ".choice-filter button.filter-update")
    public WebElement filterUpdateBtn;

    @FindBy(css = "a[title='Filters']")
    public WebElement showHideFiltersBtn;

    @FindBy(css = ".table-condensed>tbody>tr")
    public List<WebElement> mainDataTableRows;



    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }


    /**
     * @return page name, for example: Dashboard
     */
    public String getPageSubTitle() {
        //ant time we are verifying page name, or page subtitle, loader mask appears
        waitUntilLoaderScreenDisappear();
//        BrowserUtils.waitForStaleElement(pageSubTitle);
        return pageSubTitle.getText();
    }


    /**
     * Waits until loader screen present. If loader screen will not pop up at all,
     * NoSuchElementException will be handled  bu try/catch block
     * Thus, we can continue in any case.
     */
    public void waitUntilLoaderScreenDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getUserName() {
        waitUntilLoaderScreenDisappear();
        BrowserUtils.waitForVisibility(userName, 5);
        return userName.getText();
    }


    public void logOut() {
        BrowserUtils.waitFor(2);
        BrowserUtils.clickWithJS(userName);
        BrowserUtils.clickWithJS(logOutLink);
    }

    public void goToMyUser() {
        waitUntilLoaderScreenDisappear();
        BrowserUtils.waitForClickablility(userName, 5).click();
        BrowserUtils.waitForClickablility(myUser, 5).click();

    }

    /**
     * This method will navigate user to the specific module in vytrack application.
     * For example: if tab is equals to Activities, and module equals to Calls,
     * Then method will navigate user to this page: http://qa2.vytrack.com/call/
     *
     * @param tab
     * @param module
     */
    public void navigateToModule(String tab, String module) {
        String tabLocator = "//span[normalize-space()='" + tab + "' and contains(@class, 'title title-level-1')]";
        String moduleLocator = "//span[normalize-space()='" + module + "' and contains(@class, 'title title-level-2')]";
        try {
            BrowserUtils.waitForClickablility(By.xpath(tabLocator), 5);
            WebElement tabElement = Driver.get().findElement(By.xpath(tabLocator));
            new Actions(Driver.get()).moveToElement(tabElement).pause(200).doubleClick(tabElement).build().perform();
        } catch (Exception e) {
            BrowserUtils.clickWithWait(By.xpath(tabLocator), 5);
        }
        try {
            BrowserUtils.waitForPresenceOfElement(By.xpath(moduleLocator), 5);
            BrowserUtils.waitForVisibility(By.xpath(moduleLocator), 5);
            BrowserUtils.scrollToElement(Driver.get().findElement(By.xpath(moduleLocator)));
            Driver.get().findElement(By.xpath(moduleLocator)).click();
        } catch (Exception e) {
//            BrowserUtils.waitForStaleElement(Driver.get().findElement(By.xpath(moduleLocator)));
            BrowserUtils.clickWithTimeOut(Driver.get().findElement(By.xpath(moduleLocator)), 5);
        }
    }

    public Boolean isFilterOptionDisplayed(String filTerOption) {
        List<String> availableFiltersList = BrowserUtils.getElementsText(availableFilters);
        for (String text : availableFiltersList) {
            if (text.startsWith(filTerOption)) {
                return true;
            }
        }
        return false;

    }

    public void addOrRemoveFilter(String filter, boolean check) {
        manageFilters.click();
        if (check && !filterOptionToAddOrRemove(filter).isSelected()) {
            filterOptionToAddOrRemove(filter).click();
            return;
        }
        if (!check && filterOptionToAddOrRemove(filter).isSelected()) {
            filterOptionToAddOrRemove(filter).click();
        }

    }

    public void clickAvailableFilterDropdown(String filter) {
        for (WebElement displayedFilter : availableFilters) {
            String text = displayedFilter.getText().toLowerCase();
            if (text.startsWith(filter.toLowerCase())) {
                displayedFilter.click();
                return;
            }
        }
        Assert.fail("Filter option not found: " + filter);
    }

    public void selectFilterOption(String filterOption) {
        filterOptionDropdown.click();
        String xpath = String.format("//a[contains(text(), '%s')]", filterOption.toLowerCase());
        Driver.get().findElement(By.xpath(xpath)).click();
        filterUpdateBtn.click();

    }

    public void selectFilterOption(String filterOption, String filterValue) {
        filterOptionDropdown.click();
        String xpath = String.format("//a[contains(text(), '%s')]", filterOption.toLowerCase());
        Driver.get().findElement(By.xpath(xpath)).click();
        filterInput.sendKeys(filterValue);
        filterUpdateBtn.click();
    }


    public WebElement filterOptionToAddOrRemove(String filter) {
        String css = String.format("input[title=\"%s\"]", filter);
        return Driver.get().findElement(By.cssSelector(css));
    }

    public void enterFilterAndSearch(List<String> filterOptions){
        if (!isFilterOptionDisplayed(filterOptions.get(0))){
            addOrRemoveFilter(filterOptions.get(0), true);
        }
        clickAvailableFilterDropdown(filterOptions.get(0));
        BrowserUtils.waitFor(1);
        if (filterOptions.size() == 2 || filterOptions.get(2).isEmpty()) {
            selectFilterOption(filterOptions.get(1));
        } else {
            selectFilterOption(filterOptions.get(1), filterOptions.get(2));
        }
        BrowserUtils.waitFor(1);

    }

}
