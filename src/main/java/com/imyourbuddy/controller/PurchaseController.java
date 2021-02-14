package com.imyourbuddy.controller;

import com.imyourbuddy.entity.Purchase;
import com.imyourbuddy.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class PurchaseController {
    private final PurchaseService service;

    @Autowired
    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases() {
        return service.getAllPurchases();
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable(value = "id") int id) {
        Purchase purchase = service.getPurchaseById(id);
        return ResponseEntity.ok().body(purchase);
    }

    @DeleteMapping("/purchases/{id}")
    public void deletePurchaseById(@PathVariable(value = "id") int id) {
        service.deletePurchaseById(id);
    }

    @PostMapping(value = "/purchases")
    public Purchase savePurchase(@RequestBody Purchase purchase) {
        return service.savePurchase(purchase);
    }
}
