package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "arrival_times")
@AllArgsConstructor
public class ArrivalTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime checkIn;

    private LocalTime checkOut;

    @OneToOne
    private Hotel hotel;

}
