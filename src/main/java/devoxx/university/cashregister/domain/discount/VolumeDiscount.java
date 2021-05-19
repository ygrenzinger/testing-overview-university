package devoxx.university.cashregister.domain.discount;

public class VolumeDiscount {
    private final long threshold;

    public VolumeDiscount(long threshold) {
        this.threshold = threshold;
    }

    public long getDiscountAmount(long numberOfFruit, long fruitPrice) {
        long numberOfDiscountApplication = numberOfFruit / threshold;
        return numberOfDiscountApplication * fruitPrice;
    }
}
