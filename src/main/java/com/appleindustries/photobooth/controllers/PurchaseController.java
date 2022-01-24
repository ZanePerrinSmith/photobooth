package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zane
 */
@RequestMapping("/purchase")
@RestController
@AllArgsConstructor
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    /**
     * @return
     */
    @GetMapping("/list")
    public List<Purchase> listPackages() {
        return (List<Purchase>) purchaseService.listAll();
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/show/{id}")
    public Purchase showPackage(@PathVariable Integer id) {
        return purchaseService.getById(id);
    }

    /**
     * @param purchase
     * @return
     */
    @PostMapping("/create")
    public Purchase createPurchase(Purchase purchase) {
        return purchaseService.saveOrUpdate(purchase);
    }

    /**
     * @param id
     * @param purchase
     * @return
     */
    @PutMapping("/edit/{id}")
    public Purchase editPurchase(@PathVariable Integer id, Purchase purchase) {
        Purchase purchaseToUpdate = purchaseService.getById(id);
        purchaseToUpdate = purchaseService.merge(purchaseToUpdate, purchase);
        return purchaseService.saveOrUpdate(purchaseToUpdate);
    }

    /**
     * @param purchase
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Purchase saveOrUpdate(Purchase purchase) {
        return purchaseService.saveOrUpdate(purchase);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String deletePackage(@PathVariable Integer id) {
        Purchase purchaseToDelete = purchaseService.getById(id);
        purchaseService.delete(id);
        return "Deleted " + purchaseToDelete.toString();
    }
}
