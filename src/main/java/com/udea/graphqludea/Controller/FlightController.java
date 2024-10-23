package com.udea.graphqludea.Controller;

import com.udea.graphqludea.Entity.Flight;
import com.udea.graphqludea.Service.FlightService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @QueryMapping
    public List<Flight> allFlights(){
        return flightService.allFlights();
    }

    @QueryMapping
    public Flight flightById(Long id){
        return flightService.findFlightById(id);
    }

    @MutationMapping
    public Flight createFlight(@Argument String flightNumber, @Argument String flightType, @Argument String departureCity, @Argument String destinationCity, @Argument Long aircraftId, @Argument String departureDate, @Argument String arrivalDate, @Argument String departureTime, @Argument String arrivalTime, @Argument Float price, @Argument Float taxPercentage, @Argument Float surcharge){
        LocalDate depDate = LocalDate.parse(departureDate);
        LocalDate arrDate = LocalDate.parse(arrivalDate);
        LocalTime depTime = LocalTime.parse(departureTime);
        LocalTime arrTime = LocalTime.parse(arrivalTime);
        return flightService.createFlight(flightNumber, flightType, departureCity, destinationCity, aircraftId, depDate, arrDate, depTime, arrTime, price, taxPercentage, surcharge);
    }

    @MutationMapping
    public Flight updateFlight(@Argument Long id, @Argument String flightNumber, @Argument String flightType, @Argument String departureCity, @Argument String destinationCity, @Argument Long aircraftId, @Argument String departureDate, @Argument String arrivalDate, @Argument String departureTime, @Argument String arrivalTime, @Argument Float price, @Argument Float taxPercentage, @Argument Float surcharge){
        LocalDate depDate = LocalDate.parse(departureDate);
        LocalDate arrDate = LocalDate.parse(arrivalDate);
        LocalTime depTime = LocalTime.parse(departureTime);
        LocalTime arrTime = LocalTime.parse(arrivalTime);
        return flightService.updateFlight(id, flightNumber, flightType, departureCity, destinationCity, aircraftId, depDate, arrDate, depTime, arrTime, price, taxPercentage, surcharge);
    }

    @MutationMapping
    public Boolean deleteFlight(@Argument Long id){
        flightService.deleteFlight(id);
        return true;
    }
}
