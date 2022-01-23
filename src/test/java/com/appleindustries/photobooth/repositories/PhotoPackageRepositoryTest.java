package com.appleindustries.photobooth.repositories;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author zane
 */
@DataJpaTest
class PhotoPackageRepositoryTest {

    @Autowired
    PhotoPackageRepository photoPackageRepository;

    @Test
    void findByTypeFound() {
        PhotoPackageEnum queriedType = PhotoPackageEnum.PRINT;
        PhotoPackage photoPackage = new PhotoPackage();
        photoPackage.setType(queriedType);
        photoPackage.setPrice(new BigDecimal((5)));
        PhotoPackage expectedPhotoPackage = photoPackageRepository.save(photoPackage);

        PhotoPackage resultingPhotoPackage = photoPackageRepository.findByType(queriedType);
        assertEquals(expectedPhotoPackage, resultingPhotoPackage);
    }

    @Test
    void findByTypeNotFound() {
        PhotoPackageEnum queriedType = PhotoPackageEnum.PRINT;
        PhotoPackage photoPackage = new PhotoPackage();
        photoPackage.setType(PhotoPackageEnum.PANORAMA);
        photoPackage.setPrice(new BigDecimal((5)));
        photoPackageRepository.save(photoPackage);


        PhotoPackage resultingPhotoPackage = photoPackageRepository.findByType(queriedType);
        assertNull(resultingPhotoPackage);
    }
}