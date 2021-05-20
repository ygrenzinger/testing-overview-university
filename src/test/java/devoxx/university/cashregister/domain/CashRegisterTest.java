package devoxx.university.cashregister.domain;

import devoxx.university.cashregister.domain.discount.*;
import devoxx.university.cashregister.testutils.DiscountStoreForTest;
import devoxx.university.cashregister.testutils.FruitPriceForTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CashRegisterTest {

    public static final String POMMES = "Pommes";
    public static final String POIRES = "Poire";
    public static final String FRAISES = "Fraises";
    public static final String BANANES = "Bananes";
    public static final String ANANAS = "Ananas";
    private final FruitPriceForTest fruitPrice = new FruitPriceForTest();
    private final DiscountStoreForTest discountStore = new DiscountStoreForTest();

    CashRegister cashRegister = new CashRegister(fruitPrice, discountStore);

    @BeforeEach
    public void beforeEach() {
        fruitPrice.clear();
        fruitPrice.fruitPrice(FRAISES, 25);
        fruitPrice.fruitPrice(POMMES, 50);
        fruitPrice.fruitPrice(POIRES, 75);
        fruitPrice.fruitPrice(BANANES, 125);
        fruitPrice.fruitPrice(ANANAS, 200);

        discountStore.clear();
    }

    @Test
    public void should_return_an_empty_receipt_if_no_fruit() {
        var receipt = cashRegister.editReceipt(Collections.emptyList());
        assertThat(receipt.getItems()).isEmpty();
        assertThat(receipt.getTotal()).isZero();
    }

    @Test
    void should_return_the_price_of_one_fruit_in_the_basket() {
        var basket = List.of(new BasketItem(POMMES, 1));

        var receipt = cashRegister.editReceipt(basket);

        assertThat(receipt.getItems()).containsExactly(new ReceiptItem(POMMES, 1, 50));
        assertThat(receipt.getTotal()).isEqualTo(50);
    }

    @Test
    void should_return_the_price_of_multiple_times_the_fruit_in_the_basket() {
        var basket = List.of(new BasketItem(POMMES, 2));

        var receipt = cashRegister.editReceipt(basket);

        assertThat(receipt.getItems()).containsExactly(new ReceiptItem(POMMES, 2, 100));
        assertThat(receipt.getTotal()).isEqualTo(100);
    }

    @Test
    void should_return_the_price_of_different_fruits() {
        List<BasketItem> fruits = List.of(
                new BasketItem(POMMES, 1),
                new BasketItem(BANANES, 1)
        );

        var receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getItems()).containsExactly(
                new ReceiptItem(POMMES, 1, 50),
                new ReceiptItem(BANANES, 1, 125)
        );
        assertThat(receipt.getTotal()).isEqualTo(175);
    }

    @Test
    void should_return_the_price_with_volume_discounts_applied() {
        discountStore.addVolumeDiscount(POMMES, new VolumeDiscount(2));
        discountStore.addVolumeDiscount(BANANES, new VolumeDiscount(3));
        List<BasketItem> fruits = List.of(
                new BasketItem(POMMES, 2),
                new BasketItem(BANANES, 3)
        );

        var receipt = cashRegister.editReceipt(fruits);


        assertThat(receipt.getItems()).containsExactly(
                new ReceiptItem(POMMES, 2, 50),
                new ReceiptItem(BANANES, 3, 250)
        );
        assertThat(receipt.getTotal()).isEqualTo(300);
    }

    @Test
    void should_return_the_price_with_basket_discount_applied_when_more_than_10_fruits() {
        discountStore.addBasketDiscount(MoreThan10FruitsDiscountApplicable.get());
        var basket = List.of(new BasketItem(POMMES, 11));

        var receipt = cashRegister.editReceipt(basket);

        assertThat(receipt.getItems()).containsExactly(new ReceiptItem(POMMES, 11, 550));
        assertThat(receipt.getBasketDiscounts()).containsExactly(new AppliedBasketDiscount("More than 10 fruits", 200));
        assertThat(receipt.getTotal()).isEqualTo(350);
    }

    @Test
    void should_return_the_price_with_basket_discount_applied_when_more_than_5_different_fruits() {
        discountStore.addBasketDiscount(MoreThan4DifferentFruitsDiscountApplicable.get());
        var basket = List.of(
                new BasketItem(POMMES, 1),
                new BasketItem(POIRES, 1),
                new BasketItem(FRAISES, 1),
                new BasketItem(BANANES, 1),
                new BasketItem(ANANAS, 1)
        );

        var receipt = cashRegister.editReceipt(basket);

        assertThat(receipt.getBasketDiscounts()).containsExactly(new AppliedBasketDiscount("More than 4 different fruits", 100));
        assertThat(receipt.getTotal()).isEqualTo(375);
    }

    @Test
    void should_return_the_price_without_any_discount_applied() {
        discountStore.addBasketDiscount(MoreThan10FruitsDiscountApplicable.get());
        discountStore.addBasketDiscount(MoreThan4DifferentFruitsDiscountApplicable.get());
        discountStore.addBasketDiscount(LocalFruitsDiscount.get());
        var basket = List.of(
                new BasketItem(ANANAS, 1)
        );

        var receipt = cashRegister.editReceipt(basket);

        assertThat(receipt.getBasketDiscounts()).isEmpty();
        assertThat(receipt.getTotal()).isEqualTo(200);
    }

}
