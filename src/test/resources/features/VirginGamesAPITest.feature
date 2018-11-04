@api
Feature: VirginGames Login validation via API
  As an API Consumer
  I want to login in Virgin games
  So that I can Validate

  @apiloginvalidation
  Scenario Outline: 1. Invalid Login Credentials Validation
    Given I have data value for "username" as "<usernamevalue>" for login
    And I have data value for "password" as "<passwordvalue>" for login
    And I have data value for "recaptcha" as "" for login
    And I created the JSON File in DynamicJSONData.json
    When the user request POST method against the URL /api/login/recaptcha?tmp=1
    Then the user will receive response code as <statuscode>
    Then the user should get "[<responseValue>]" in the path "error"

    Examples:
    |usernamevalue|passwordvalue|statuscode |responseValue                   |
    |             |             |422        |username.password.required      |
    |user         |p            |422        |password.length.invalid         |
    |u            |password     |422        |username.length.invalid         |
    |user         |             |422        |username.password.required      |
    |             |password     |422        |username.password.required      |
    |user         |password     |400        |member.invalid.credentials      |

