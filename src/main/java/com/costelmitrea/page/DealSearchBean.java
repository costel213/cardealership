package com.costelmitrea.page;

import com.costelmitrea.model.Deal;
import com.costelmitrea.service.DealService;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("dealSearch")
@RequestScoped
@Getter
@Setter
public class DealSearchBean implements Serializable {

    @Inject
    private DealService dealService;

    private Long selectedDealer;
    private String inputUserId;
    private String inputSellerNumber;
    private String inputLeadId;
    private LocalDate selectedDate;
    private Long selectedContractStatus;
    private String inputCustomerLastName;
    private String inputCustomerFirstName;
    private String inputCompanyName;
    private Long selectedVehicle;
    private String inputContractNumber;

    private String message;

    private List<Deal> deals;

    public void filterDeals() {
        Map<String, Object> parameterMap = new HashMap<>();
        List<String> whereClause = new ArrayList<>();
        if(selectedDealer != null) {
            whereClause.add(" d.dealer.id=:dealer ");
            parameterMap.put("dealer", selectedDealer);
        }
        if(!inputUserId.isEmpty()) {
            whereClause.add(" d.userId like :userId ");
            parameterMap.put("userId", inputUserId);
        }
        if(!inputSellerNumber.isEmpty()) {
            whereClause.add(" d.sellerNumber like :sellerNumber ");
            parameterMap.put("sellerNumber", inputSellerNumber);
        }
        if(!inputLeadId.isEmpty()) {
            whereClause.add(" d.leadId like :leadId ");
            parameterMap.put("leadId", inputLeadId);
        }
        if(selectedDate != null) {
            whereClause.add(" d.dateCreated=:dateCreated ");
            parameterMap.put("dateCreated", selectedDate);
        }
        if(selectedContractStatus != null) {
            whereClause.add(" d.contractStatus.id=:contractStatus ");
            parameterMap.put("contractStatus", selectedContractStatus);
        }
        if(!inputCustomerLastName.isEmpty()) {
            whereClause.add(" d.customer.lastName like :customerLastName ");
            parameterMap.put("customerLastName", inputCustomerLastName);
        }
        if(!inputCustomerFirstName.isEmpty()) {
            whereClause.add(" d.customer.firstName like :customerFirstName ");
            parameterMap.put("customerFirstName", inputCustomerFirstName);
        }
        if(selectedVehicle != null) {
            whereClause.add(" d.vehicle.id=:vehicle ");
            parameterMap.put("vehicle", selectedVehicle);
        }
        if(!inputContractNumber.isEmpty()) {
            whereClause.add(" d.contractNumber like :contractNumber ");
            parameterMap.put("contractNumber", inputContractNumber);
        }

        if(parameterMap.isEmpty() && whereClause.isEmpty()) {
            deals = dealService.get();
        } else {
            deals = dealService.filterDeals(parameterMap, whereClause);
        }
    }
}
