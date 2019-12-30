Feature: Contacts page


  Scenario: Default page number
    Given a driver is logged in
    When the user goes to "Customers" "Contacts"
    Then default page number should be 1


  Scenario: Menu options
    Given a driver is logged in
    Then the user should see following menu options
      | Fleet      |
      | Customers  |
      | Activities |
      | System     |


  Scenario:  login as a given user
    Given the user is on the login page
    When the user logs in using following credentials
      | fname    | Mizgin      |
      | username | user1       |
      | password | UserUser123 |
      | lname    | Ayaz        |
    Then the user should be able to login


  Scenario Outline: Login as many given users
    Given the user is on the login page
    When the user logs in using following credentials
      | fname    | <firstname> |
      | lname    | <lastname>  |
      | username | <username>  |
      | password | UserUser123 |
    Then the user should be able to login

    Examples:
      | firstname | lastname | username        |
      | Parsa     | Mehdi    | salesmanager101 |
      | Rahwa     | Maaza    | storemanager85  |
