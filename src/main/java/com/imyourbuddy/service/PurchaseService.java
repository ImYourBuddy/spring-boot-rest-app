package com.imyourbuddy.service;

import com.imyourbuddy.entity.Purchase;
import com.imyourbuddy.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private final PurchaseRepository repository;

    @Autowired
    public PurchaseService(PurchaseRepository repository) {
        this.repository = repository;
    }

    public List<Purchase> getAllPurchases() {
        return repository.findAll();
    }

    public Purchase getPurchaseById(int id) {
        Optional<Purchase> optional = repository.findById(id);
        return optional.orElse(new Purchase());
    }

    public void deletePurchaseById(int id) {
        repository.deleteById(id);
    }

    public Purchase savePurchase(Purchase purchase) {
        return repository.save(purchase);
    }

}
