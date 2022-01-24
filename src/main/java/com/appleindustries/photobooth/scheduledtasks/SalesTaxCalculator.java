package com.appleindustries.photobooth.scheduledtasks;

import com.appleindustries.photobooth.entities.PurchaseDetail;
import com.appleindustries.photobooth.repositories.PurchaseDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author zane
 */
@Component
@AllArgsConstructor
public class SalesTaxCalculator {
    public static final BigDecimal SALES_TAX_RATE = new BigDecimal(8.635);

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    /**
     * Calculate taxes for previous month
     */
    @Scheduled(cron = "0 0 1 1 1/1 *")
    public void calculateTaxesForPreviousMonth() {
        LocalDate previousMonth = getPreviousMonth();
        LocalDate localMonthBegin = getBeginningOfMonth(previousMonth);
        LocalDate localMonthEnd = getEndOfMonth(previousMonth);

        Date monthBegin = convertLocalDateToDate(localMonthBegin);
        Date monthEnd = convertLocalDateToDate(localMonthEnd);
        calculateTaxesForMonth(monthBegin, monthEnd);
    }

    /**
     * @param monthBegin
     * @param monthEnd
     * @return
     */
    public BigDecimal calculateTaxesForMonth(Date monthBegin, Date monthEnd) {
        BigDecimal salesTaxDue = new BigDecimal(0);
        List<PurchaseDetail> purchaseDetails = purchaseDetailRepository.findPurchasesInDateRange(monthBegin, monthEnd);

        for (PurchaseDetail purchaseDetail : purchaseDetails) {
            salesTaxDue = salesTaxDue.add(getSalePrice(purchaseDetail));
        }
        return salesTaxDue.multiply(SALES_TAX_RATE);
    }

    private BigDecimal getSalePrice(PurchaseDetail purchaseDetail) {
        BigDecimal price = purchaseDetail.getPrice();
        BigDecimal quantity = BigDecimal.valueOf(purchaseDetail.getQuantity());
        return price.multiply(quantity);
    }

    private Date convertLocalDateToDate(LocalDate date) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
    }

    private LocalDate getEndOfMonth(LocalDate month) {
        return month.withDayOfMonth(month.lengthOfMonth());
    }

    private LocalDate getBeginningOfMonth(LocalDate month) {
        return month.withDayOfMonth(1);
    }

    private LocalDate getPreviousMonth() {
        LocalDate today = LocalDate.now();
        return today.minusMonths(1);
    }
}
