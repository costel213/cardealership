package com.costelmitrea.dto;

public enum VehicleStatus {

    newVehicle("NEW"),

    used("USED");

    private String vehicleStatus;

    VehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}
