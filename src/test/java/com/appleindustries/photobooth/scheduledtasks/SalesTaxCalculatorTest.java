package com.appleindustries.photobooth.scheduledtasks;

import com.appleindustries.photobooth.entities.PurchaseDetail;
import com.appleindustries.photobooth.repositories.PurchaseDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * @author zane
 */
class SalesTaxCalculatorTest {

    @Mock
    private PurchaseDetailRepository purchaseDetailRepository;

    @InjectMocks
    private SalesTaxCalculator salesTaxCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateTaxesForMonth() {
        List<PurchaseDetail> purchaseDetails = new ArrayList<>();

        BigDecimal price1 = new BigDecimal(5);
        BigDecimal price2 = new BigDecimal(7);
        BigDecimal price3 = new BigDecimal(0);
        Integer quantity1 = 2;
        Integer quantity2 = 5;
        Integer quantity3 = 1;

        BigDecimal total1 = price1.multiply(BigDecimal.valueOf(quantity1));
        BigDecimal total2 = price2.multiply(BigDecimal.valueOf(quantity2));
        BigDecimal total3 = price3.multiply(BigDecimal.valueOf(quantity3));

        BigDecimal expectedResult = total1.add(total2).add(total3);
        expectedResult = expectedResult.multiply(SalesTaxCalculator.SALES_TAX_RATE);

        PurchaseDetail purchaseDetail1 = new PurchaseDetail();
        purchaseDetail1.setPrice(price1);
        purchaseDetail1.setQuantity(quantity1);
        purchaseDetails.add(purchaseDetail1);

        PurchaseDetail purchaseDetail2 = new PurchaseDetail();
        purchaseDetail2.setPrice(price2);
        purchaseDetail2.setQuantity(quantity2);
        purchaseDetails.add(purchaseDetail2);

        PurchaseDetail purchaseDetail3 = new PurchaseDetail();
        purchaseDetail3.setPrice(price3);
        purchaseDetail3.setQuantity(quantity3);
        purchaseDetails.add(purchaseDetail3);

        when(purchaseDetailRepository.findPurchasesForMonth(any(Date.class), any(Date.class))).thenReturn(purchaseDetails);

        BigDecimal result = salesTaxCalculator.calculateTaxesForMonth(new Date(), new Date());
        assertEquals(expectedResult, result);
    }
}