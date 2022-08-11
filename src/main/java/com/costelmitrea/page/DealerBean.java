package com.costelmitrea.page;

import com.costelmitrea.model.Dealer;
import com.costelmitrea.service.DealerService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
@Named("dealerBean")
@Setter
@Getter
public class DealerBean {

    private List<Dealer> dealers;

    @Inject
    private DealerService dealerService;

    @PostConstruct
    public void init() {
        dealers = new LinkedList<>();
        dealers = dealerService.get();
    }
}
