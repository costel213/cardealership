package com.costelmitrea.page;

import com.costelmitrea.model.Brand;
import com.costelmitrea.service.BrandService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
@Named("brandBean")
@Setter
@Getter
public class BrandBean {

    private List<Brand> brands;

    @Inject
    private BrandService brandService;

    @PostConstruct
    public void init() {
        brands = new LinkedList<>();
        brands = brandService.get();
    }
}
