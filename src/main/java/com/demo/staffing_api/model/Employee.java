package com.demo.staffing_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column
    @NonNull
    private String firstName;

    @Column
    @NonNull
    private String lastName;

    @Column
    @NonNull
    private String email;

    @Column
    @NonNull
    private Integer age;

    @Column
    @NonNull
    private String gender;

    @Column
    private Integer managerId;

    @Column
    @NonNull
    private Double salary;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
        this.address.setEmployee(this);
    }
}
