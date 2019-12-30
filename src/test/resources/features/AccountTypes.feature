Feature: Login as different user types

  # WARM UP : implement the following scenarios
  # you can reuse previous step defs classes or create a new one
  # use any tag

  Scenario: Driver user
    Given the user logs in as a "driver"
    When the user goes to "Activities" "Calendar Events"
    Then the title should contain "Calendar Events - Activities"

  Scenario: Sales manager user
    Given the user logs in as a "sales manager"
    When the user goes to "Customers" "Accounts"
    Then the title should contain "Accounts - Customers"

  Scenario: Store manager user
    Given the user logs in as a "store manager"
    When the user goes to "Customers" "Accounts"
    Then the title should contain "Accounts - Customers"



# test the login functionality using different users

  Scenario Outline: Test login with different types
    Given the user logs in as a "<usertype>"

    Examples:
      | usertype      |
      | driver        |
      | sales manager |
      | store manager |


  Scenario Outline: Test login as <usertype>
    Given the user logs in as a "<usertype>"

    Examples:
      | usertype      |
      | driver        |
      | store manager |
      | boss          |

    # FORMAT MAC:OPTION+CMD+L
    # FORMAT WIN:CTRL+ALT+L

  @wip
  Scenario Outline: Test login as <usertype>
    Given the user logs in as a "<usertype>"
    When the user goes to "<tab>" "<module>"
    Then the title should contain "<title>"

    Examples:
      | usertype | tab        | module          | title                        |
      | driver   | Activities | Calendar Events | Calendar Events - Activities |
      | driver   | Customers  | Accounts        | Accounts - Customers         |














