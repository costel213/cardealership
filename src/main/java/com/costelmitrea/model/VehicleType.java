package com.costelmitrea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_type")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_type_id")
    private Long id;

    @Column(name = "vehicle_type")
    private String vehicleType;

    public VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "id=" + id +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }
}
