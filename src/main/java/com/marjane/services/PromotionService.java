package com.marjane.services;

import com.marjane.dtos.PromotionDTO;
import com.marjane.enumeration.PromotionStatus;
import com.marjane.models.Promotion;
import com.marjane.repositories.CategoryRepository;
import com.marjane.repositories.ProductRepository;
import com.marjane.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository,
                            CategoryRepository categoryRepository,
                            ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public PromotionDTO addPromotion(PromotionDTO promotionDTO) {
         Promotion promotion = mapPromotionDTOToEntity(promotionDTO);

         promotion.setStatus(PromotionStatus.PENDING);

         Promotion savedPromotion = promotionRepository.save(promotion);

         return mapPromotionEntityToDTO(savedPromotion);
    }

    public List<PromotionDTO> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.stream()
                .map(this::mapPromotionEntityToDTO)
                .collect(Collectors.toList());
    }

    public PromotionDTO getPromotionById(Long promotionId) {
        Optional<Promotion> optionalPromotion = promotionRepository.findById(promotionId);
        return optionalPromotion.map(this::mapPromotionEntityToDTO).orElse(null);
    }

    public void deletePromotion(Long promotionId) {
        promotionRepository.deleteById(promotionId);
    }

    private Promotion mapPromotionDTOToEntity(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        promotion.setId(promotionDTO.getId());
        promotion.setDiscountPercentage(promotionDTO.getDiscountPercentage());
        promotion.setLoyaltyPoints(promotionDTO.getLoyaltyPoints());
        promotion.setValidFrom(promotionDTO.getValidFrom());
        promotion.setValidTo(promotionDTO.getValidTo());

        if (promotionDTO.getCategoryId() != null) {
            promotion.setCategory(categoryRepository.findById(promotionDTO.getCategoryId()).orElse(null));
        }

        if (promotionDTO.getProductId() != null) {
            promotion.setProduct(productRepository.findById(promotionDTO.getProductId()).orElse(null));
        }

        return promotion;
    }

    private PromotionDTO mapPromotionEntityToDTO(Promotion promotion) {
        PromotionDTO promotionDTO = new PromotionDTO();
        promotionDTO.setId(promotion.getId());
        promotionDTO.setDiscountPercentage(promotion.getDiscountPercentage());
        promotionDTO.setLoyaltyPoints(promotion.getLoyaltyPoints());
        promotionDTO.setValidFrom(promotion.getValidFrom());
        promotionDTO.setValidTo(promotion.getValidTo());

        if (promotion.getCategory() != null) {
            promotionDTO.setCategoryId(promotion.getCategory().getId());
        }

        if (promotion.getProduct() != null) {
            promotionDTO.setProductId(promotion.getProduct().getId());
        }

        return promotionDTO;
    }
}
