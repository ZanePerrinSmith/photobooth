package com.appleindustries.photobooth.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class Customer extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private List<Purchase> purchases = new ArrayList<>();


    public void addPurchase(Purchase purchase) {
        purchase.setCustomer(this);
        purchases.add(purchase);
    }

    public void addAllPurchaseDetail(List<Purchase> purchasesToAdd) {
        for (Purchase purchase : purchasesToAdd) {
            addPurchase(purchase);
        }
    }

    public void removePurchase(Purchase purchase) {
        purchases.remove(purchase);
    }

}
