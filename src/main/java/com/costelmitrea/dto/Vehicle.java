package com.costelmitrea.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vehicle")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "model_series")
    private String modelSeries;

    @Column(name = "emission_standard")
    private String emissionStandard;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @Column(name = "production_year")
    private LocalDate productionYear;

    @Column(name = "value")
    private double value;

    public Vehicle(String brand, String model, String modelSeries, String emissionStandard, VehicleType type,
                   VehicleStatus status, LocalDate productionYear, double value) {
        this.brand = brand;
        this.model = model;
        this.modelSeries = modelSeries;
        this.emissionStandard = emissionStandard;
        this.type = type;
        this.status = status;
        this.productionYear = productionYear;
        this.value = value;
    }
}
