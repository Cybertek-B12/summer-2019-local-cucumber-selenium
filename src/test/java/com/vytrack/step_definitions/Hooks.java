package com.vytrack.step_definitions;


import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("\nThis is coming from before scenario");
    }

    @After
    public void tearDown() {
        System.out.println("This is coming from after scenario\n");
        Driver.closeDriver();
    }

    @After("@sales_manager")
    public void tearDownSalesManager(){
        System.out.println("This is coming from after scenario for sales managers\n");

    }
}
