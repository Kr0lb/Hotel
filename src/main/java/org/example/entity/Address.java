package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "adddresses")
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseNumber;

    private String street;

    private String city;

    private String country;

    private String postCode;

    @OneToOne
    private Hotel hotel;

}
