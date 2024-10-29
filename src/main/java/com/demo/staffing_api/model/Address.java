package com.demo.staffing_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @Column(name = "addr_id")
    private Long id;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private  String zipCode;

    @OneToOne
    @MapsId
    @JoinColumn(name = "addr_id")
    private Employee employee;
}
