package devoxx.university.cashregister.testutils;

import devoxx.university.cashregister.domain.discount.ApplicableBasketDiscount;
import devoxx.university.cashregister.domain.discount.DiscountStore;
import devoxx.university.cashregister.domain.discount.VolumeDiscount;

import java.util.*;

public class DiscountStoreForTest implements DiscountStore {

    private final Map<String, VolumeDiscount> fruitDiscounts = new HashMap<>();
    private final List<ApplicableBasketDiscount> basketDiscounts = new ArrayList<>();

    @Override
    public Optional<VolumeDiscount> getVolumeDiscount(String fruit) {
        return Optional.ofNullable(fruitDiscounts.get(fruit));
    }

    @Override
    public List<ApplicableBasketDiscount> getBasketDiscount() {
        return basketDiscounts;
    }

    public void addVolumeDiscount(String fruit, VolumeDiscount discount) {
        fruitDiscounts.put(fruit, discount);
    }
    public void addBasketDiscount(ApplicableBasketDiscount basketDiscount) {
        this.basketDiscounts.add(basketDiscount);
    }

    public void clear() {
        fruitDiscounts.clear();
        basketDiscounts.clear();
    }
}
