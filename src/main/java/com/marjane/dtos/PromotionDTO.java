package com.marjane.dtos;

import com.marjane.enumeration.PromotionStatus;
import lombok.Data;

import java.util.Date;

@Data
 public class PromotionDTO {
    private Long id;
    private Long categoryId;
    private double discountPercentage;
    private int loyaltyPoints;
    private Date validFrom;
    private Date validTo;
    private Long productId;
    private PromotionStatus status;

}
