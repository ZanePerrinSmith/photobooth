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

    private static final Integer LUCKY_PURCHASE_QUANTITY = 1;

    /**
     * @return
     */
    @Override
    public List<PurchaseDetail> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from PurchaseDetail", PurchaseDetail.class).getResultList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public PurchaseDetail getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(PurchaseDetail.class, id);
    }

    /**
     * @param photoPackage
     * @return
     */
    @Override
    public PurchaseDetail saveOrUpdate(PurchaseDetail photoPackage) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        PurchaseDetail savedPurchaseDetail = em.merge(photoPackage);
        em.getTransaction().commit();

        return savedPurchaseDetail;
    }

    /**
     * @param purchaseDetailToUpdate
     * @param purchaseDetail
     * @return
     */
    @Override
    public PurchaseDetail merge(PurchaseDetail purchaseDetailToUpdate, PurchaseDetail purchaseDetail) {
        purchaseDetailToUpdate.setPhotoPackage(purchaseDetail.getPhotoPackage());
        purchaseDetailToUpdate.setQuantity(purchaseDetail.getQuantity());
        return purchaseDetailToUpdate;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(PurchaseDetail.class, id));
        em.getTransaction().commit();
    }

    /**
     * Check that the PhotoPackage is lucky
     * and that only 1 was purchased
     *
     * @param purchaseDetail
     * @return
     */
    @Override
    public boolean isLucky(PurchaseDetail purchaseDetail) {
        return purchaseDetail.getPhotoPackage().isLuckEnabled() && purchaseDetail.getQuantity() == LUCKY_PURCHASE_QUANTITY;
    }
}