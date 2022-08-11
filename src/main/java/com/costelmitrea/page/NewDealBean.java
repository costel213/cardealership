package com.costelmitrea.page;

import com.costelmitrea.model.*;
import com.costelmitrea.service.*;
import com.lowagie.text.DocumentException;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

@Named("newDealBean")
@SessionScoped
@Setter
@Getter
public class NewDealBean implements Serializable {

    @Inject
    private VehicleService vehicleService;

    @Inject
    private DealerService dealerService;

    @Inject
    private VehicleOptionService vehicleOptionService;

    @Inject
    private DealService dealService;

    @Inject
    private ContractStatusService contractStatusService;

    private Vehicle selectedVehicle;
    private Long selectedDealer;
    private Long selectedVehicleBrand;
    private Long selectedVehicleType;
    private Long selectedVehicleStatus;
    private String inputVehicleName;

    private Dealer dealer;

    private String inputCustomerFirstName;
    private String inputCustomerLastName;
    private String inputCountry;
    private String inputState;
    private String inputCity;
    private String inputStreet;
    private String inputNumber;
    private String inputZipcode;

    private String inputOptionName;
    private String inputOptionDescription;
    private String inputOptionSupplier;
    private double inputOptionPrice;

    private VehicleOption selectedVehicleOption;

    private List<VehicleOption> vehicleOptionList;

    private List<VehicleOption> selectedVehicleOptions = new LinkedList<>();

    private List<Vehicle> vehicles;

    private boolean netValue;

    private int selectedPeriod;
    private double monthlyPayment;
    private double inputInterestRate;

    private double vehicleOptionsTotalPrice = 0L;
    private double dealTotalValue;
    private double totalValueWithRate;

    private List<Integer> periodList = new ArrayList<>();

    private List<Deal> dealList = new LinkedList<>();

    @PostConstruct
    public void init() {
        vehicles = vehicleService.get();
        periodList.add(1);
        periodList.add(2);
        periodList.add(3);
        periodList.add(4);
        periodList.add(5);
    }

    public void openNew() {
        if(vehicleOptionList == null) {
            vehicleOptionList = new LinkedList<>();
        }
        this.selectedVehicleOption = new VehicleOption();
    }

    public void deleteVehicleOption() {
        this.vehicleOptionList.remove(this.selectedVehicleOption);
        this.vehicleOptionsTotalPrice -= this.selectedVehicleOption.getPrice();
        this.dealTotalValue -= this.selectedVehicleOption.getPrice();
        this.totalValueWithRate = this.dealTotalValue * (1 + this.inputInterestRate / 100);
        this.selectedVehicleOption = null;
        this.monthlyPayment = (dealTotalValue / (selectedPeriod * 12)) * (1 + inputInterestRate / 100);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vehicle Option Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-vehicleOptions");
        PrimeFaces.current().ajax().update("form:messages", "form:vehicleOptionsPriceComponent");
        PrimeFaces.current().ajax().update("form:messages", "form:dealTotalValue");
        PrimeFaces.current().ajax().update("form:messages", "form:dealPaymentDetails");
    }

    public void openDialogWindow() {
        dealTotalValue = selectedVehicle.getValue();
        PrimeFaces.current().executeScript("PF('dlg3').show()");
    }

    public String onDealerChosen() throws IOException {
        dealer = dealerService.find(selectedDealer);
        PrimeFaces.current().executeScript("PF('dlg').hide()");
        return "dealDetails?faces-redirect=true";
    }

