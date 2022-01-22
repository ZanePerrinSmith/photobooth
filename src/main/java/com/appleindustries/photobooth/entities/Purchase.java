package com.appleindustries.photobooth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zane
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Purchase extends AbstractEntity {

    @ManyToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase", orphanRemoval = true)
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    private BigDecimal price;

    public void addPurchaseDetail(PurchaseDetail purchaseDetail) {
        purchaseDetail.setPurchase(this);
        purchaseDetails.add(purchaseDetail);
    }

    public void removePurchaseDetail(Purchase purchase) {
        purchaseDetails.remove(purchase);
    }

    @PrePersist
    @PreUpdate
    private void determinePrice() {
        BigDecimal price = new BigDecimal(0);
        for (PurchaseDetail purchaseDetail : purchaseDetails) {
            price.add(purchaseDetail.getPrice());
        }
    }
}
