package devoxx.university.cashregister.domain.fruit;

import devoxx.university.cashregister.domain.discount.DiscountStore;

public interface FruitPrice {

    long retrieveFruitPrice(String fruitName);

    default long getPriceWithDiscount(String fruitName, int number, DiscountStore discountStore) {
        long price = retrieveFruitPrice(fruitName);
        long discountAmount = discountStore.getVolumeDiscount(fruitName)
                .map(discount -> discount.getDiscountAmount(number, price))
                .orElse(0L);
        long total = number * price;
        return total - discountAmount;
    }
}
