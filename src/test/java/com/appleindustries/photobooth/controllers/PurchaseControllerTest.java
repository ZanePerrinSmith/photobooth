package com.appleindustries.photobooth.controllers;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.entities.PurchaseDetail;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import com.appleindustries.photobooth.services.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zane
 */

class PurchaseControllerTest extends AbstractRestControllerTest {

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
    void listPackages() throws Exception {
        List<Purchase> expectedPurchases = new ArrayList<>();
        expectedPurchases.add(new Purchase());
        expectedPurchases.add(new Purchase());

        when(purchaseService.listAll()).thenReturn((List) expectedPurchases);

        MvcResult mvcResult = mockMvc.perform(get("/purchase/list"))
                .andExpect(status().isOk())
                .andReturn();
        Purchase[] purchasesResult = mvcResultAsObject(mvcResult, Purchase[].class);

        assertEquals(expectedPurchases.size(), purchasesResult.length);
    }

    @Test
    void showPackage() throws Exception {
        Integer id = 1;
        when(purchaseService.getById(id)).thenReturn(new Purchase());

        MvcResult mvcResult = mockMvc.perform(get("/purchase/show/1"))
                .andExpect(status().isOk())
                .andReturn();
        Purchase purchaseResult = mvcResultAsObject(mvcResult, Purchase.class);

        assertNotNull(purchaseResult);
    }

    @Test
    void createPurchase() throws Exception {
        when(purchaseService.saveOrUpdate(any(Purchase.class))).thenReturn(new Purchase());

        MvcResult mvcResult = mockMvc.perform(post("/purchase/create"))
                .andExpect(status().isOk())
                .andReturn();
        Purchase purchaseResult = mvcResultAsObject(mvcResult, Purchase.class);

        assertNotNull(purchaseResult);
    }

    @Test
    void editPurchase() throws Exception {
        Integer id = 1;
        when(purchaseService.getById(id)).thenReturn(new Purchase());
        when(purchaseService.merge(any(Purchase.class), any(Purchase.class))).thenReturn(new Purchase());
        when(purchaseService.saveOrUpdate(any(Purchase.class))).thenReturn(new Purchase());

        MvcResult mvcResult = mockMvc.perform(put("/purchase/edit/1"))
                .andExpect(status().isOk())
                .andReturn();
        Purchase purchaseResult = mvcResultAsObject(mvcResult, Purchase.class);

        assertNotNull(purchaseResult);
    }

    @Test
    void saveOrUpdate() throws Exception {
        Integer id = 1;
        Purchase expectedPurchase = new Purchase();
        BigDecimal price = new BigDecimal(5);

        PhotoPackage photoPackage = new PhotoPackage();
        photoPackage.setType(PhotoPackageEnum.PANORAMA);
        photoPackage.setPrice(price);

        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setPhotoPackage(photoPackage);

        List<PurchaseDetail> purchaseDetails = new ArrayList<>();
        purchaseDetails.add(purchaseDetail);

        expectedPurchase.setId(1);
        expectedPurchase.addAllPurchaseDetail(purchaseDetails);


        when(purchaseService.saveOrUpdate(any(Purchase.class))).thenReturn(expectedPurchase);

        MvcResult mvcResult = mockMvc.perform(post("/purchase/saveOrUpdate")
                        .param("id", String.valueOf(id))
                        .content(mapToJson(purchaseDetails)))
                .andExpect(status().isOk())
                .andReturn();

        Purchase purchaseResult = mvcResultAsObject(mvcResult, Purchase.class);
        assertEquals(id, purchaseResult.getId());
        assertEquals(purchaseDetails.size(), purchaseResult.getPurchaseDetails().size());
    }

    @Test
    void deletePackage() throws Exception {
        Integer id = 1;
        when(purchaseService.getById(id)).thenReturn(new Purchase());

        MvcResult mvcResult = mockMvc.perform(delete("/purchase/delete/1"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals("Deleted " + new Purchase(), content);
    }
}