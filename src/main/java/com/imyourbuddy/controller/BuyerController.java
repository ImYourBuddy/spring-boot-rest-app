package com.imyourbuddy.controller;

import com.imyourbuddy.entity.Buyer;
import com.imyourbuddy.service.BuyerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class BuyerController {
    private final BuyerService service;

    public BuyerController(BuyerService service) {
        this.service = service;
    }

    @GetMapping("/buyers")
    public List<Buyer> getAllBuyers() {
        return service.getAllBuyers();
    }

    @GetMapping("/buyers/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable(value = "id") int id) {
        Buyer buyer = service.getBuyerById(id);
        return ResponseEntity.ok().body(buyer);
    }

    @DeleteMapping("/buyers/{id}")
    public void deleteBuyerById(@PathVariable(value = "id") int id) {
        service.deleteBuyerById(id);
    }

    @PostMapping("/buyers")
    public Buyer saveBuyer(@RequestBody Buyer buyer) {
        return service.saveBuyer(buyer);
    }
}
