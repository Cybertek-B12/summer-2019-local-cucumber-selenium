Feature: Users should be able to login

  Scenario: Login as a driver
    Given the user is on the login page
    When the users enters the driver information
    Then the user should be able to login