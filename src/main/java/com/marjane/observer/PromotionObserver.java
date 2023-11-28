package com.marjane.observer;

import com.marjane.dtos.PromotionDTO;

public interface PromotionObserver {
 void onPromotionStatusChange(PromotionDTO promotion);
}
