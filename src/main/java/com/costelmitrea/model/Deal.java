package com.costelmitrea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

    @OneToOne()
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

    @OneToOne
    @JoinColumn(name = "contract_status", referencedColumnName = "contract_status_id")
    private ContractStatus contractStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToOne()
    @JoinColumn(name = "vehicle", referencedColumnName = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "deal_id")
    private List<VehicleOption> vehicleOptions;

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "vehicle_options_value")
    private double vehicleOptionsValue;

    @Column(name = "period")
    private int period;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "monthly_payment")
    private double monthlyPayment;

    @Column(name = "deal_total_value")
    private double dealTotalValue;

    public Deal(Dealer dealer, Customer customer, Vehicle vehicle,
                List<VehicleOption> vehicleOptions) {
        this.dealer = dealer;
        this.customer = customer;
        this.vehicle = vehicle;
        this.vehicleOptions = vehicleOptions;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", dealer=" + dealer +
                ", userId='" + userId + '\'' +
                ", sellerNumber='" + sellerNumber + '\'' +
                ", leadId='" + leadId + '\'' +
                ", dateCreated=" + dateCreated +
                ", contractStatus=" + contractStatus +
                ", customer=" + customer +
                ", vehicle=" + vehicle +
                ", contractNumber='" + contractNumber + '\'' +
                '}';
    }
}
