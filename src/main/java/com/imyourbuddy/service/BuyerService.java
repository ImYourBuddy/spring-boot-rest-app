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
}
