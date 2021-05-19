package devoxx.university.cashregister.domain.discount;

import devoxx.university.cashregister.domain.BasketItem;

import java.util.List;

public class MoreThan10FruitsDiscountApplicable implements ApplicableBasketDiscount {

    private static final MoreThan10FruitsDiscountApplicable instance = new MoreThan10FruitsDiscountApplicable();

    private MoreThan10FruitsDiscountApplicable() { }

    public static MoreThan10FruitsDiscountApplicable get() {
        return instance;
    }

    @Override
    public String getName() {
        return "More than 10 fruits";
    }

    @Override
    public long getAmount(List<BasketItem> fruits) {
        return 200;
    }

    @Override
    public boolean isApplicable(List<BasketItem> fruits) {
        return fruits.stream()
                .mapToLong(BasketItem::getQuantity)
                .sum() > 10;
    }
}
