package com.costelmitrea.service;

import com.costelmitrea.model.Deal;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Stateless
public class DealService {

    @PersistenceContext(unitName = "cardealership")
    private EntityManager em;

    public List<Deal> get() {
        return em.createQuery("SELECT d FROM Deal d", Deal.class).getResultList();
    }

    public Deal find(Long id) {
        return em.createQuery("select d from Deal d where d.id=" + id, Deal.class).getSingleResult();
    }

    public Deal getReference(String userId) {
        return em.getReference(Deal.class, userId);
    }

    public Deal findByUserId(String userId) {
        return em.createQuery("select d from Deal d where d.userId like " + userId, Deal.class).getSingleResult();
    }

    public void update(Deal deal) {
        em.merge(deal);
    }

    public void persist(Deal deal) {
        em.persist(deal);
    }

    public List<Deal> filterDeals(Map<String, Object> paramMap, List<String> whereClause) {
        StringBuilder queryBuilder = new StringBuilder("select d from Deal d ");
        queryBuilder.append(" where " + StringUtils.join(whereClause, " and "));
        Query query = em.createQuery(queryBuilder.toString(), Deal.class);
        for(String key : paramMap.keySet()) {
            if(key.equals("dealer") || key.equals("dateCreated") || key.equals("vehicle") || key.equals("contractStatus")) {
                query.setParameter(key, paramMap.get(key));
            } else {
                query.setParameter(key, "%" + paramMap.get(key) + "%");
            }
        }
        return query.getResultList();
    }
}
