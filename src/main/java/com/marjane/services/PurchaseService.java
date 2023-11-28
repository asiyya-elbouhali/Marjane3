package com.marjane.services;

import com.marjane.dtos.PurchaseDTO;
import com.marjane.models.Purchase;
import com.marjane.repositories.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, ModelMapper modelMapper) {
        this.purchaseRepository = purchaseRepository;
        this.modelMapper = modelMapper;
    }

    public PurchaseDTO createPurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = modelMapper.map(purchaseDTO, Purchase.class);
        Purchase savedPurchase = purchaseRepository.save(purchase);
        return modelMapper.map(savedPurchase, PurchaseDTO.class);
    }

    public PurchaseDTO getPurchaseById(Long id) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        return optionalPurchase.map(purchase -> modelMapper.map(purchase, PurchaseDTO.class)).orElse(null);
    }

    public PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        if (optionalPurchase.isPresent()) {
            Purchase existingPurchase = optionalPurchase.get();
            // Update the existingPurchase fields with values from purchaseDTO
            // Use setters or manual assignment
            Purchase updatedPurchase = purchaseRepository.save(existingPurchase);
            return modelMapper.map(updatedPurchase, PurchaseDTO.class);
        }
        return null;
    }

    public boolean deletePurchase(Long id) {
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}