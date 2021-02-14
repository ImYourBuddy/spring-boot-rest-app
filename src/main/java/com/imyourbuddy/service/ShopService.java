package com.imyourbuddy.service;

import com.imyourbuddy.entity.Shop;
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

    public Shop getShopById(int id) {
        Optional<Shop> optional = repository.findById(id);
        return optional.orElse(new Shop());
    }

    public void deleteShopById(int id) {
        repository.deleteById(id);
    }

    public Shop saveShop(Shop shop) {
        return repository.save(shop);
    }
}
