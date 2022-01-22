package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.entities.Customer;
import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.repositories.CustomerRepository;
import com.appleindustries.photobooth.repositories.PhotoPackageRepository;
import com.appleindustries.photobooth.repositories.PurchaseRepository;
import com.appleindustries.photobooth.services.CustomerService;
import com.appleindustries.photobooth.services.PhotoPackageService;
import com.appleindustries.photobooth.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zane
 */
@RequestMapping("/customer")
@RestController
@AllArgsConstructor
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    PhotoPackageService photoPackageService;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PhotoPackageRepository photoPackageRepository;

    @GetMapping("/list")
    public List<Customer> listCustomers() {
        return (List<Customer>) customerService.listAll();
    }

    @GetMapping("/show/{id}")
    public Customer showCustomer(@PathVariable Integer id) {
        return customerService.getById(id);
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveOrUpdate(customer);
    }

    @PostMapping("/edit/{id}")
    public Customer editCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        Customer customerToUpdate = customerService.getById(id);
        customerToUpdate = customerService.merge(customerToUpdate, customer);
        return customerService.saveOrUpdate(customerToUpdate);
    }

    @PostMapping("/saveOrUpdate")
    public Customer saveOrUpdate(@RequestBody Customer customer) {
        return customerService.saveOrUpdate(customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        Customer customerToDelete = customerService.getById(id);
        customerService.delete(id);
        return "Deleted " + customerToDelete.toString();
    }

    @PostMapping("/{id}/purchase/create")
    public Purchase createPurchase(@PathVariable Integer id, @RequestBody Purchase purchase) {
        Customer customer = customerService.getById(id);
        purchase.setCustomer(customer);
        return purchaseService.saveOrUpdate(purchase);
    }

    @GetMapping("/{id}/purchase/list")
    public List<Purchase> listPurhcases() {
        return (List<Purchase>) purchaseService.listAll();
    }

    @GetMapping("/{id}/purchase/show/{id}")
    public Purchase showPurchase(@PathVariable Integer id) {
        return purchaseService.getById(id);
    }

    @PostMapping("/{id}/purchase/edit/{id}")
    public Purchase editPurhcase(@PathVariable Integer id, @RequestBody Purchase purchase) {
        Purchase purchaseToUpdate = purchaseService.getById(id);
        purchaseToUpdate = purchaseService.merge(purchaseToUpdate, purchase);
        return purchaseService.saveOrUpdate(purchaseToUpdate);
    }

    @PostMapping("/{id}/purchase/saveOrUpdate")
    public Purchase saveOrUpdatePurchase(@RequestBody Purchase purchase) {
        return purchaseService.saveOrUpdate(purchase);
    }

    @DeleteMapping("/{id}/purchase/delete/{id}")
    public String deletePurchase(@PathVariable Integer id) {
        Purchase purchaseToDelete = purchaseService.getById(id);
        purchaseService.delete(id);
        return "Deleted " + purchaseToDelete.toString();
    }

}
