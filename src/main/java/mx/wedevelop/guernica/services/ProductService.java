package mx.wedevelop.guernica.services;

import mx.wedevelop.guernica.models.Product;

import java.util.List;

/**
 * Created by colorado on 27/02/17.
 */
public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    Product saveOrUpdate(Product product);
    void delete(int id);

}
