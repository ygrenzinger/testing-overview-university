package devoxx.university.cashregister.testutils;

import devoxx.university.cashregister.domain.fruit.FruitPrice;

import java.util.HashMap;
import java.util.Map;

public class FruitPriceForTest implements FruitPrice {

    private final Map<String, Long> prices = new HashMap<>();

    @Override
    public long retrieveFruitPrice(String fruitName) {
        return prices.get(fruitName);
    }

    public void fruitPrice(String fruit, long price) {
        this.prices.put(fruit, price);
    }

    public void clear() {
        prices.clear();
    }
}
