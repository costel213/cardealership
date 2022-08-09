package com.costelmitrea.page;

import com.costelmitrea.model.ContractStatus;
import com.costelmitrea.service.ContractStatusService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
@Named("contractStatusBean")
@Setter
@Getter
public class ContractStatusBean {

    private List<ContractStatus> contractStatusList;

    @Inject
    private ContractStatusService contractStatusService;

    @PostConstruct
    public void init() {
        contractStatusList = new LinkedList<>();
        contractStatusList = contractStatusService.get();
    }
}
