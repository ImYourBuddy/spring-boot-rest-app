package com.imyourbuddy.service;

import com.imyourbuddy.entity.Buyer;
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

    public Buyer getBuyerById(int id) {
        Optional<Buyer> optional = repository.findById(id);
        return optional.orElse(new Buyer());
    }

    public void deleteBuyerById(int id) {
        repository.deleteById(id);
    }

    public Buyer saveBuyer(Buyer buyer) {
        return repository.save(buyer);
    }
}
