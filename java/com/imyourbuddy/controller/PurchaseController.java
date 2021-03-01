package com.imyourbuddy.controller;

import com.imyourbuddy.entity.Purchase;
import com.imyourbuddy.entity.search.*;
import com.imyourbuddy.exception.IllegalOperationException;
import com.imyourbuddy.exception.ResourceNotFoundException;
import com.imyourbuddy.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public Purchase getPurchaseById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        return service.getPurchaseById(id);
    }

    @DeleteMapping("/purchases/{id}")
    public void deletePurchaseById(@PathVariable(value = "id") int id) throws IllegalOperationException {
        service.deletePurchaseById(id);
    }

    @PostMapping(value = "/purchases")
    public Purchase savePurchase(@RequestBody @Valid Purchase purchase) throws ResourceNotFoundException, IllegalOperationException {
        return service.savePurchase(purchase);
    }

    @PutMapping("/purchases/{id}")
    public void updatePurchaseById(@PathVariable(value = "id") int id, @RequestBody Purchase purchase) throws IllegalOperationException {
        service.updatePurchaseById(id, purchase);
    }

    @PatchMapping("/purchases/{id}")
    public void partUpdatePurchaseById(@PathVariable(value = "id") int id, @RequestBody Purchase purchase) throws IllegalOperationException {
         service.partUpdatePurchaseById(id, purchase);
    }

    @GetMapping("/purchases/months")
    public List<PurchaseMonthSearchResult> getAllDistinctMonths() {
        return service.getAllDistinctMonths();
    }

    @GetMapping("/purchases/buyer-shop")
    public List<PurchaseBuyerShopResult> getBuyerAndShop() {
        return service.getBuyerAndShop();
    }

    @GetMapping("purchases/special")
    public List<SpecialPurchaseSearchResult> getSpecialPurchase() {
        return service.getSpecialPurchase();
    }

    @GetMapping("/purchases/expensive")
    public List<ExpensivePurchaseSearchResult> getExpensivePurchases() {
        return service.getExpensivePurchases();
    }

    @GetMapping("/purchases/dist")
    public List<PurchaseDistrictSearchResult> getPurchasesInDist() {
        return service.getPurchasesInDist();
    }
    @GetMapping("/purchases/not-avtoz")
    public List<PurchaseNotAvtozResult> getPurchasesNotInAvtoz() {
        return service.getPurchasesNotInAvtoz();
    }

    @GetMapping("/purchases/storage")
    public List<PurchaseInStorageResult> getPurchasesInStorage() {
        return service.getPurchasesInStorage();
    }
}
