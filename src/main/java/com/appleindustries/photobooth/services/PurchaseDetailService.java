package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.PurchaseDetail;

/**
 * @author zane
 */
public interface PurchaseDetailService extends CRUDService<PurchaseDetail> {
    boolean isLucky(PurchaseDetail purchaseDetail);
}
