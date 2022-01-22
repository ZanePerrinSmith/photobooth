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
    PurchaseService purchaseService;

    @GetMapping("/list")
    public List<Purchase> listOrders() {
        return (List<Purchase>) purchaseService.listAll();
    }

    @GetMapping("/show/{id}")
    public Purchase showOrder(@PathVariable Integer id) {
        return purchaseService.getById(id);
    }

    @PostMapping("/create")
    public Purchase createOrder(@RequestBody Purchase purchase) {
        return purchaseService.saveOrUpdate(purchase);
    }

    @PostMapping("/edit/{id}")
    public Purchase editOrder(@PathVariable Integer id, @RequestBody Purchase purchase) {
        Purchase purchaseToUpdate = purchaseService.getById(id);
        purchaseToUpdate = purchaseService.merge(purchaseToUpdate, purchase);
        return purchaseService.saveOrUpdate(purchaseToUpdate);
    }

    @PostMapping("/saveOrUpdate")
    public Purchase saveOrUpdate(@RequestBody Purchase purchase) {
        return purchaseService.saveOrUpdate(purchase);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        Purchase purchaseToDelete = purchaseService.getById(id);
        purchaseService.delete(id);
        return "Deleted " + purchaseToDelete.toString();
    }
}
