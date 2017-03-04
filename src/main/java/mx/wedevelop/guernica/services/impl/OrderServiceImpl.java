package mx.wedevelop.guernica.services.impl;

import mx.wedevelop.guernica.models.AbstractDomain;
import mx.wedevelop.guernica.models.Order;
import mx.wedevelop.guernica.services.OrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by colorado on 4/03/17.
 */
@Service
public class OrderServiceImpl extends AbstractJpaDaoService implements OrderService {
    @Override
    public List<Order> findAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public Order findById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order object) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Order savedOrder = em.merge(object);
        em.getTransaction().commit();

        return savedOrder;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Order.class, id));
        em.getTransaction().commit();
    }
}
