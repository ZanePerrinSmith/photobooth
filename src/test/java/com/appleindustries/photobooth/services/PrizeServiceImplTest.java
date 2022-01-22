package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.entities.PurchaseDetail;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import com.appleindustries.photobooth.repositories.PhotoPackageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author zane
 */
class PrizeServiceImplTest {

    @Mock
    private PhotoPackageRepository photoPackageRepository;

    @Mock
    private PhotoPackageService photoPackageService;

    @InjectMocks
    private PrizeServiceImpl prizeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPrizes() {
        int expectedResultSize = PhotoPackageEnum.values().length - 1;

        PhotoPackage photoPackage = new PhotoPackage();
        photoPackage.setType(PhotoPackageEnum.PRINT);
        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setPhotoPackage(photoPackage);
        Purchase purchase = new Purchase();
        purchase.addPurchaseDetail(purchaseDetail);

        when(photoPackageRepository.findByType(any(PhotoPackageEnum.class))).thenReturn(new PhotoPackage());
        when(photoPackageService.saveOrUpdate(any(PhotoPackage.class))).thenReturn(new PhotoPackage());

        assertEquals(expectedResultSize, prizeService.getPrizes(purchase).size());
    }
}