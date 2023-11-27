package com.marjane.dtos;

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
    // Other relevant fields
}
