package com.costelmitrea.model;

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

    @OneToOne
    @JoinColumn(name = "vehicle_brand", referencedColumnName = "brand_id")
    private Brand vehicleBrand;

    @Column(name = "vehicle_model")
    private String model;

    @Column(name = "model_series")
    private String modelSeries;

    @OneToOne
    @JoinColumn(name = "emission_standard", referencedColumnName = "emission_standard_id")
    private EmissionStandard emissionStandard;

    @OneToOne
    @JoinColumn(name = "type", referencedColumnName = "vehicle_type_id")
    private VehicleType type;

    @OneToOne
    @JoinColumn(name = "status", referencedColumnName = "vehicle_status_id")
    private VehicleStatus status;

    @Column(name = "production_year")
    private LocalDate productionYear;

    @Column(name = "vehicle_value")
    private double value;

    public Vehicle(Brand vehicleBrand, String model, String modelSeries, EmissionStandard emissionStandard,
                   VehicleType type, VehicleStatus status, LocalDate productionYear, double value) {
        this.vehicleBrand = vehicleBrand;
        this.model = model;
        this.modelSeries = modelSeries;
        this.emissionStandard = emissionStandard;
        this.type = type;
        this.status = status;
        this.productionYear = productionYear;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vehicleBrand=" + vehicleBrand +
                ", model='" + model + '\'' +
                ", modelSeries='" + modelSeries + '\'' +
                ", emissionStandard=" + emissionStandard +
                ", type=" + type +
                ", status=" + status +
                ", productionYear=" + productionYear +
                ", value=" + value +
                '}';
    }
}
