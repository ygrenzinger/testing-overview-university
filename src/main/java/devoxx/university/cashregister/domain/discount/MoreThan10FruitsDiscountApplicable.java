package devoxx.university.cashregister.domain.discount;

import devoxx.university.cashregister.domain.BasketItem;
import devoxx.university.cashregister.domain.ReceiptItem;

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
    public long getAmount(List<ReceiptItem> receiptItemss) {
        return 200;
    }

    @Override
    public boolean isApplicable(List<ReceiptItem> receiptItems) {
        return receiptItems.stream()
                .mapToLong(ReceiptItem::getQuantity)
                .sum() > 10;
    }
}
