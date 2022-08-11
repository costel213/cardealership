package com.costelmitrea.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "First Name is required")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_address", referencedColumnName = "address_id")
    private Address address;

    public Customer(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                '}';
    }
}
