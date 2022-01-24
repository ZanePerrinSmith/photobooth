package com.appleindustries.photobooth.repositories;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zane
 */
public interface PhotoPackageRepository extends JpaRepository<PhotoPackage, Integer> {
    /**
     * @param type
     * @return
     */
    PhotoPackage findByType(PhotoPackageEnum type);
}
