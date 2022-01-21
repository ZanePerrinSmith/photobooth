package com.appleindustries.photobooth.services;

import java.util.List;

/**
 * @author zane
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    T merge(T domainObjectToUpdate, T domainObject);

    void delete(Integer id);
}
