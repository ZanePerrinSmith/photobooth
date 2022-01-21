package com.appleindustries.photobooth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
}
