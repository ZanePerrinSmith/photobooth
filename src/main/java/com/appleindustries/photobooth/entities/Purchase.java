package com.appleindustries.photobooth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @ToString.Exclude
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase", orphanRemoval = true)
    @ToString.Exclude
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    public void addPurchaseDetail(PurchaseDetail purchaseDetail) {
        purchaseDetail.setPurchase(this);
        purchaseDetails.add(purchaseDetail);
    }

    public void addAllPurchaseDetail(List<PurchaseDetail> purchaseDetailsToAdd) {
        for (PurchaseDetail purchaseDetail : purchaseDetailsToAdd) {
            addPurchaseDetail(purchaseDetail);
        }
    }

    public void removePurchaseDetail(Purchase purchase) {
        purchaseDetails.remove(purchase);
    }

}
