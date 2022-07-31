package com.costelmitrea.page;

import com.costelmitrea.dto.ContractStatus;
import com.costelmitrea.dto.Dealer;
import com.costelmitrea.dto.Vehicle;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.time.LocalDate;

@Named("dealSearch")
@RequestScoped
@Getter
@Setter
public class DealSearchBean {

    private Dealer selectedDealer;
    private String inputUserId;
    private String inputSellerNumber;
    private String inputLeadId;
    private LocalDate selectedDate;
    private ContractStatus selectedContractStatus;
    private String inputCustomerLastName;
    private String inputCustomerFirstName;
    private String inputCompanyName;
    private Vehicle selectedVehicle;
    private String inputContractNumber;

    private String message;

    public void search() {
        message =  inputUserId;
        System.out.println(message);
    }
}
