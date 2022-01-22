package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.Purchase;

/**
 * @author zane
 */
public interface PurchaseService extends CRUDService<Purchase> {
    Purchase getByCustomerId(Integer customerId);

    boolean isLucky(Purchase purchase);

    void givePrize(Purchase purchase);
}
