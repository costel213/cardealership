package com.costelmitrea.dto;

public enum ContractStatus {

    done("DONE"),
    processing("PROCESSING"),
    denied("DENIED"),
    ;

    private String contractStatus;

    ContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }
}
