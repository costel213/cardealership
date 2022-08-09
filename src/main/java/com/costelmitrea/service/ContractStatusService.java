package com.costelmitrea.service;

import com.costelmitrea.model.ContractStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ContractStatusService {

    @PersistenceContext(unitName = "cardealership")
    private EntityManager em;

    public List<ContractStatus> get() {
        return em.createQuery("select c from ContractStatus c", ContractStatus.class).getResultList();
    }

    public ContractStatus getById(Long id) {
        return em.createQuery("select c from ContractStatus c where c.id=" + id,ContractStatus.class).getSingleResult();
    }
}
