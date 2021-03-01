package com.imyourbuddy.service;

import com.imyourbuddy.entity.Book;
import com.imyourbuddy.entity.Buyer;
import com.imyourbuddy.entity.Purchase;
import com.imyourbuddy.entity.Shop;
import com.imyourbuddy.entity.search.*;
import com.imyourbuddy.exception.IllegalOperationException;
import com.imyourbuddy.exception.ResourceNotFoundException;
import com.imyourbuddy.repository.BookRepository;
import com.imyourbuddy.repository.BuyerRepository;
import com.imyourbuddy.repository.PurchaseRepository;
import com.imyourbuddy.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseService {
    private final PurchaseRepository repository;

    private final BookRepository bookRepository;

    private final BuyerRepository buyerRepository;

    private final ShopRepository shopRepository;

    @Autowired
    public PurchaseService(PurchaseRepository repository, BookRepository bookRepository, BuyerRepository buyerRepository, ShopRepository shopRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.buyerRepository = buyerRepository;
        this.shopRepository = shopRepository;
    }

    public List<Purchase> getAllPurchases() {
        return repository.findAll();
    }

    public Purchase getPurchaseById(int id) throws ResourceNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase with id = " + id + " not found"));
    }

    public void deletePurchaseById(int id) throws IllegalOperationException {
        throw new IllegalOperationException("You can't delete a purchase record");
    }

    public Purchase savePurchase(Purchase purchase) throws ResourceNotFoundException, IllegalOperationException {
        Book book = bookRepository.findById(purchase.getBook())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = " + purchase.getBook() + " not found"));
        if (book.isDeleted()) {
            throw new IllegalOperationException("Book with id = " + purchase.getBook() + " is not available for purchase");
        }
        Shop shop = shopRepository.findById(purchase.getSeller())
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id = " + purchase.getSeller() + " not found"));
        if (shop.isDeleted()) {
            throw new IllegalOperationException("Shop with id = " + purchase.getSeller() + " is closed");
        }
        Buyer buyer = buyerRepository.findById(purchase.getBuyer())
                .orElseThrow(() -> new ResourceNotFoundException("Buyer with id = " + purchase.getBuyer() + " not found"));
        if (buyer.isDeleted()) {
            throw new IllegalOperationException("Buyer with id = " + purchase.getBuyer() + " was deleted");
        }
        return repository.save(purchase);
    }

    public void updatePurchaseById(int id, Purchase updatedPurchase) throws IllegalOperationException {
        throw new IllegalOperationException("You can't update a purchase record");
    }

    public void partUpdatePurchaseById(int id, Purchase updatedPurchase) throws IllegalOperationException {
        throw new IllegalOperationException("You can't update a purchase record");
    }

    public List<PurchaseMonthSearchResult> getAllDistinctMonths() {
        return repository.findAllDistinctMonths();
    }

    public List<PurchaseBuyerShopResult> getBuyerAndShop() {
        return repository.findBuyerAndShop();
    }

    public List<SpecialPurchaseSearchResult> getSpecialPurchase() {
        return repository.findSpecialPurchase();
    }

    public List<ExpensivePurchaseSearchResult> getExpensivePurchases() {
        return repository.findExpensivePurchases();
    }

    public List<PurchaseDistrictSearchResult> getPurchasesInDist() {
        return repository.findPurchasesInDist();
    }

    public List<PurchaseNotAvtozResult> getPurchasesNotInAvtoz() {
        return repository.findPurchasesNotInAvtoz();
    }

    public List<PurchaseInStorageResult> getPurchasesInStorage() {
        return repository.findPurchasesInStorage();

    }
}
