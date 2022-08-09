package com.costelmitrea.service;

import com.costelmitrea.model.VehicleType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VehicleTypeService {

    @PersistenceContext(unitName = "cardealership")
    private EntityManager em;

    public List<VehicleType> get() {
        return em.createQuery("select v from VehicleType v", VehicleType.class).getResultList();
    }
}
