package com.costelmitrea.service;

import com.costelmitrea.model.Dealer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DealerDao {

    @PersistenceContext(unitName = "cardealership")
    private EntityManager em;

    public List<Dealer> get() {
        return em.createQuery("select d from Dealer d", Dealer.class).getResultList();
    }

    public Dealer find(Long id) {
        return em.createQuery("select d from Dealer d where d.id=" + id, Dealer.class).getSingleResult();
    }
}
