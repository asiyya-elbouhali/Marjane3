package com.marjane.observer;

import com.marjane.dtos.PromotionDTO;

public interface Observer {
    void update(PromotionDTO promotionDTO);
}
