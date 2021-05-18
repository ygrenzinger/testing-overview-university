package devoxx.university.cashregister.repository;

import devoxx.university.cashregister.model.ApplicableBasketDiscount;
import devoxx.university.cashregister.model.VolumeDiscount;
import devoxx.university.cashregister.model.discount.MoreThan10FruitsDiscountApplicable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class DiscountStore {

    private final Map<String, VolumeDiscount> discounts;

    public DiscountStore() {
        this.discounts = new TreeMap<>();
        this.discounts.put("Cerises", new VolumeDiscount(4));
        this.discounts.put("Bananes", new VolumeDiscount(2));
    }

    public Optional<VolumeDiscount> getVolumeDiscount(String fruit) {
        return Optional.ofNullable(discounts.get(fruit));
    }

    public List<ApplicableBasketDiscount> getBasketDiscount() {
        return List.of(
                new MoreThan10FruitsDiscountApplicable()
        );
    }
}
