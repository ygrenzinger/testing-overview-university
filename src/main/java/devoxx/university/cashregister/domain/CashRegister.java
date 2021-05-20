package devoxx.university.cashregister.domain;

import devoxx.university.cashregister.domain.discount.AppliedBasketDiscount;
import devoxx.university.cashregister.domain.discount.DiscountStore;
import devoxx.university.cashregister.domain.fruit.FruitPrice;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CashRegister {

    private final FruitPrice fruitPrice;
    private final DiscountStore discountStore;

    public CashRegister(FruitPrice fruitPrice, DiscountStore discountStore) {
        this.fruitPrice = fruitPrice;
        this.discountStore = discountStore;
    }

    public Receipt editReceipt(List<BasketItem> basketItem) {
        var receiptItems = getPrices(basketItem);
        var basketDiscounts = getApplicableBasketDiscount(receiptItems);
        long total = sumFruits(receiptItems) - getTotalDiscount(basketDiscounts);
        return new Receipt(receiptItems, basketDiscounts, total);
    }

    private long getTotalDiscount(List<AppliedBasketDiscount> basketDiscounts) {
        return basketDiscounts.stream()
                .mapToLong(AppliedBasketDiscount::getAmount)
                .sum();
    }

    private List<ReceiptItem> getPrices(List<BasketItem> basketItem) {
        return basketItem.stream()
                .map(this::createReceiptItem)
                .collect(toList());
    }

    private long sumFruits(List<ReceiptItem> receiptItems) {
        return receiptItems.stream()
                .mapToLong(ReceiptItem::getTotal)
                .sum();
    }

    private List<AppliedBasketDiscount> getApplicableBasketDiscount(List<ReceiptItem> receiptItems) {
        return discountStore.getBasketDiscount().stream()
                    .filter(discount -> discount.isApplicable(receiptItems))
                    .map(applicable -> new AppliedBasketDiscount(applicable.getName(), applicable.getAmount(receiptItems)))
                    .collect(toList());
    }

    private ReceiptItem createReceiptItem(BasketItem item) {
        int number = item.getQuantity();
        String fruit = item.getName();
        long total = fruitPrice.getPriceWithDiscount(fruit, number, discountStore);
        return new ReceiptItem(fruit, number, total);
    }


}
