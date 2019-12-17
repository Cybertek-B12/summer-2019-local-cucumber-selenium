@nav_menu
Feature: Navigation menu


  Scenario: Fleet --> Vehicles
    Given the user is on the login page
    And the user enters the sales manager information
    When the user goes to Fleet, Vehicles
    Then the url should be https://qa3.vytrack.com/entity/Extend_Entity_Carreservation

  Scenario: Marketing --> Campaigns
    Given the user is on the login page
    And the user enters the sales manager information
    When the user goes to Marketing, Campaigns
    Then the url should be https://qa3.vytrack.com/campaign/

  Scenario: Activities --> Calendar Events
    Given the user is on the login page
    And the user enters the sales manager information
    When the user goes to Activities, Calendar Events
    Then the url should be https://qa3.vytrack.com/calendar/event

