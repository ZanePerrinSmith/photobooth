package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.repositories.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author zane
 */
@Service
@AllArgsConstructor
public class PurchaseServiceJpaDaoImpl extends AbstractJpaDaoService implements PurchaseService {

    private static final int LUCK_PROBABILITY = 2;
    private static final int WINNER = 1;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Purchase", Purchase.class).getResultList();
    }

    @Override
    public Purchase getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Purchase.class, id);
    }

    @Override
    public Purchase saveOrUpdate(Purchase domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Purchase savedPurchase = em.merge(domainObject);
        em.getTransaction().commit();

        return savedPurchase;
    }

    @Override
    // TODO
    public Purchase merge(Purchase purchaseToUpdate, Purchase purchase) {
        //purchaseToUpdate.setPhotoPackages(purchase.getPhotoPackages());
        return purchaseToUpdate;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Purchase.class, id));
        em.getTransaction().commit();
    }

    @Override
    public Purchase getByCustomerId(Integer customerId) {
        EntityManager em = emf.createEntityManager();

        return em.find(Purchase.class, customerId);
    }


}

