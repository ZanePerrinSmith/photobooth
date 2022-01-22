package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.PurchaseDetail;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author zane
 */
@Service
public class PurchaseDetailServiceJpaDaoImpl extends AbstractJpaDaoService implements PurchaseDetailService {

    @Override
    public List<PurchaseDetail> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from PurchaseDetail", PurchaseDetail.class).getResultList();
    }

    @Override
    public PurchaseDetail getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(PurchaseDetail.class, id);
    }

    @Override
    public PurchaseDetail saveOrUpdate(PurchaseDetail domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        PurchaseDetail savedPurchaseDetail = em.merge(domainObject);
        em.getTransaction().commit();

        return savedPurchaseDetail;
    }

    @Override
    public PurchaseDetail merge(PurchaseDetail purchaseDetailToUpdate, PurchaseDetail purchaseDetail) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(PurchaseDetail.class, id));
        em.getTransaction().commit();
    }

    @Override
    public boolean isLucky(PurchaseDetail purchaseDetail) {
        return purchaseDetail.getPhotoPackage().isLuckEnabled();
    }
}