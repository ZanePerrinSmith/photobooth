package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.entities.PurchaseDetail;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import com.appleindustries.photobooth.repositories.PhotoPackageRepository;
import com.appleindustries.photobooth.repositories.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
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
    private static final int LUCK_PROBABILITY = 2;
    private static final int WINNER = 1;
    private static final Integer PRIZE_QUANTITY = 1;
    private static final BigDecimal PRIZE_PRICE = BigDecimal.ZERO;

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    PurchaseDetailService purchaseDetailService;
    @Autowired
    PhotoPackageService photoPackageService;
    @Autowired
    PhotoPackageRepository photoPackageRepository;

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

    @Override
    public boolean isLucky(Purchase purchase) {
        Random random = new Random(LUCK_PROBABILITY);
        List<PurchaseDetail> purchaseDetails = purchase.getPurchaseDetails();
        return purchaseDetails.size() == ELIGIBLE_WINNER
                && purchaseDetailService.isLucky(purchaseDetails.get(FIRST_PURCHASE_DETAIL))
                && random.nextInt() == WINNER;
    }

    @Override
    public void givePrize(Purchase purchase) {
        PhotoPackageEnum photoPackagePurchased = purchase.getPurchaseDetails().get(FIRST_PURCHASE_DETAIL).getPhotoPackage().getType();
        for (PhotoPackageEnum photoPackageType : PhotoPackageEnum.values()) {
            if (photoPackageType != photoPackagePurchased) {
                PhotoPackage photoPackageToAdd = photoPackageRepository.findByType(photoPackageType);
                purchase.addPurchaseDetail(new PurchaseDetail(purchase, photoPackageToAdd, PRIZE_QUANTITY, PRIZE_PRICE));
            }
        }
        disableLuck(photoPackagePurchased);
        this.saveOrUpdate(purchase);
    }

    private void disableLuck(PhotoPackageEnum purchasedType) {
        PhotoPackage photoPackage = photoPackageRepository.findByType(purchasedType);
        photoPackage.setLuckEnabled(false);
        photoPackageService.saveOrUpdate(photoPackage);
    }

}

