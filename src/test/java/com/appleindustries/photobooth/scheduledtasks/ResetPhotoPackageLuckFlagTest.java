package com.appleindustries.photobooth.scheduledtasks;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import com.appleindustries.photobooth.services.PhotoPackageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author zane
 */
class ResetPhotoPackageLuckFlagTest {

    @Mock
    PhotoPackageService photoPackageService;

    @InjectMocks
    ResetPhotoPackageLuckFlag resetPhotoPackageLuckFlag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void resetPhotoPackageLuckFlagsTask() {
        PhotoPackage photoPackage = new PhotoPackage();
        List<PhotoPackage> photoPackages = new ArrayList<>();

        photoPackage.setType(PhotoPackageEnum.PANORAMA);
        photoPackage.setPrice(new BigDecimal((5)));
        photoPackage.setLuckEnabled(false);
        photoPackages.add(photoPackage);

        when(photoPackageService.saveOrUpdate(any(PhotoPackage.class))).thenReturn(photoPackage);
        resetPhotoPackageLuckFlag.resetPhotoPackageLuckFlagsTask(photoPackages);
        
        assertEquals(true, photoPackage.isLuckEnabled());
    }
}