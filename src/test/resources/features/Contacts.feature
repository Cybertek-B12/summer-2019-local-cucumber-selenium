Feature: Contacts page


  Scenario: Default page number
    Given a driver is logged in
    When the user goes to "Customers" "Contacts"
    Then default page number should be 1


  @wip
  Scenario: Menu options
    Given a driver is logged in
    Then the user should see following menu options
      | Fleet       |
      | Customers   |
      | Activities  |
      | System      |
      | System      |
      | System      |
      | System      |
      | System      |
      | System      |
      | System      |

