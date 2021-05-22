Feature: Basket level discounts
  ====
  [quote]
  ____
  In order to increase sales
  As a fruit seller
  I want the cash register to handle specific discount at basket level
  ____
  ====

  [source, java]
  -----
  public class LocalFruitsDiscount implements ApplicableBasketDiscount {
  include::../../src/main/java/devoxx/university/cashregister/domain/discount/LocalFruitsDiscount.java[tags=rules]
  }
  -----
  <1> isApplicable tells if the discount can be applied to basket
  <2> getAmount gives the amount. Here depending on the total price without basket discount

  Background:
    Given the price of fruits are:
      | Fraises | 25  |
      | Pommes  | 50  |
      | Poires  | 75  |
      | Bananes | 125 |
      | Ananas  | 200 |

  Scenario: having the "more than 4 different fruits" discount
    Given there is a more than 4 different fruits discount
    And the basket contains:
      | Pommes  | 1 |
      | Fraises | 1 |
      | Bananes | 1 |
      | Poires  | 1 |
      | Ananas  | 1 |
    When the customer ask for the receipt
    Then the receipt displays the 'More than 4 different fruits' discount
    And the receipt total is 375

  Scenario: having the "more than 10 fruits" discount
    Given there is a more than 10 fruits discount
    And the basket contains:
      | Pommes  | 8 |
      | Bananes | 3 |
    When the customer ask for the receipt
    Then the receipt displays the 'More than 10 fruits' discount
    And the receipt total is 575

  Scenario: having the "Local fruits" discount

    Given there is a local fruits discount
    And the basket contains:
      | Pommes  | 2 |
      | Fraises | 1 |
      | Poires  | 1 |
    When the customer ask for the receipt
    Then the receipt displays the 'Local fruits' discount
    And the receipt total is 160
