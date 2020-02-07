package com.vytrack.utilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridExample {

    public static void main(String[] args) throws MalformedURLException {

        // Create URL for the hub
        URL url = new URL("http://ec2-3-90-242-72.compute-1.amazonaws.com:4444/wd/hub");
        // create options
        System.out.println("STARTING OW");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.LINUX);

        WebDriver driver = new RemoteWebDriver(url, desiredCapabilities);

        // RemoteWebDriver--> used to open browser remotely using grid
        driver.get("https://selenium.dev");
        System.out.println(driver.getTitle());
        BrowserUtils.waitFor(3);
        driver.quit();

    }
}
