package com.appleindustries.photobooth.scheduledtasks;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.repositories.PhotoPackageRepository;
import com.appleindustries.photobooth.services.PhotoPackageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zane
 */
@Component
@AllArgsConstructor
public class ResetPhotoPackageLuckFlag {

    @Autowired
    PhotoPackageService photoPackageService;
    @Autowired
    PhotoPackageRepository photoPackageRepository;

    @Scheduled(cron = "0 0 0 1 1/1 *")
    public void resetPhotoPackageLuckFlagsTask() {
        List<PhotoPackage> photoPackages = (List<PhotoPackage>) photoPackageService.listAll();
        for (PhotoPackage photoPackage : photoPackages) {
            photoPackage.setLuckEnabled(true);
            photoPackageService.saveOrUpdate(photoPackage);
        }
    }
}
