package com.appleindustries.photobooth.services;

import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.repositories.PhotoPackageRepository;
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
public class PhotoPackageServiceJpaDaoImpl extends AbstractJpaDaoService implements PhotoPackageService {

    @Autowired
    private PhotoPackageRepository photoPackageRepository;

    /**
     * @return
     */
    @Override
    public List<PhotoPackage> listAll() {
        EntityManager em = emf.createEntityManager();
        photoPackageRepository.findAll();
        return em.createQuery("from PhotoPackage", PhotoPackage.class).getResultList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public PhotoPackage getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(PhotoPackage.class, id);
    }

    /**
     * @param photoPackage
     * @return
     */
    @Override
    public PhotoPackage saveOrUpdate(PhotoPackage photoPackage) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        PhotoPackage savedPhotoPackage = em.merge(photoPackage);
        em.getTransaction().commit();

        return savedPhotoPackage;
    }

    /**
     * @param photoPackageToUpdate
     * @param photoPackage
     * @return
     */
    @Override
    public PhotoPackage merge(PhotoPackage photoPackageToUpdate, PhotoPackage photoPackage) {
        photoPackageToUpdate.setType(photoPackage.getType());
        photoPackageToUpdate.setPrice(photoPackage.getPrice());
        return photoPackageToUpdate;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(PhotoPackage.class, id));
        em.getTransaction().commit();
    }
}

