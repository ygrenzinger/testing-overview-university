package pbt;

import devoxx.university.cashregister.domain.BasketItem;
import devoxx.university.cashregister.domain.CashRegister;
import devoxx.university.cashregister.domain.Receipt;
import devoxx.university.cashregister.domain.discount.LocalFruitsDiscount;
import devoxx.university.cashregister.domain.discount.MoreThan10FruitsDiscountApplicable;
import devoxx.university.cashregister.domain.discount.MoreThan4DifferentFruitsDiscountApplicable;
import devoxx.university.cashregister.domain.discount.VolumeDiscount;
import devoxx.university.cashregister.testutils.DiscountStoreForTest;
import devoxx.university.cashregister.testutils.FruitPriceForTest;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.From;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Tuple;
import net.jqwik.api.statistics.Statistics;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketPropertyTest {
    public static final String FRAISES = "Fraises";
    public static final String POMMES = "Pommes";
    public static final String POIRES = "Poires";

    private final FruitPriceForTest fruitPrice = new FruitPriceForTest();
    private final DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private final CashRegister cashRegister = new CashRegister(fruitPrice, discountStore);

    {
        this.fruitPrice.fruitPrice(FRAISES, 25L);
        this.fruitPrice.fruitPrice(POMMES, 50L);
        this.fruitPrice.fruitPrice(POIRES, 75L);

        this.discountStore.addVolumeDiscount(FRAISES, new VolumeDiscount(4));
        this.discountStore.addVolumeDiscount(POMMES, new VolumeDiscount(3));
        this.discountStore.addVolumeDiscount(POIRES, new VolumeDiscount(2));

        this.discountStore.addBasketDiscount(MoreThan10FruitsDiscountApplicable.get());
        this.discountStore.addBasketDiscount(MoreThan4DifferentFruitsDiscountApplicable.get());
        this.discountStore.addBasketDiscount(LocalFruitsDiscount.get());
    }

    Arbitrary<BasketItem> randomBasketItem() {
        return Combinators.combine(
                Arbitraries.of(POMMES, POIRES, FRAISES),
                Arbitraries.integers().between(1, 10)
        ).as(BasketItem::new);
    }

    @Provide("basket")
    Arbitrary<List<BasketItem>> randomBasket() {
        return randomBasketItem().list()
                .ofMinSize(1)
                .ofMaxSize(5)
                .uniqueElements(BasketItem::getName);
    }

    @Property(tries = 10000)
    public void allReceiptShouldBeSuperiorToZero(@ForAll @From("basket") List<BasketItem> basketItems) {
        // Statistics.collect(basketItems);
        Receipt receipt = this.cashRegister.editReceipt(basketItems);
        assertThat(receipt.getTotal()).isGreaterThan(0);
    }
}
