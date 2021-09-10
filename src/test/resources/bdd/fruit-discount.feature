Feature: The discount on fruits
  ====
  [quote]
  ____
  In order to increase sales
  As a fruit seller
  I want the cash register to take into account volume discount like 1 free for X units bought
  ____
  ====

  Background: :
    Given the price of a Cerises is 75
    And the price of a Bananes is 150

  Scenario Outline: Don't apply a discount when not enough fruits
    Given there is a free unit every 3 <fruit>
    And the basket contains <volume> <fruit>
    When the customer ask for the receipt
    Then the receipt total is <total>
    And the receipt display the price of <total> for <volume> <fruit>

    Examples:
      | fruit   | volume | total |
      | Cerises | 2      | 150   |
      | Cerises | 3      | 150   |
      | Cerises | 4      | 225   |
      | Cerises | 6      | 300   |

  Scenario: Apply a discount when enough fruits
    Given there is a free unit every 3 Cerises
    And the basket contains:
      | Cerises | 3 |
    When the customer ask for the receipt
    Then the receipt total is 150
    And the receipt display the price of 150 for 3 Cerises

  Scenario: Apply several discounts
    Given there is a free unit every 2 Cerises
    And there is a free unit every 2 Bananes
    And the basket contains:
      | Cerises | 2 |
      | Bananes | 2 |
    When the customer ask for the receipt
    Then the receipt total is 225
    And the receipt display the price of 75 for 2 Cerises
    And the receipt display the price of 150 for 2 Bananes

    