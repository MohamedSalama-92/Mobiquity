Feature: validating emails in the comments

  As a developer using the API I searched for a specific user and
  get his posts and reached the comments to validate the emails

  Scenario Outline: Validating emails in comments section of a specific user
    Given Developer Search for User using "<Username>"
    When Developer Fetched User Id from API Response
    And Developer Get all Posts using User Id
    And Developer Fetched Posts Ids List from API Response
    And Developer use posts Ids List to get post's comments
    Then All Emails written in the comments should be in a correct format

    Examples: Validate Emails in comments
              |Username|
              |Delphine|

  Scenario Outline: Searching API for a username that doesn't exists
    Given Developer Search for User using "<Username>"
    When Status Code should be <StatusCode>
    Then The Response should be empty

    Examples: Searching for not existing user
      |Username|StatusCode|
      |test    |200       |

  Scenario Outline: Trying to get posts using invalid User Id
    Given Developer Search for posts using incorrect "<UserId>"
    When Status Code is <StatusCode>
    Then The Response of the API should be empty

    Examples: Searching for not existing user
      |UserId|StatusCode|
      |Test  |200       |