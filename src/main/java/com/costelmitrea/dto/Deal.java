package com.costelmitrea.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "deal")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Deal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_id", referencedColumnName = "dealer_id")
    private Dealer dealer;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "seller_id")
    private String sellerNumber;

    @Column(name = "lead_id")
    private String leadId;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDate dateCreated;

    @Column(name = "contract_status")
    private ContractStatus contractStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle", referencedColumnName = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "contract_number")
    private String contractNumber;

    public Deal(Dealer dealer, String userId, String sellerNumber, String leadId, LocalDate dateCreated,
                ContractStatus contractStatus, Customer customer, Vehicle vehicle, String contractNumber) {
        this.dealer = dealer;
        this.userId = userId;
        this.sellerNumber = sellerNumber;
        this.leadId = leadId;
        this.dateCreated = dateCreated;
        this.contractStatus = contractStatus;
        this.customer = customer;
        this.vehicle = vehicle;
        this.contractNumber = contractNumber;
    }
}
