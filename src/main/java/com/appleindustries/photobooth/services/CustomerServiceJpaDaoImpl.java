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

    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Customer savedCustomer = em.merge(domainObject);
        em.getTransaction().commit();

        return savedCustomer;
    }

    @Override
    public Customer merge(Customer customerToUpdate, Customer customer) {
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        return customerToUpdate;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
    }
}
