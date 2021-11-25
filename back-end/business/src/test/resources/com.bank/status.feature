Feature: Client and Bank



  Scenario: a client should be able to read his accounts
    Given I am steve
    And I own 130.0 on my account FORTUNEO
    And I own 210.0 on my account N26
    When I check my account FORTUNEO
    Then balance of my account FORTUNEO should be 130.0

  Scenario: a client should be able to make a deposit on his accounts
    Given I am elon
    And I own 100.0 on my account BNP
    When I deposit 10.0 on my account BNP
    And I check my account BNP
    Then balance of my account BNP should be 110.0


  Scenario: a client should be able to make a withdraw from his accounts
    Given I am jeff
    And I own 30.0 on my account BFORBANK
    When I withdraw 10.0 on my account BFORBANK
    And I check my account BFORBANK
    Then balance of my account BFORBANK should be 20.0
