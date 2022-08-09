package com.costelmitrea.service;

import com.costelmitrea.model.Vehicle;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Stateless
public class VehicleService {

    @PersistenceContext(unitName = "cardealership")
    private EntityManager em;

    public List<Vehicle> get() {
        return em.createQuery("select v from Vehicle v", Vehicle.class).getResultList();
    }

    public List<Vehicle> filterVehicles(Map<String, Object> paramMap, List<String> whereClause) {
        StringBuilder queryBuilder = new StringBuilder("select v from Vehicle v ");
        queryBuilder.append(" where " + StringUtils.join(whereClause, " and "));
        Query query = em.createQuery(queryBuilder.toString(), Vehicle.class);
        for(String key : paramMap.keySet()) {
            query.setParameter(key, paramMap.get(key));
        }
        return query.getResultList();
    }

    public List<Vehicle> filterVehiclesByWord(Map<String, Object> paramMap, List<String> whereClause) {
        StringBuilder queryBuilder = new StringBuilder("select v from Vehicle v ");
        queryBuilder.append(" where " + StringUtils.join(whereClause, " or "));
        System.out.println(queryBuilder);
        Query query = em.createQuery(queryBuilder.toString(), Vehicle.class);
        for(String key : paramMap.keySet()) {
            query.setParameter(key, "%" + paramMap.get(key) + "%");
        }
        return query.getResultList();
    }
}
