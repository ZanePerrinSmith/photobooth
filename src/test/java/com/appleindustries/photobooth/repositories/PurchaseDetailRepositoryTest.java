package com.appleindustries.photobooth.repositories;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.entities.PurchaseDetail;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author zane
 */
@DataJpaTest
class PurchaseDetailRepositoryTest {

    @Autowired
    PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    PhotoPackageRepository photoPackageRepository;

    @Test
    void findPurchasesInDateRangeHasContent() {
        List<PurchaseDetail> purchaseDetails = new ArrayList<>();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        ZoneOffset defaultZoneOffset = OffsetDateTime.now().getOffset();

        PhotoPackage photoPackage1 = new PhotoPackage();
        photoPackage1.setType(PhotoPackageEnum.PRINT);
        photoPackage1.setPrice(new BigDecimal((5)));
        photoPackageRepository.save(photoPackage1);
        PhotoPackage photoPackage2 = new PhotoPackage();
        photoPackage2.setType(PhotoPackageEnum.PANORAMA);
        photoPackage2.setPrice(new BigDecimal((5)));
        photoPackageRepository.save(photoPackage2);

        PurchaseDetail purchaseDetail1 = new PurchaseDetail();
        purchaseDetail1.setPhotoPackage(photoPackage1);
        purchaseDetail1.setQuantity(1);
        purchaseDetailRepository.save(purchaseDetail1);
        purchaseDetails.add(purchaseDetail1);

        PurchaseDetail purchaseDetail2 = new PurchaseDetail();
        purchaseDetail2.setPhotoPackage(photoPackage2);
        purchaseDetail2.setQuantity(1);
        purchaseDetailRepository.save(purchaseDetail2);
        purchaseDetails.add(purchaseDetail2);

        LocalDate today = LocalDate.now();
        Date beginDate = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
        Date endDate = Date.from(today.atTime(LocalTime.MAX).toInstant(defaultZoneOffset));

        List<PurchaseDetail> resultingPurchaseDetails = purchaseDetailRepository.findPurchasesInDateRange(beginDate, endDate);
        assertEquals(purchaseDetails, resultingPurchaseDetails);
    }

    @Test
    void findPurchasesInDateRangeNoContent() {
        List<PurchaseDetail> purchaseDetails = new ArrayList<>();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        ZoneOffset defaultZoneOffset = OffsetDateTime.now().getOffset();

        PhotoPackage photoPackage1 = new PhotoPackage();
        photoPackage1.setType(PhotoPackageEnum.PRINT);
        photoPackage1.setPrice(new BigDecimal((5)));
        photoPackageRepository.save(photoPackage1);
        PhotoPackage photoPackage2 = new PhotoPackage();
        photoPackage2.setType(PhotoPackageEnum.PANORAMA);
        photoPackage2.setPrice(new BigDecimal((5)));
        photoPackageRepository.save(photoPackage2);

        PurchaseDetail purchaseDetail1 = new PurchaseDetail();
        purchaseDetail1.setPhotoPackage(photoPackage1);
        purchaseDetail1.setQuantity(1);
        purchaseDetailRepository.save(purchaseDetail1);
        purchaseDetails.add(purchaseDetail1);

        PurchaseDetail purchaseDetail2 = new PurchaseDetail();
        purchaseDetail2.setPhotoPackage(photoPackage2);
        purchaseDetail2.setQuantity(1);
        purchaseDetailRepository.save(purchaseDetail2);
        purchaseDetails.add(purchaseDetail2);

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Date beginDate = Date.from(yesterday.atStartOfDay(defaultZoneId).toInstant());
        Date endDate = Date.from(yesterday.atTime(LocalTime.MAX).toInstant(defaultZoneOffset));

        List<PurchaseDetail> resultingPurchaseDetails = purchaseDetailRepository.findPurchasesInDateRange(beginDate, endDate);
        assertTrue(resultingPurchaseDetails.isEmpty());

    }
}