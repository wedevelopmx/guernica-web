package mx.wedevelop.guernica.services;

import mx.wedevelop.guernica.models.Domain;

import java.util.List;

/**
 * Created by colorado on 3/03/17.
 */
public interface CRUDService<T> {
    List<T> findAll();
    T findById(int id);
    T saveOrUpdate(T object);
    void delete(int id);
}
