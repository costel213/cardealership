package com.costelmitrea.dto;

public enum VehicleType {

    suv("SUV"),
    electricCar("Electric Car");

    private String vehicleType;

    VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
