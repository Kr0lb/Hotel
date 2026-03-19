package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "hotels")
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String brand;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne
    private Contact contacts;

    @OneToOne(cascade = CascadeType.ALL)
    private ArrivalTime arrivalTime;

    @OneToMany(mappedBy = "hotel")
    private List<Amenities> amenities;

}
