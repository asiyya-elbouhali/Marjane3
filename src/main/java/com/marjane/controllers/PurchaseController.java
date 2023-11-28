package com.marjane.controllers;

import com.marjane.dtos.PurchaseDTO;
 import com.marjane.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseDTO> addPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        PurchaseDTO newPurchase = purchaseService.createPurchase(purchaseDTO);
        return new ResponseEntity<>(newPurchase, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable Long id) {
        PurchaseDTO purchase = purchaseService.getPurchaseById(id);
        return purchase != null ? new ResponseEntity<>(purchase, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDTO> updatePurchase(@PathVariable Long id, @RequestBody PurchaseDTO purchaseDTO) {
        PurchaseDTO updatedPurchase = purchaseService.updatePurchase(id, purchaseDTO);
        return updatedPurchase != null ? new ResponseEntity<>(updatedPurchase, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        boolean deleted = purchaseService.deletePurchase(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}