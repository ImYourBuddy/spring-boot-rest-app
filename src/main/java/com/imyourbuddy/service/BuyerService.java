package com.imyourbuddy.service;

import com.imyourbuddy.entity.Buyer;
import com.imyourbuddy.exception.ResourceNotFoundException;
import com.imyourbuddy.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {
    private final BuyerRepository repository;

    @Autowired
    public BuyerService(BuyerRepository repository) {
        this.repository = repository;
    }

    public List<Buyer> getAllBuyers() {
        return repository.findAll();
    }

    public Buyer getBuyerById(int id) throws ResourceNotFoundException {
        Optional<Buyer> optional = repository.findById(id);
        return optional
                .orElseThrow(() -> new ResourceNotFoundException("Buyer with id = " + id + "not found"));
    }

    public Buyer deleteBuyerById(int id) throws ResourceNotFoundException {
        Buyer buyer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer with id = " + id + "not found"));
        repository.delete(buyer);
        return buyer;
    }

    public Buyer saveBuyer(Buyer buyer) {
        return repository.save(buyer);
    }

    public Buyer updateBuyerById(int id, Buyer updatedBuyer) throws ResourceNotFoundException {
        Buyer buyer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer with id = " + id + "not found"));
        buyer.setLastName(updatedBuyer.getLastName());
        buyer.setGeography(updatedBuyer.getGeography());
        buyer.setDiscount(updatedBuyer.getDiscount());
        return repository.save(buyer);
    }

    public Buyer partUpdateBuyerById(int id, Buyer updatedBuyer) throws ResourceNotFoundException {
        Buyer buyer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer with id = " + id + "not found"));
        if (updatedBuyer.getLastName() != null) {
            buyer.setLastName(updatedBuyer.getLastName());
        }
        if (updatedBuyer.getGeography() != null) {
            buyer.setGeography(updatedBuyer.getGeography());
        }
        if (updatedBuyer.getDiscount() != null) {
            buyer.setDiscount(updatedBuyer.getDiscount());
        }
        return repository.save(buyer);
    }

    public List<String> getAllDistinctDistricts() {
        return repository.findAllDistinctDistricts();
    }

    public List<String> getAllByNizhDist() {
        return repository.findAllByNizhDist();
    }
}
