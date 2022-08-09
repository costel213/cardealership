package com.costelmitrea.page;

import com.costelmitrea.model.VehicleType;
import com.costelmitrea.service.VehicleTypeService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
@Named("vehicleTypeBean")
@Setter
@Getter
public class VehicleTypeBean {

    private List<VehicleType> vehicleTypeList;

    @Inject
    private VehicleTypeService vehicleTypeService;

    @PostConstruct
    public void init() {
        vehicleTypeList = new LinkedList<>();
        vehicleTypeList = vehicleTypeService.get();
    }
}
