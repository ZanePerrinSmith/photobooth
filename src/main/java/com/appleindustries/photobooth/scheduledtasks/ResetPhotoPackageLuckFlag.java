package com.appleindustries.photobooth.scheduledtasks;

import com.appleindustries.photobooth.entities.PhotoPackage;
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

    /**
     * Reset luck flags to true
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void resetPhotoPackageLuckFlagsTask() {
        List<PhotoPackage> photoPackages = (List<PhotoPackage>) photoPackageService.listAll();
        resetPhotoPackageLuckFlagsTask(photoPackages);
    }

    public void resetPhotoPackageLuckFlagsTask(List<PhotoPackage> photoPackages) {
        for (PhotoPackage photoPackage : photoPackages) {
            photoPackage.setLuckEnabled(true);
            photoPackageService.saveOrUpdate(photoPackage);
        }
    }
}
