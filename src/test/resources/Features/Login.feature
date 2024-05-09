Feature: Verifying omr page login module

  Scenario Outline: verifying omr page login with valid data
    Given user is on the omr page
    When user login "<userName>" and "<password>"
    Then user should verify after login success message

    Examples: 
      | userName                      | password |
      | ajithkumarmoorthy11@gmail.com | Ajith@3  |