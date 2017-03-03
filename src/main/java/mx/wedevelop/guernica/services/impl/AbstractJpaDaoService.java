package mx.wedevelop.guernica.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;

/**
 * Created by colorado on 3/03/17.
 */
public abstract class AbstractJpaDaoService {

    protected EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
