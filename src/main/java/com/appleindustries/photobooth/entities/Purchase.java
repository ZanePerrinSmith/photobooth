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

//    @ManyToMany
//    @JoinTable
//    private List<PhotoPackage> photoPackages = new ArrayList<>();
//
//    @ManyToOne
//    private PhotoPackage photoPackage;

    private BigDecimal price;

//    public void addPhotoPackage(PhotoPackage photoPackage) {
//        photoPackage.addPurchase(this);
//        photoPackages.add(photoPackage);
//    }
//
//    public void removePhotoPackage(PhotoPackage photoPackage) {
//        photoPackage.removePurchase(this);
//        photoPackages.remove(photoPackage);
//    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    @PrePersist
    @PreUpdate
    private void initializePrice() {
        BigDecimal price = new BigDecimal(0);
        for (PurchaseDetail purchaseDetail : purchaseDetails) {
            BigDecimal photoPackagePrice = purchaseDetail.getPhotoPackage().getSoldPrice();
            BigDecimal quantity = BigDecimal.valueOf(purchaseDetail.getQuantity());

            price.add(photoPackagePrice.multiply(quantity));
        }
    }
}
