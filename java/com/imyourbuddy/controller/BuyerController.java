package com.imyourbuddy.controller;


import com.imyourbuddy.entity.Buyer;
import com.imyourbuddy.entity.search.BuyerSearchResult;
import com.imyourbuddy.exception.ResourceNotFoundException;
import com.imyourbuddy.service.BuyerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public Buyer getBuyerById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
       return service.getBuyerById(id);
    }

    @DeleteMapping("/buyers/{id}")
    public Buyer deleteBuyerById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        return service.deleteBuyerById(id);
    }

    @PostMapping("/buyers")
    public Buyer saveBuyer(@RequestBody @Valid Buyer buyer) {
        return service.saveBuyer(buyer);
    }

    @PutMapping("/buyers/{id}")
    public Buyer updateBuyerById(@PathVariable(value = "id") int id, @RequestBody @Valid Buyer buyer) throws ResourceNotFoundException {
        return service.updateBuyerById(id, buyer);
    }

    @PatchMapping("/buyers/{id}")
    public Buyer partUpdateBuyerById(@PathVariable(value = "id") int id, @RequestBody @Valid Buyer buyer) throws ResourceNotFoundException {
        return service.partUpdateBuyerById(id, buyer);
    }

    @GetMapping("/buyers/districts")
    public List<BuyerSearchResult> getAllDistinctDistricts() {
        return service.getAllDistinctDistricts();
    }

    @GetMapping("/buyers/nizh")
    public List<Map<String, Object>> getAllByNizhDist() {
        return service.getAllByNizhDist();
    }

}
