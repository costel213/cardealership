package com.costelmitrea.dao;

import com.costelmitrea.dto.*;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
@Named("dealDao")
@Getter
@Setter
public class DealDAO {

    private List<Dealer> dealers;
    private List<Vehicle> vehicles;
    private List<ContractStatus> contractStatusList;
    private List<Deal> deals;

    private List<String> vehicleBrands;
    private List<VehicleType> vehicleTypes;
    private List<VehicleStatus> vehicleStatusList;

    @PostConstruct
    public void loadDeals() {
        Address dealerAddress = new Address("Germany", "Bavaria", "Munich", "Main",
                "25", "58585");

        Dealer dealer1 = new Dealer(1L, "Dealer 1", dealerAddress);
        Dealer dealer2 = new Dealer(2L, "Dealer 2", dealerAddress);
        Dealer dealer3 = new Dealer(3L, "Dealer 3", dealerAddress);

        dealers = new LinkedList<>();
        dealers.add(dealer1);
        dealers.add(dealer2);
        dealers.add(dealer3);

        Vehicle dacia = new Vehicle(1L,"Dacia", "Duster", "DD100", "EURO 6",
                VehicleType.suv, VehicleStatus.newVehicle, LocalDate.of(2021, Month.AUGUST, 30), 19_123);
        Vehicle volvo = new Vehicle(2L,"Volvo", "XC60", "VX260", "EURO 6",
                VehicleType.suv, VehicleStatus.newVehicle, LocalDate.of(2020, Month.JUNE, 2), 70_285);
        Vehicle audi = new Vehicle(3L,"Audi", "e-tron", "AE22", "EV",
                VehicleType.electricCar, VehicleStatus.newVehicle, LocalDate.of(2021, Month.NOVEMBER, 4), 110_234);

        vehicles = new LinkedList<>();
        vehicles.add(dacia);
        vehicles.add(volvo);
        vehicles.add(audi);

        Address customerAddress = new Address("Germany", "Baden", "Baden", "Random",
                "78", "9023867");
        Customer customer = new Customer("John", "Doe", customerAddress);

        Deal deal1 = new Deal(dealer1, "FE2746", "GT34", "1234",
                LocalDate.of(2021, Month.APRIL, 23), ContractStatus.done, customer, dacia, "YU59");
        Deal deal2 = new Deal(2L, dealer2, "TO0258", "BN90", "475",
                LocalDate.of(2020, Month.DECEMBER, 1), ContractStatus.done, customer, volvo, "QE66");
        Deal deal3 = new Deal(3L, dealer3, "FF99", "CI45", "987",
                LocalDate.now(), ContractStatus.processing, customer, audi, "TU234");

        deals = new LinkedList<>();
        deals.add(deal1);
        deals.add(deal2);
        deals.add(deal3);

        contractStatusList = new LinkedList<>();
        contractStatusList.add(ContractStatus.done);
        contractStatusList.add(ContractStatus.processing);
        contractStatusList.add(ContractStatus.denied);

        vehicleBrands = new LinkedList<>();
        vehicleBrands.add(dacia.getBrand());
        vehicleBrands.add(volvo.getBrand());
        vehicleBrands.add(audi.getBrand());

        vehicleTypes = new LinkedList<>();
        vehicleTypes.add(VehicleType.suv);
        vehicleTypes.add(VehicleType.electricCar);

        vehicleStatusList = new LinkedList<>();
        vehicleStatusList.add(VehicleStatus.newVehicle);
        vehicleStatusList.add(VehicleStatus.used);

        System.out.println("Deals loaded");
    }
}
