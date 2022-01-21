package com.appleindustries.photobooth.repositories;

import com.appleindustries.photobooth.entities.Customer;
import com.appleindustries.photobooth.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zane
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Customer, Integer> {

    @Query("select o from Purchase o where o.id = ?1 and o.customer = ?2")
    Purchase findByOrderIdAndCustomerId(Integer orderId, Integer customerId);
}
