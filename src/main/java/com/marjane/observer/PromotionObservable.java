package com.marjane.observer;

import com.marjane.dtos.PromotionDTO;

public interface PromotionObservable {
    //void addObserver(PromotionObserver observer);
    // void removeObserver(PromotionObserver observer);
    void notifyObservers(PromotionDTO promotionDTO);
}
