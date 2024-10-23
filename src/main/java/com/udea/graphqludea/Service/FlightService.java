package com.udea.graphqludea.Service;

import com.udea.graphqludea.Entity.Aircraft;
import com.udea.graphqludea.Entity.Flight;
import com.udea.graphqludea.Repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;;
    private final AircraftService aircraftService;

    public FlightService(FlightRepository flightRepository, AircraftService aircraftService) {
        this.flightRepository = flightRepository;
        this.aircraftService = aircraftService;
    }

    public List<Flight> allFlights(){
        return flightRepository.findAll();
    }

    public Flight findFlightById(Long id){
        return flightRepository.findById(id).orElseThrow(()-> new RuntimeException("Flight not found"));
    }

    public Flight createFlight(String flightNumber, String flightType, String departureCity, String destinationCity,
                               Long aircraftId, LocalDate departureDate, LocalDate arrivalDate, LocalTime departureTime,
                               LocalTime arrivalTime, Float price, Float taxPercentage, Float surcharge){
        Aircraft aircraft = aircraftService.findAircraftById(aircraftId);
        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setFlightType(flightType);
        flight.setDepartureCity(departureCity);
        flight.setDestinationCity(destinationCity);
        flight.setAircraft(aircraft);
        flight.setDepartureDate(departureDate);
        flight.setArrivalDate(arrivalDate);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setPrice(price);
        flight.setTaxPercentage(taxPercentage);
        flight.setSurcharge(surcharge);
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, String flightNumber, String flightType, String departureCity, String destinationCity,
                               Long aircraftId, LocalDate departureDate, LocalDate arrivalDate, LocalTime departureTime,
                               LocalTime arrivalTime, Float price, Float taxPercentage, Float surcharge){
        Flight flight = findFlightById(id);
            if(flightNumber != null){
                flight.setFlightNumber(flightNumber);
            }
            if(flightType != null){
                flight.setFlightType(flightType);
            }
            if(departureCity != null){
                flight.setDepartureCity(departureCity);
            }
            if(destinationCity != null){
                flight.setDestinationCity(destinationCity);
            }
            if(aircraftId != null){
                Aircraft aircraft = aircraftService.findAircraftById(aircraftId);
                if(!aircraft.equals(flight.getAircraft())){
                    flight.getAircraft().getFlights().remove(flight);
                    flight.setAircraft(aircraft);
                    aircraft.getFlights().add(flight);
                }
            }
            if(departureDate != null){
                flight.setDepartureDate(departureDate);
            }
            if(arrivalDate != null){
                flight.setArrivalDate(arrivalDate);
            }
            if(departureTime != null){
                flight.setDepartureTime(departureTime);
            }
            if(arrivalTime != null){
                flight.setArrivalTime(arrivalTime);
            }
            if(price != null){
                flight.setPrice(price);
            }
            if(taxPercentage != null){
                flight.setTaxPercentage(taxPercentage);
            }
            if(surcharge != null){
                flight.setSurcharge(surcharge);
            }
            return flightRepository.save(flight);

    }

    public void deleteFlight(Long id){
        if(!flightRepository.existsById(id)){
            throw new RuntimeException("Flight not found");
        }
        flightRepository.deleteById(id);
    }
}
