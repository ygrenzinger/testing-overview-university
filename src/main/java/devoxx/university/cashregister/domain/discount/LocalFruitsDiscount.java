package devoxx.university.cashregister.domain.discount;

import devoxx.university.cashregister.domain.ReceiptItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class LocalFruitsDiscount implements ApplicableBasketDiscount {
    private static final List<String> LOCAL_FRUITS = Arrays.asList("Pommes", "Poires", "Fraises");

    private static final LocalFruitsDiscount instance = new LocalFruitsDiscount();

    private LocalFruitsDiscount() { }

    public static LocalFruitsDiscount get() {
        return instance;
    }

    @Override
    public String getName() {
        return "Local fruits";
    }

    // tag::rules[]
    @Override
    public long getAmount(List<ReceiptItem> receiptItems) { //<2>
        var total = receiptItems.stream().mapToLong(ReceiptItem::getTotal).sum();
        return (total * 20) / 100;
    }

    @Override
    public boolean isApplicable(List<ReceiptItem> receiptItems) { //<1>
        return receiptItems.stream().map(ReceiptItem::getFruit).allMatch(LOCAL_FRUITS::contains);
    }
    // end::rules[]
}
