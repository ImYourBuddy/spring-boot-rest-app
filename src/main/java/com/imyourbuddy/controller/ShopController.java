package com.imyourbuddy.controller;



import com.imyourbuddy.entity.Shop;
import com.imyourbuddy.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ShopController {
    private final ShopService service;

    @Autowired
    public ShopController(ShopService service) {
        this.service = service;
    }

    @GetMapping("/shops")
    public List<Shop> getAllShops() {
        return service.getAllShops();
    }

    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable(value = "id") int id) {
        Shop shop = service.getShopById(id);
        return ResponseEntity.ok().body(shop);
    }

    @DeleteMapping("/shops")
    public void deleteShopById(@PathVariable(value = "id") int id) {
        service.deleteShopById(id);
    }

    @PostMapping("/shops")
    public Shop saveShop(@RequestBody Shop shop) {
        return service.saveShop(shop);
    }
}
