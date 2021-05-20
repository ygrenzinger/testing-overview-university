package devoxx.university.cashregister.domain.discount;

import devoxx.university.cashregister.domain.ReceiptItem;

import java.util.List;

public class MoreThan4DifferentFruitsDiscountApplicable implements ApplicableBasketDiscount {

    private static final MoreThan4DifferentFruitsDiscountApplicable instance = new MoreThan4DifferentFruitsDiscountApplicable();

    private MoreThan4DifferentFruitsDiscountApplicable() { }

    public static MoreThan4DifferentFruitsDiscountApplicable get() {
        return instance;
    }

    @Override
    public String getName() {
        return "More than 4 different fruits";
    }

    @Override
    public long getAmount(List<ReceiptItem> receiptItems) {
        return 100;
    }

    @Override
    public boolean isApplicable(List<ReceiptItem> receiptItems) {
        return receiptItems.stream()
                .map(ReceiptItem::getFruit)
                .distinct()
                .count() > 4;
    }
}
