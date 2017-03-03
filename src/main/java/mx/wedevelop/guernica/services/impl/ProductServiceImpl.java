package mx.wedevelop.guernica.services.impl;

import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.services.CRUDService;
import mx.wedevelop.guernica.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by colorado on 27/02/17.
 */
@Service
public class ProductServiceImpl extends AbstractJpaDaoService implements ProductService {

    @Override
    public List<Product> findAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product findById(int id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Product savedProduct = em.merge(product);
        em.getTransaction().commit();

        return savedProduct;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Product.class, id));
        em.getTransaction().commit();
    }
}
