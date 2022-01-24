package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.entities.PurchaseDetail;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

/**
 * @author zane
 */
@Service
@AllArgsConstructor
public class PurchaseServiceJpaDaoImpl extends AbstractJpaDaoService implements PurchaseService {
    private static final int ELIGIBLE_WINNER = 1;
    private static final int FIRST_PURCHASE_DETAIL = 0;
    private static final int LUCK_PROBABILITY = 5;
    private static final int WINNER = 1;

    @Autowired
    private PurchaseDetailService purchaseDetailService;
    @Autowired
    private PrizeService prizeService;

    /**
     * @return
     */
    @Override
    public List<Purchase> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Purchase", Purchase.class).getResultList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Purchase getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Purchase.class, id);
    }

    /**
     * @param purchase
     * @return
     */
    @Override
    public Purchase saveOrUpdate(Purchase purchase) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        if (isLucky(purchase)) {
            purchase.addAllPurchaseDetail(prizeService.getPrizes(purchase));
        }
        Purchase savedPurchase = em.merge(purchase);
        em.getTransaction().commit();

        return savedPurchase;
    }

    /**
     * @param purchaseToUpdate
     * @param purchase
     * @return
     */
    @Override
    public Purchase merge(Purchase purchaseToUpdate, Purchase purchase) {
        purchaseToUpdate.setPurchaseDetails(purchase.getPurchaseDetails());
        return purchaseToUpdate;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Purchase.class, id));
        em.getTransaction().commit();
    }

    /**
     * @param customerId
     * @return
     */
    @Override
    public Purchase getByCustomerId(Integer customerId) {
        EntityManager em = emf.createEntityManager();

        return em.find(Purchase.class, customerId);
    }

    @Override
    /**
     * Check that the user only purchased 1 photo or 1 type
     * Check if a prize has been given for the photo package they purchased only 1
     * Randomly calculate if they've won if the above pass
     */
    public boolean isLucky(Purchase purchase) {
        Random random = new Random();
        List<PurchaseDetail> purchaseDetails = purchase.getPurchaseDetails();
        return purchaseDetails.size() == ELIGIBLE_WINNER
                && purchaseDetailService.isLucky(purchaseDetails.get(FIRST_PURCHASE_DETAIL))
                && random.nextInt(LUCK_PROBABILITY) <= WINNER;
    }
}

