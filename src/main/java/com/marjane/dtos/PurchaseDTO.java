package com.marjane.dtos;

 import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PurchaseDTO {
    private Long id;
    private Long customerId;
    private List<Long> productIds;
}
