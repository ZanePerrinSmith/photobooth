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
    private CustomerService customerService;

    /**
     * @return
     */
    @GetMapping("/list")
    public List<Customer> listPackages() {
        return (List<Customer>) customerService.listAll();
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/show/{id}")
    public Customer showPackage(@PathVariable Integer id) {
        return customerService.getById(id);
    }

    /**
     * @param customer
     * @return
     */
    @PostMapping("/create")
    public Customer createCustomer(Customer customer) {
        return customerService.saveOrUpdate(customer);
    }

    /**
     * @param id
     * @param customer
     * @return
     */
    @PutMapping("/edit/{id}")
    public Customer editCustomer(@PathVariable Integer id, Customer customer) {
        Customer customerToUpdate = customerService.getById(id);
        customerToUpdate = customerService.merge(customerToUpdate, customer);
        return customerService.saveOrUpdate(customerToUpdate);
    }

    /**
     * @param customer
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Customer saveOrUpdate(Customer customer) {
        return customerService.saveOrUpdate(customer);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String deletePackage(@PathVariable Integer id) {
        Customer customerToDelete = customerService.getById(id);
        customerService.delete(id);
        return "Deleted " + customerToDelete.toString();
    }
}
