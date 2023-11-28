package com.marjane.controllers;
import com.marjane.dtos.CashierDTO;
 import com.marjane.services.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cashiers")
public class CashierController {
    @Autowired
    private CashierService cashierService;

    @PostMapping
    public ResponseEntity<CashierDTO> addCashier(@RequestBody CashierDTO cashierDTO) {
        CashierDTO newCashier = cashierService.addCashier(cashierDTO);
        return new ResponseEntity<>(newCashier, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashierDTO> getCashierById(@PathVariable Long id) {
        CashierDTO cashier = cashierService.getCashierById(id);
        return cashier != null ? new ResponseEntity<>(cashier, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashierDTO> updateCashier(@PathVariable Long id, @RequestBody CashierDTO cashierDTO) {
        CashierDTO updatedCashier = cashierService.updateCashier(id, cashierDTO);
        return updatedCashier != null ? new ResponseEntity<>(updatedCashier, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCashier(@PathVariable Long id) {
        boolean deleted = cashierService.deleteCashier(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
