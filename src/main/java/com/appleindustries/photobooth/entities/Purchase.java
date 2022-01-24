package com.appleindustries.photobooth.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zane
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Purchase extends AbstractEntity {

    @ManyToOne
    @ToString.Exclude
    @JsonBackReference
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase", orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
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
