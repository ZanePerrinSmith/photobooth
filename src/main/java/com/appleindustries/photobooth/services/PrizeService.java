package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.entities.PurchaseDetail;

import java.util.List;

/**
 * @author zane
 */
public interface PrizeService {
    List<PurchaseDetail> getPrizes(Purchase purchase);
}
