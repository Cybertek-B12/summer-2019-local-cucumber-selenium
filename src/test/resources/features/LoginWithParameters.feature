Feature: Login as different users

  Scenario: Login as a driver user
    Given the user is on the login page
    When user logs in using "user11" and "UserUser123"
    Then the user should be able to login
    And the title should contain "Dashboard"


    # write tests for sales manager and store manager

  Scenario: Login as a sales manger user
    Given the user is on the login page
    When user logs in using "salesmanager101" and "UserUser123"
    Then the user should be able to login
    And the title should contain "Dashboard"


  Scenario: Login as a store manger user
    Given the user is on the login page
    When user logs in using "storemanager85" and "UserUser123"
    Then the user should be able to login
    And the title should contain "Dashboard"

