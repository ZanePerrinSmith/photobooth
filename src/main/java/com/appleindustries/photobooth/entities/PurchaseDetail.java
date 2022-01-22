package com.appleindustries.photobooth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author zane
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PurchaseDetail extends AbstractEntity {

    @ManyToOne
    private Purchase purchase;

    @OneToOne
    private PhotoPackage photoPackage;

    private Integer quantity;
    private BigDecimal price;

    @PrePersist
    @PreUpdate
    private void determinePrice() {
        if (null == price || BigDecimal.ZERO != price) {
            BigDecimal photoPackagePrice = this.getPhotoPackage().getPrice();
            setPrice(photoPackagePrice.multiply(BigDecimal.valueOf(quantity)));
        }
    }
}
