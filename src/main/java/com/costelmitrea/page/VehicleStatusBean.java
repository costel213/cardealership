package com.costelmitrea.page;

import com.costelmitrea.model.VehicleStatus;
import com.costelmitrea.service.VehicleStatusService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
@Named("vehicleStatusBean")
@Setter
@Getter
public class VehicleStatusBean {

    private List<VehicleStatus> vehicleStatusList;

    @Inject
    private VehicleStatusService vehicleStatusService;

    @PostConstruct
    public void init() {
        vehicleStatusList = new LinkedList<>();
        vehicleStatusList = vehicleStatusService.get();
    }
}
