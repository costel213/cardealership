package com.costelmitrea.service;

import com.costelmitrea.model.Brand;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BrandService {

    @PersistenceContext(unitName = "cardealership")
    private EntityManager em;

    public List<Brand> get() {
        return em.createQuery("select b from Brand b", Brand.class).getResultList();
    }
}
