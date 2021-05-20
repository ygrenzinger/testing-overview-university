package bdd.fixtures;

import devoxx.university.cashregister.domain.BasketItem;
import devoxx.university.cashregister.domain.CashRegister;
import devoxx.university.cashregister.domain.Receipt;
import devoxx.university.cashregister.domain.ReceiptItem;
import devoxx.university.cashregister.domain.discount.*;
import devoxx.university.cashregister.testutils.DiscountStoreForTest;
import devoxx.university.cashregister.testutils.FruitPriceForTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CashRegisterSteps {

    private final FruitPriceForTest fruitPrice = new FruitPriceForTest();
    private final DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private final CashRegister cashRegister = new CashRegister(fruitPrice, discountStore);

    private final List<BasketItem> basketItems = new ArrayList<>();
    private Receipt receipt = null;

    @Given("the price of a {word} is {long}")
    public void fruitPrice(String fruit, long amount) {
        fruitPrice.fruitPrice(fruit, amount);
    }

    @Given("there is a free unit every {long} {word}")
    public void thereIsAFreeUnitEvery(long threshold, String fruit) {
        discountStore.addVolumeDiscount(fruit, new VolumeDiscount(threshold));
    }

    @Given("there is a more than 10 fruits discount")
    public void moreThan10FruitsDiscountApplicable() {
        discountStore.addBasketDiscount(MoreThan10FruitsDiscountApplicable.get());
    }

    @Given("there is a more than 4 different fruits discount")
    public void moreThan4DifferentFruitsDiscountApplicable() {
        discountStore.addBasketDiscount(MoreThan4DifferentFruitsDiscountApplicable.get());
    }

    @Given("there is a local fruits discount")
    public void localFruitsDiscountApplicable() {
        discountStore.addBasketDiscount(LocalFruitsDiscount.get());
    }

    @Given("the basket is empty")
    public void theBasketIsEmpty() {
        basketItems.clear();
    }

    @Given("the basket contains {int} {word}")
    public void theBasketContains(int number, String fruit) {
        basketItems.add(new BasketItem(fruit, number));
    }

    @When("the customer ask for the receipt")
    public void theCustomerAskForTheReceipt() {
        receipt = cashRegister.editReceipt(basketItems);
    }

    @Then("the receipt displays the {string} discount")
    public void theReceiptDisplaysTheMoreThanFruitsDiscount(String discountName) {
        assertThat(receipt.getBasketDiscounts())
                .extracting(AppliedBasketDiscount::getName)
                .contains(discountName);
    }

    @Then("the receipt total is {int}")
    public void theReceiptTotalIs(long amount) {
        assertThat(receipt.getTotal()).isEqualTo(amount);
    }

    @Then("the item list is empty")
    public void theItemListIsEmpty() {
        assertThat(receipt.getItems()).isEmpty();
    }

    @Then("the receipt display the price of {int} for {int} {word}")
    public void theReceiptDisplayThePriceOfForPommes(int price, int quantity, String fruit) {
        assertThat(receipt.getItems())
                .contains(new ReceiptItem(fruit, quantity, price));
    }

    @Given("the price of fruits are:")
    public void the_price_of_fruits_are(DataTable dataTable) {
        dataTable.cells().forEach(columns ->
                fruitPrice.fruitPrice(columns.get(0), Long.parseLong(columns.get(1))));
    }

    @And("the basket contains:")
    public void theBasketContains(DataTable dataTable) {
        dataTable.cells().forEach(columns ->
                basketItems.add(new BasketItem(columns.get(0), Integer.parseInt(columns.get(1)))));

    }
}

