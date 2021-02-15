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
}
