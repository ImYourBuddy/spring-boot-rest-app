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

    public Purchase updatePurchaseById(int id, Purchase updatedPurchase) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase with id = " + id + "not found"));
        purchase.setDate(updatedPurchase.getDate());
        purchase.setSeller(updatedPurchase.getSeller());
        purchase.setBuyer(updatedPurchase.getBuyer());
        purchase.setBook(updatedPurchase.getBook());
        purchase.setQuantity(updatedPurchase.getQuantity());
        purchase.setTotalPrice(updatedPurchase.getTotalPrice());
        return repository.save(purchase);
    }

    public Purchase partUpdatePurchaseById(int id, Purchase updatedPurchase) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase with id = " + id + "not found"));
        if (updatedPurchase.getDate() != null) {
            purchase.setDate(updatedPurchase.getDate());
        }
        if (updatedPurchase.getSeller() != 0) {
            purchase.setSeller(updatedPurchase.getSeller());
        }
        if (updatedPurchase.getBuyer() != 0) {
            purchase.setBuyer(updatedPurchase.getBuyer());
        }
        if (updatedPurchase.getBook() != 0) {
            purchase.setBook(updatedPurchase.getBook());
        }
        if (updatedPurchase.getQuantity() != 0) {
            purchase.setQuantity(updatedPurchase.getQuantity());
        }
        if (updatedPurchase.getTotalPrice() != null) {
            purchase.setTotalPrice(updatedPurchase.getTotalPrice());
        }
        return repository.save(purchase);
    }

    public List<Integer> getAllDistinctMonths() {
        return repository.findAllDistinctMonths();
    }

    public List<String> getBuyerAndShop() {
        return repository.findBuyerAndShop();
    }

    public List<String> getSpecialPurchase() {
        return repository.findSpecialPurchase();
    }

    public List<String> getExpensivePurchases() {
        return repository.findExpensivePurchases();
    }

    public List<String> getPurchasesInDist() {
        return repository.findPurchasesInDist();
    }

    public List<String> getPurchasesNotInAvtoz() {
        return repository.findPurchasesNotInAvtoz();
    }

    public List<String> getPurchasesInStorage() {
        return repository.findPurchasesInStorage();
    }
}
