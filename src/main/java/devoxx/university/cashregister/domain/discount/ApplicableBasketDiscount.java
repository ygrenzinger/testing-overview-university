package devoxx.university.cashregister.domain.discount;

import devoxx.university.cashregister.domain.ReceiptItem;

import java.util.List;

public interface ApplicableBasketDiscount {

    String getName();

    long getAmount(List<ReceiptItem> receiptItems);

    boolean isApplicable(List<ReceiptItem> receiptItems);
}
