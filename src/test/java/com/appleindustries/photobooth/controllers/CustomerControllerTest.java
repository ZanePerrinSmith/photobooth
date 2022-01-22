package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.entities.Customer;
import com.appleindustries.photobooth.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zane
 */
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void listCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn((List) customers);

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void showCustomer() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void editCustomer() {
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void createPurchase() {
    }

    @Test
    void testListCustomers() {
    }

    @Test
    void testShowCustomer() {
    }

    @Test
    void testCreateCustomer() {
    }

    @Test
    void testEditCustomer() {
    }

    @Test
    void testSaveOrUpdate() {
    }

    @Test
    void testDeleteCustomer() {
    }

    @Test
    void testCreatePurchase() {
    }

    @Test
    void listPurhcases() {
    }

    @Test
    void showPurchase() {
    }

    @Test
    void editPurhcase() {
    }

    @Test
    void saveOrUpdatePurchase() {
    }

    @Test
    void deletePurchase() {
    }
}