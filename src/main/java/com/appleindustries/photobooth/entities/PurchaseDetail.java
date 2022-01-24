package com.appleindustries.photobooth.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author zane
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PurchaseDetail extends AbstractEntity {

    @ManyToOne
    @ToString.Exclude
    @JsonBackReference
    private Purchase purchase;

    @OneToOne
    @ToString.Exclude
    private PhotoPackage photoPackage;

    private Integer quantity;
    private BigDecimal price;

    @PrePersist
    @PreUpdate
    private void determinePrice() {
        if (null == price) {
            BigDecimal photoPackagePrice = this.getPhotoPackage().getPrice();
            setPrice(photoPackagePrice.multiply(BigDecimal.valueOf(quantity)));
        } else if (isGreaterThanZero(price)) {
            BigDecimal photoPackagePrice = this.getPhotoPackage().getPrice();
            setPrice(photoPackagePrice.multiply(BigDecimal.valueOf(quantity)));
        }
    }

    private boolean isGreaterThanZero(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) == 1;
    }
}
