package com.marjane.controllers;

import com.marjane.dtos.PromotionDTO;
import com.marjane.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping
    public PromotionDTO addPromotion(@RequestBody PromotionDTO promotionDTO) {
        return promotionService.addPromotion(promotionDTO);
    }

    @GetMapping
    public List<PromotionDTO> getAllPromotions() {
        return promotionService.getAllPromotions();
    }

    @GetMapping("/{id}")
    public PromotionDTO getPromotionById(@PathVariable("id") Long id) {
        return promotionService.getPromotionById(id);
    }

    @PutMapping("/{id}")
    public PromotionDTO updatePromotion(@PathVariable("id") Long id, @RequestBody PromotionDTO promotionDTO) {
        // Set the ID of the promotionDTO to match the path variable ID
        promotionDTO.setId(id);
        return promotionService.addPromotion(promotionDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePromotion(@PathVariable("id") Long id) {
        promotionService.deletePromotion(id);
    }
}
