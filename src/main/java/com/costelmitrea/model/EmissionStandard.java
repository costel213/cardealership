package com.costelmitrea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "emission_standard")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmissionStandard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emission_standard_id")
    private Long id;

    @Column(name = "emission_standard")
    private String emissionStandard;

    public EmissionStandard(String emissionStandard) {
        this.emissionStandard = emissionStandard;
    }

    @Override
    public String toString() {
        return "EmissionStandard{" +
                "id=" + id +
                ", emissionStandard='" + emissionStandard + '\'' +
                '}';
    }
}
