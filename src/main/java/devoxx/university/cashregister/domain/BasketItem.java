package devoxx.university.cashregister.domain;

public class BasketItem {

    private final String name;
    private final int quantity;

    public BasketItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
