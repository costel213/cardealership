package com.costelmitrea.service;

import com.costelmitrea.model.VehicleOption;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class VehicleOptionService {

    @PersistenceContext(unitName = "cardealership")
    private EntityManager em;

    private List<VehicleOption> vehicleOptionList = new ArrayList<>();

    public void persist(VehicleOption vehicleOption) {
        vehicleOptionList.add(vehicleOption);
        em.persist(vehicleOption);
    }

    public List<VehicleOption> get(Long dealId) {
        return em.createQuery("select v from VehicleOption v where deal_id=" + dealId, VehicleOption.class).getResultList();
    }

    public List<VehicleOption> getVehicleOptionList() {
        return vehicleOptionList;
    }
}
