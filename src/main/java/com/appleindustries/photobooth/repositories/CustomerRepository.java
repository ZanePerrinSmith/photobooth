package com.appleindustries.photobooth.repositories;

import com.appleindustries.photobooth.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zane
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
