package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.Customer;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author zane
 */
@Service
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {

    /**
     * @return
     */
    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Customer", Customer.class).getResultList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Customer.class, id);
    }

    /**
     * @param customer
     * @return
     */
    @Override
    public Customer saveOrUpdate(Customer customer) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Customer savedCustomer = em.merge(customer);
        em.getTransaction().commit();

        return savedCustomer;
    }

    /**
     * @param customerToUpdate
     * @param customer
     * @return
     */
    @Override
    public Customer merge(Customer customerToUpdate, Customer customer) {
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        return customerToUpdate;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
    }
}
