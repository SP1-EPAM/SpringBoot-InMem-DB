package com.demo.staffing_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;
}
