package com.marjane.services;
import com.marjane.dtos.CashierDTO;
import com.marjane.models.Cashier;
import com.marjane.repositories.CashierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CashierService {

    private final CashierRepository cashierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CashierService(CashierRepository cashierRepository, ModelMapper modelMapper) {
        this.cashierRepository = cashierRepository;
        this.modelMapper = modelMapper;
    }

    public CashierDTO addCashier(CashierDTO cashierDTO) {
        Cashier cashier = modelMapper.map(cashierDTO, Cashier.class);
        Cashier savedCashier = cashierRepository.save(cashier);
        return modelMapper.map(savedCashier, CashierDTO.class);
    }

    public CashierDTO getCashierById(Long id) {
        Optional<Cashier> optionalCashier = cashierRepository.findById(id);
        return optionalCashier.map(cashier -> modelMapper.map(cashier, CashierDTO.class)).orElse(null);
    }

    public CashierDTO updateCashier(Long id, CashierDTO cashierDTO) {
        Optional<Cashier> optionalCashier = cashierRepository.findById(id);
        if (optionalCashier.isPresent()) {
            Cashier cashier = modelMapper.map(cashierDTO, Cashier.class);
            cashier.setId(id); // Set the ID to the provided ID for update
            Cashier updatedCashier = cashierRepository.save(cashier);
            return modelMapper.map(updatedCashier, CashierDTO.class);
        }
        return null; // Or throw an exception if needed
    }

    public boolean deleteCashier(Long id) {
        if (cashierRepository.existsById(id)) {
            cashierRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
