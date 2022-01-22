package com.appleindustries.photobooth.repositories;

import com.appleindustries.photobooth.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author zane
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query("select p from Purchase p where p.id = ?1 and p.customer = ?2")
    Purchase findByOrderIdAndCustomerId(Integer orderId, Integer customerId);

    @Query("select p from Purchase p where p.dateCreated between ?1 and ?2")
    List<Purchase> findPurchasesForMonth(Date monthBegin, Date monthEnd);
}
