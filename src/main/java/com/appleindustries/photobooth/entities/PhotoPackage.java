package com.appleindustries.photobooth.entities;

import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author zane
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PhotoPackage extends AbstractEntity {

    @Column(unique = true)
    private PhotoPackageEnum type;
    private BigDecimal price;
    private BigDecimal soldPrice;

    private boolean luckEnabled = true;

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.soldPrice = price;
    }

    //    @ManyToMany
//    @JoinTable
//    private List<Purchase> purchases = new ArrayList<>();
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "photoPackage")
//    private List<Purchase> purchases;

//    public void addPurchase(Purchase purchase) {
//        purchase.setPhotoPackage(this);
//        purchases.add(purchase);
//    }

//    public void removePurchase(Purchase purchase) {
//        purchases.remove(purchase);
//    }

}
