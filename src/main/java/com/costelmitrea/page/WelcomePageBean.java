package com.costelmitrea.page;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("welcomePageBean")
@ApplicationScoped
@Setter
@Getter
public class WelcomePageBean {

    private String title;
    private String indexTitle;
    private String newDealTitle;
    private String message;

    @PostConstruct
    public void init() {
        title = "Buy a Car";
        indexTitle = "All Deals";
        newDealTitle = "Select vehicle";
    }

    public void search() {
        message = "Button pressed";
    }
}