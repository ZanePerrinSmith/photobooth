package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.Purchase;

/**
 * @author zane
 */
public interface PurchaseService extends CRUDService<Purchase> {
    Purchase getByCustomerId(Integer customerId);

}
