package com.costelmitrea.page;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("welcomePageBean")
@RequestScoped
@Setter
@Getter
public class WelcomePageBean {

    private String title;
    private String message;

    @PostConstruct
    public void init() {
        title = "Buy a Car";
    }

    public void search() {
        message = "Button pressed" ;
    }
}