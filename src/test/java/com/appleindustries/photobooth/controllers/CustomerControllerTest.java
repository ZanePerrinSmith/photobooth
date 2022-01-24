package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.AbstractRestControllerTest;
import com.appleindustries.photobooth.entities.Customer;
import com.appleindustries.photobooth.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zane
 */

class CustomerControllerTest extends AbstractRestControllerTest {
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
    void listPackages() throws Exception {
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer());
        expectedCustomers.add(new Customer());

        when(customerService.listAll()).thenReturn((List) expectedCustomers);

        MvcResult mvcResult = mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andReturn();
        Customer[] customersResult = mvcResultAsObject(mvcResult, Customer[].class);

        assertEquals(expectedCustomers.size(), customersResult.length);
    }

    @Test
    void showPackage() throws Exception {
        Integer id = 1;
        when(customerService.getById(id)).thenReturn(new Customer());

        MvcResult mvcResult = mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andReturn();
        Customer customerResult = mvcResultAsObject(mvcResult, Customer.class);

        assertNotNull(customerResult);
    }

    @Test
    void createCustomer() throws Exception {
        when(customerService.saveOrUpdate(any(Customer.class))).thenReturn(new Customer());

        MvcResult mvcResult = mockMvc.perform(post("/customer/create"))
                .andExpect(status().isOk())
                .andReturn();
        Customer customerResult = mvcResultAsObject(mvcResult, Customer.class);

        assertNotNull(customerResult);
    }

    @Test
    void editCustomer() throws Exception {
        Integer id = 1;
        when(customerService.getById(id)).thenReturn(new Customer());
        when(customerService.merge(any(Customer.class), any(Customer.class))).thenReturn(new Customer());
        when(customerService.saveOrUpdate(any(Customer.class))).thenReturn(new Customer());

        MvcResult mvcResult = mockMvc.perform(put("/customer/edit/1"))
                .andExpect(status().isOk())
                .andReturn();
        Customer customerResult = mvcResultAsObject(mvcResult, Customer.class);

        assertNotNull(customerResult);
    }

    @Test
    void saveOrUpdate() throws Exception {
        Integer id = 1;
        Customer expectedCustomer = new Customer();
        String firstName = "Master";
        String lastName = "Splinter";
        String email = "ms@tmnt.com";

        expectedCustomer.setId(1);
        expectedCustomer.setFirstName(firstName);
        expectedCustomer.setLastName(lastName);
        expectedCustomer.setEmail(email);


        when(customerService.saveOrUpdate(any(Customer.class))).thenReturn(expectedCustomer);

        MvcResult mvcResult = mockMvc.perform(post("/customer/saveOrUpdate")
                        .param("id", String.valueOf(id))
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .param("email", email))
                .andExpect(status().isOk())
                .andReturn();

        Customer customerResult = mvcResultAsObject(mvcResult, Customer.class);
        assertNotNull(customerResult);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(customerArgumentCaptor.capture());

        Customer boundCustomer = customerArgumentCaptor.getValue();

        assertEquals(id, boundCustomer.getId());
        assertEquals(firstName, boundCustomer.getFirstName());
        assertEquals(lastName, boundCustomer.getLastName());
        assertEquals(email, boundCustomer.getEmail());
    }

    @Test
    void deletePackage() throws Exception {
        Integer id = 1;
        when(customerService.getById(id)).thenReturn(new Customer());

        MvcResult mvcResult = mockMvc.perform(delete("/customer/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals("Deleted " + new Customer(), content);
    }
}