package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.entities.Customer;
import com.appleindustries.photobooth.services.CustomerService;
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

    @GetMapping("/list")
    public List<Customer> listPackages() {
        return (List<Customer>) customerService.listAll();
    }

    @GetMapping("/show/{id}")
    public Customer showPackage(@PathVariable Integer id) {
        return customerService.getById(id);
    }

    @PostMapping("/create")
    public Customer createCustomer(Customer customer) {
        return customerService.saveOrUpdate(customer);
    }

    @PutMapping("/edit/{id}")
    public Customer editCustomer(@PathVariable Integer id, Customer customer) {
        Customer customerToUpdate = customerService.getById(id);
        customerToUpdate = customerService.merge(customerToUpdate, customer);
        return customerService.saveOrUpdate(customerToUpdate);
    }

    @PostMapping("/saveOrUpdate")
    public Customer saveOrUpdate(Customer customer) {
        return customerService.saveOrUpdate(customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePackage(@PathVariable Integer id) {
        Customer customerToDelete = customerService.getById(id);
        customerService.delete(id);
        return "Deleted " + customerToDelete.toString();
    }
}
