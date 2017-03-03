package mx.wedevelop.guernica.services.impl;

import mx.wedevelop.guernica.models.WorkShift;
import mx.wedevelop.guernica.services.WorkShiftService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by colorado on 3/03/17.
 */
@Service
public class WorkShiftServiceImpl extends AbstractJpaDaoService implements WorkShiftService {

    @Override
    public List<WorkShift> findAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from WorkShift", WorkShift.class).getResultList();
    }

    @Override
    public WorkShift findById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(WorkShift.class, id);
    }

    @Override
    public WorkShift saveOrUpdate(WorkShift object) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        WorkShift savedWorkShift = em.merge(object);
        em.getTransaction().commit();

        return savedWorkShift;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(WorkShift.class, id));
        em.getTransaction().commit();
    }
}
