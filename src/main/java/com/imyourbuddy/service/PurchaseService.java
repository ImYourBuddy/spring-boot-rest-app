package com.imyourbuddy.service;

import com.imyourbuddy.entity.Purchase;
import com.imyourbuddy.exception.ResourceNotFoundException;
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

    public Purchase getPurchaseById(int id) throws ResourceNotFoundException {
        Optional<Purchase> optional = repository.findById(id);
        return optional
                .orElseThrow(() -> new ResourceNotFoundException("Purchase with id = " + id + "not found"));
    }

    public Purchase deletePurchaseById(int id) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase with id = " + id + "not found"));
        repository.delete(purchase);
        return purchase;
    }

    public Purchase savePurchase(Purchase purchase) {
        return repository.save(purchase);
    }

}
