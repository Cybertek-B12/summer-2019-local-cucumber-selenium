@login @smoke
Feature: Users should be able to login

  Background:
    Given the user is on the login page

  @driver @VYT-123
  Scenario: Login as a driver
    When the users enters the driver information
    Then the user should be able to login

  @sales_manager @VYT-123
  Scenario: Login as a sales manager
    When the user enters the sales manager information
    Then the user should be able to login

  @store_manager
  Scenario: Login as a s tore manager
    When the user enters the store manager information
    Then the user should be able to login

  @store_manager
  Scenario: Login and fail
    When the user enters the store manager information
    Then the user should be able to login
    And the title should contain "JKGADKJHGDAF"


  @wip
  Scenario Outline: Login multiple
    When the user enters the <user> information
    Then the user should be able to login

    Examples:
      | user          |
      | sales manager |
      | store manager |