package com.costelmitrea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_status")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_status_id")
    private Long id;

    @Column(name = "vehicle_status")
    private String vehicleStatus;

    public VehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    @Override
    public String toString() {
        return "VehicleStatus{" +
                "id=" + id +
                ", vehicleStatus='" + vehicleStatus + '\'' +
                '}';
    }
}
