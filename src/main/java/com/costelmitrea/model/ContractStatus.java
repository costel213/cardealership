package com.costelmitrea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contract_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_status_id")
    private Long id;

    @Column(name = "contract_status")
    private String contractStatus;

    public ContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    @Override
    public String toString() {
        return "ContractStatus{" +
                "id=" + id +
                ", contractStatus='" + contractStatus + '\'' +
                '}';
    }
}
