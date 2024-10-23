package com.udea.graphqludea.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

    @Entity
    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "flight")
    public class Flight {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String flightNumber;
        private String flightType;
        private String departureCity;
        private String destinationCity;
        @ManyToOne
        @JoinColumn(name = "aircraft_id", nullable = false)
        private Aircraft aircraft;
        private LocalDate departureDate;
        private LocalDate arrivalDate;
        private LocalTime departureTime;
        private LocalTime arrivalTime;
        private Float price;
        private Float taxPercentage;
        private Float surcharge;
    }
