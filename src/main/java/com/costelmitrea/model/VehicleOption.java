package com.costelmitrea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_option")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_option_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "price")
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deal_id")
    private Deal deal;

    @Column(name = "vehicle_option_code")
    private String code;

    public VehicleOption(String name, String description, String supplier, double price) {
        this.name = name;
        this.description = description;
        this.supplier = supplier;
        this.price = price;
    }

    @Override
    public String toString() {
        return "VehicleOption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", supplier='" + supplier + '\'' +
                ", price=" + price +
                '}';
    }
}
