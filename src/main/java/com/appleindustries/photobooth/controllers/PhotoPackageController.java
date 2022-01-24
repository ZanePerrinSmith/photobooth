package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.services.PhotoPackageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zane
 */
@RequestMapping("/photoPackage")
@RestController
@AllArgsConstructor
public class PhotoPackageController {

    @Autowired
    private PhotoPackageService photoPackageService;

    /**
     * @return
     */
    @GetMapping("/list")
    public List<PhotoPackage> listPackages() {
        return (List<PhotoPackage>) photoPackageService.listAll();
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/show/{id}")
    public PhotoPackage showPackage(@PathVariable Integer id) {
        return photoPackageService.getById(id);
    }

    /**
     * @param photoPackage
     * @return
     */
    @PostMapping("/create")
    public PhotoPackage createPhotoPackage(PhotoPackage photoPackage) {
        return photoPackageService.saveOrUpdate(photoPackage);
    }

    /**
     * @param id
     * @param photoPackage
     * @return
     */
    @PutMapping("/edit/{id}")
    public PhotoPackage editPhotoPackage(@PathVariable Integer id, PhotoPackage photoPackage) {
        PhotoPackage photoPackageToUpdate = photoPackageService.getById(id);
        photoPackageToUpdate = photoPackageService.merge(photoPackageToUpdate, photoPackage);
        return photoPackageService.saveOrUpdate(photoPackageToUpdate);
    }

    /**
     * @param photoPackage
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public PhotoPackage saveOrUpdate(PhotoPackage photoPackage) {
        return photoPackageService.saveOrUpdate(photoPackage);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String deletePackage(@PathVariable Integer id) {
        PhotoPackage photoPackageToDelete = photoPackageService.getById(id);
        photoPackageService.delete(id);
        return "Deleted " + photoPackageToDelete.toString();
    }
}
