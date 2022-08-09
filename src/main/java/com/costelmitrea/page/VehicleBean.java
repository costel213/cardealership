package com.costelmitrea.page;

import com.costelmitrea.model.Vehicle;
import com.costelmitrea.service.VehicleService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named("vehicleBean")
@Setter
@Getter
public class VehicleBean {

    private List<Vehicle> vehicles;

    @Inject
    private VehicleService vehicleService;

    @PostConstruct
    public void initVehiclesList() {
        vehicles = new ArrayList<>();
        vehicles = vehicleService.get();
    }
}
