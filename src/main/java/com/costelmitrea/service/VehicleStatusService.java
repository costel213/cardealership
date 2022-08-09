package com.costelmitrea.service;

import com.costelmitrea.model.VehicleStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VehicleStatusService {

    @PersistenceContext(unitName = "cardealership")
    private EntityManager em;

    public List<VehicleStatus> get() {
        return em.createQuery("select v from VehicleStatus v", VehicleStatus.class).getResultList();
    }
}
