package com.udea.graphqludea.Controller;

import com.udea.graphqludea.Entity.Aircraft;
import com.udea.graphqludea.Service.AircraftService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService){
        this.aircraftService=aircraftService;
    }

    @QueryMapping
    public List<Aircraft> allAircrafts(){
        return aircraftService.allAircrafts();
    }

    @QueryMapping
    public Aircraft aircraftById(@Argument Long id){
        return aircraftService.findAircraftById(id);
    }

    @MutationMapping
    public Aircraft createAircraft(@Argument String aircraftModel, @Argument Integer maxSeats, @Argument String seatConfiguration){
        return aircraftService.createAircraft(aircraftModel, maxSeats, seatConfiguration);
    }

    @MutationMapping
    public Aircraft updateAircraft(@Argument Long id, @Argument String aircraftModel, @Argument Integer maxSeats, @Argument String seatConfiguration){
        return aircraftService.updateAircraft(id, aircraftModel, maxSeats, seatConfiguration);
    }

    @MutationMapping
    public Boolean deleteAircraft(@Argument Long id){
        aircraftService.deleteAircraft(id);
        return true;
    }
}
