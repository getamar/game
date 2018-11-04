@UI
Feature: VirginGames Login Validation
 As a Customer
 I want login with different credentials
 So that I can validate the login details


  Scenario Outline: Validate login error condition
    Given the customer is in Virgin Home Page
    When the customer login with invalid userid - "<username>" and passord - "<password>"
    Then I should get the error message as "<errormessage>"

    Examples:
      |username  | password|errormessage|
      |user      | password|Please try again, your username/email or password has not been recognised|
      |user      |    test |Your password must be 6 to 16 characters long                           |
      |u         | password|Your username/email must be 4 to 60 characters long                     |
      |user      |         |Both your username and password are required                           |
      |          |password |Both your username and password are required                           |
      |          |         |Both your username and password are required                           |
      |test@gmail.com|password|Please try again, your username/email or password has not been recognised|