package mx.wedevelop.guernica.services.impl;

import mx.wedevelop.guernica.models.User;
import mx.wedevelop.guernica.services.UserServiceDao;
import mx.wedevelop.guernica.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by colorado on 26/02/17.
 */
@Service
public class UserServiceDaoImpl extends AbstractJpaDaoService implements UserServiceDao {

    private SecurityService securityService;

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public List<User> findAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public User findByUserName(String userName) {
        EntityManager em = emf.createEntityManager();

        List<User> result = em
                .createQuery("from User u where u.userName= :username")
                .setParameter("username", userName)
                .getResultList();

        if(result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    @Override
    public User saveOrUpdate(User user) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        if(user.getPassword() != null) {
            user.setEncodedPassword(securityService.encryptString(user.getPassword()));
        }

        User savedUser = em.merge(user);
        em.getTransaction().commit();
        return savedUser;
    }

    @Override
    public void delete(int id) {

    }
}
