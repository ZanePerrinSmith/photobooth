package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.entities.PurchaseDetail;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import com.appleindustries.photobooth.repositories.PhotoPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zane
 */
@Service
@AllArgsConstructor
public class PrizeServiceImpl implements PrizeService {

    private static final int FIRST_PURCHASE_DETAIL = 0;
    private static final Integer PRIZE_QUANTITY = 1;
    private static final BigDecimal PRIZE_PRICE = BigDecimal.ZERO;


    @Autowired
    PhotoPackageService photoPackageService;

    @Autowired
    PhotoPackageRepository photoPackageRepository;

    @Override
    public List<PurchaseDetail> getPrizes(Purchase purchase) {
        List<PurchaseDetail> purchaseDetails = new ArrayList<>();
        PhotoPackageEnum photoPackagePurchased = purchase.getPurchaseDetails().get(FIRST_PURCHASE_DETAIL).getPhotoPackage().getType();
        for (PhotoPackageEnum photoPackageType : PhotoPackageEnum.values()) {
            if (photoPackageType != photoPackagePurchased) {
                PhotoPackage photoPackageToAdd = photoPackageRepository.findByType(photoPackageType);
                purchaseDetails.add(new PurchaseDetail(purchase, photoPackageToAdd, PRIZE_QUANTITY, PRIZE_PRICE));
            }
        }
        disableLuck(photoPackagePurchased);
        return purchaseDetails;
    }

    private void disableLuck(PhotoPackageEnum purchasedType) {
        PhotoPackage photoPackage = photoPackageRepository.findByType(purchasedType);
        photoPackage.setLuckEnabled(false);
        photoPackageService.saveOrUpdate(photoPackage);
    }
}