    public void saveVehicleOption() {
        if(this.selectedVehicleOption.getCode() == null) {
            this.selectedVehicleOption.setCode(UUID.randomUUID().toString().substring(0, 5));
            this.vehicleOptionList.add(this.selectedVehicleOption);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vehicle Option Added"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vehicle Option Updated"));
        }
        dealTotalValue = selectedVehicle.getValue();
        vehicleOptionsTotalPrice = 0L;
        for(VehicleOption vehicleOption : vehicleOptionList) {
            this.dealTotalValue += vehicleOption.getPrice();
            this.vehicleOptionsTotalPrice += vehicleOption.getPrice();
        }
        this.totalValueWithRate = this.dealTotalValue * (1 + this.inputInterestRate / 100);
        this.monthlyPayment = (dealTotalValue / (selectedPeriod * 12)) * (1 + inputInterestRate / 100);
        PrimeFaces.current().executeScript("PF('manageVehicleOptionDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-vehicleOptions");
        PrimeFaces.current().ajax().update("form:messages", "form:vehicleOptionsPriceComponent");
        PrimeFaces.current().ajax().update("form:messages", "form:dealTotalValue");
        PrimeFaces.current().ajax().update("form:messages", "form:dealPaymentDetails");
    }

    public void delete() {
        addMessage("Confirmed", "Record deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addDeal() throws DocumentException, IOException {
        Address customerAddress = new Address(inputCountry, inputState, inputCity, inputStreet, inputNumber, inputZipcode);
        Customer customer = new Customer(inputCustomerFirstName, inputCustomerLastName, customerAddress);
        Deal deal = new Deal();
        deal.setDealer(dealer);
        deal.setCustomer(customer);
        deal.setVehicle(selectedVehicle);
        String userId = UUID.randomUUID().toString().substring(0,5);
        deal.setUserId(userId);
        deal.setSellerNumber(UUID.randomUUID().toString().substring(0, 5));
        deal.setLeadId(UUID.randomUUID().toString().substring(0, 5));
        ContractStatus contractStatus = contractStatusService.getById(1L);
        deal.setContractStatus(contractStatus);
        deal.setContractNumber(UUID.randomUUID().toString().substring(0, 5));

        double optionsAmount = 0d;
        if(vehicleOptionList != null && !vehicleOptionList.isEmpty()) {
            for(VehicleOption vehicleOption : vehicleOptionList) {
                vehicleOption.setDeal(deal);
                optionsAmount += vehicleOption.getPrice();
            }
        }

        deal.setVehicleOptions(vehicleOptionList);
        deal.setVehicleOptionsValue(optionsAmount);
        deal.setPeriod(selectedPeriod);
        deal.setInterestRate(inputInterestRate);
        deal.setMonthlyPayment(monthlyPayment);
        deal.setDealTotalValue(totalValueWithRate);
        dealService.persist(deal);
        dealList.add(deal);
        FacesContext.getCurrentInstance().getExternalContext().redirect("dealOverview.xhtml");
        this.createPDF();
//        this.vehicleOptionList = null;
    }

    public void filterVehicles() {
        Map<String, Object> paramMap = new HashMap<>();
        List<String> whereClause = new ArrayList<>();
        if(selectedVehicleBrand != null) {
            whereClause.add(" v.vehicleBrand.id=:brand ");
            paramMap.put("brand", selectedVehicleBrand);
        }
        if(selectedVehicleType != null) {
            whereClause.add(" v.type.id=:type ");
            paramMap.put("type", selectedVehicleType);
        }
        if(selectedVehicleStatus != null) {
            whereClause.add(" v.status.id=:status ");
            paramMap.put("status", selectedVehicleStatus);
        }

        if(paramMap.isEmpty() && whereClause.isEmpty()) {
            vehicles = vehicleService.get();
        } else {
            vehicles = vehicleService.filterVehicles(paramMap, whereClause);
        }
    }

    public void filterVehiclesByWord() {
        Map<String, Object> paramMap = new HashMap<>();
        List<String> whereClause = new ArrayList<>();
        if(!inputVehicleName.isEmpty()) {
            whereClause.add(" v.vehicleBrand.brandName like :brandName ");
            paramMap.put("brandName", inputVehicleName);
            whereClause.add(" v.model like :vehicleModel ");
            paramMap.put("vehicleModel", inputVehicleName);
        }

        if(paramMap.isEmpty() && whereClause.isEmpty()) {
            vehicles = vehicleService.get();
        } else {
            vehicles = vehicleService.filterVehiclesByWord(paramMap, whereClause);
        }
    }

    public void showMessage() {
        String summary = netValue ? "Net Value" : "Gross Value";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void calculateMonthlyPayment() {
        this.monthlyPayment = (dealTotalValue / (selectedPeriod * 12)) * (1 + inputInterestRate / 100);
        this.totalValueWithRate = this.dealTotalValue * (1 + this.inputInterestRate / 100);
    }

    public void createPDF() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String url = "http://127.0.0.1:8080/cardealership-1.0-SNAPSHOT/cardealership/newDealPage.xhtml" + "?pdf=true";
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());
            renderer.layout();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"printfile.pdf\"");
            OutputStream outputStream = response.getOutputStream();
            renderer.createPDF(outputStream);
        } catch(Exception e) {
            e.printStackTrace();
        }
        facesContext.responseComplete();
    }
}
