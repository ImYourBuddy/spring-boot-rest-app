package com.imyourbuddy.service;

import com.imyourbuddy.entity.Shop;
import com.imyourbuddy.exception.ResourceNotFoundException;
import com.imyourbuddy.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    private final ShopRepository repository;

    @Autowired
    public ShopService(ShopRepository repository) {
        this.repository = repository;
    }

    public List<Shop> getAllShops() {
        return repository.findAll();
    }

    public Shop getShopById(int id) throws ResourceNotFoundException {
        Optional<Shop> optional = repository.findById(id);
        return optional
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id = " + id + "not found"));
    }

    public Shop deleteShopById(int id) throws ResourceNotFoundException {
        Shop shop = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id = " + id + "not found"));
        repository.delete(shop);
        return shop;
    }

    public Shop saveShop(Shop shop) {
        return repository.save(shop);
    }

    public Shop updateShopById(int id, Shop updatedShop) throws ResourceNotFoundException {
        Shop shop = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id = " + id + "not found"));
        shop.setName(updatedShop.getName());
        shop.setGeography(updatedShop.getGeography());
        shop.setCommission(updatedShop.getCommission());
        return repository.save(shop);
    }

    public Shop partUpdateShopById(int id, Shop updatedShop) throws ResourceNotFoundException {
        Shop shop = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id = " + id + "not found"));
        if (updatedShop.getName() != null) {
            shop.setName(updatedShop.getName());
        }
        if (updatedShop.getGeography() != null) {
            shop.setGeography(updatedShop.getGeography());
        }
        if (updatedShop.getCommission() != null) {
            shop.setCommission(updatedShop.getCommission());
        }
        return repository.save(shop);
    }

    public List<String> getLocalShop() {
        return repository.findLocalShop();
    }
}
