package com.appleindustries.photobooth.entities;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    List<Purchase> purchases = new ArrayList<>();

    private String firstName;
    private String lastName;
    private String email;

    public void addPurchase(Purchase purchase) {
        purchase.setCustomer(this);
        purchases.add(purchase);
    }

    public void removePurchase(Purchase purchase) {
        purchases.remove(purchase);
    }

}
