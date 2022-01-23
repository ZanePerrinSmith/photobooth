package com.appleindustries.photobooth.repositories;

import com.appleindustries.photobooth.entities.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author zane
 */
@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Integer> {

    @Query("select p from PurchaseDetail p where p.dateCreated between ?1 and ?2")
    List<PurchaseDetail> findPurchasesInDateRange(Date monthBegin, Date monthEnd);
}
