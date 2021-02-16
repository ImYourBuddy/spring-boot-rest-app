package com.imyourbuddy.controller;



import com.imyourbuddy.entity.Shop;
import com.imyourbuddy.exception.ResourceNotFoundException;
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
    public Shop getShopById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        return service.getShopById(id);
    }

    @DeleteMapping("/shops/{id}")
    public Shop deleteShopById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        return service.deleteShopById(id);
    }

    @PostMapping("/shops")
    public Shop saveShop(@RequestBody Shop shop) {
        return service.saveShop(shop);
    }

    @PutMapping("/shops/{id}")
    public Shop updateShopById(@PathVariable(value = "id") int id, @RequestBody Shop updatedShop) throws ResourceNotFoundException {
        return service.updateShopById(id, updatedShop);
    }

    @PatchMapping("/shops/{id}")
    public Shop partUpdateShopById(@PathVariable(value = "id") int id, @RequestBody Shop shop) throws ResourceNotFoundException {
        return service.partUpdateShopById(id, shop);
    }

    @GetMapping("/shops/local")
    public List<String> getLocalShop() {
        return service.getLocalShop();
    }

}
