package com.appleindustries.photobooth.services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @author zane
 */
public abstract class AbstractJpaDaoService {
    protected EntityManagerFactory emf;

    /**
     * @param emf
     */
    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
