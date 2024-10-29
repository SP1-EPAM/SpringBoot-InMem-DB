package com.demo.staffing_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
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
}
