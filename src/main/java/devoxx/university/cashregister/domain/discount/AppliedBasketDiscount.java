package devoxx.university.cashregister.domain.discount;

import java.util.Objects;

public class AppliedBasketDiscount {

    private final String name;
    private final long amount;

    public AppliedBasketDiscount(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppliedBasketDiscount that = (AppliedBasketDiscount) o;
        return amount == that.amount &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }

    @Override
    public String toString() {
        return "AppliedBasketDiscount{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
