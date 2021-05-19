package devoxx.university.cashregister.api;

import devoxx.university.cashregister.domain.BasketItem;
import devoxx.university.cashregister.domain.Receipt;
import devoxx.university.cashregister.domain.CashRegister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CashRegisterResource {

    private final CashRegister cashRegister;

    public CashRegisterResource(CashRegister cashRegister) {
        this.cashRegister = cashRegister;
    }

    @PostMapping("/api/receipt")
    public Receipt getPrice(@RequestBody List<BasketItem> basketItems) {
        return this.cashRegister.editReceipt(basketItems);
    }

}
