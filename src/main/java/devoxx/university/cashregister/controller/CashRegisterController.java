package devoxx.university.cashregister.controller;

import devoxx.university.cashregister.model.BasketItem;
import devoxx.university.cashregister.model.Receipt;
import devoxx.university.cashregister.service.CashRegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CashRegisterController {

    private final CashRegisterService cashRegisterService;

    public CashRegisterController(CashRegisterService cashRegisterService) {
        this.cashRegisterService = cashRegisterService;
    }

    @PostMapping("/api/receipt")
    public Receipt getPrice(@RequestBody List<BasketItem> basketItems) {
        return this.cashRegisterService.editReceipt(basketItems);
    }

}
