Feature: Create the receipt of a basket
  ====
  [quote]
  ____
  In order to invoice my clients
  As a fruit seller
  I want the cash register to give me a receipt.
  ____
  ====

  Scenario: Nothing is in the basket
    Given the basket is empty
    When the customer ask for the receipt
    Then the receipt total is 0
    And the item list is empty

  Scenario: Just one fruit in the basket
    Given the price of a Pommes is 100
    And the basket contains:
      | Pommes  | 1 |
    When  the customer ask for the receipt
    Then the receipt total is 100
    And the receipt display the price of 100 for 1 Pommes

  Scenario: Several fruits in the basket
    Given the price of fruits are:
      | Pommes  | 100 |
      | Bananes | 150 |
    And the basket contains:
      | Pommes  | 2 |
      | Bananes | 1 |
    When  the customer ask for the receipt
    Then the receipt total is 350
    And the receipt display the price of 200 for 2 Pommes
    And the receipt display the price of 150 for 1 Bananes
