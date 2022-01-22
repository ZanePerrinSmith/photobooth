package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.services.PurchaseService;
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
class PurchaseControllerTest {
    @Mock
    private PurchaseService purchaseService;
    @InjectMocks
    private PurchaseController purchaseController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseController).build();
    }

    @Test
    void listOrders() throws Exception {
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase());
        purchases.add(new Purchase());

        when(purchaseService.listAll()).thenReturn((List) purchases);

        mockMvc.perform(get("/purchase/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void showOrder() {
    }

    @Test
    void createOrder() {
    }

    @Test
    void editOrder() {
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void deleteOrder() {
    }
}