package devoxx.university.cashregister.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FRUIT")
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private long price;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return id == fruit.id &&
                price == fruit.price &&
                Objects.equals(name, fruit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
