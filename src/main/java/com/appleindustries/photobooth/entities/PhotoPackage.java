package com.appleindustries.photobooth.entities;

import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private PhotoPackageEnum type;

    private BigDecimal price;

    private boolean luckEnabled = true;

}
