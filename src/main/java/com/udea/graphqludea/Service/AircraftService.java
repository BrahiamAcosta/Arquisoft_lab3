package com.udea.graphqludea.Service;

import com.udea.graphqludea.Entity.Aircraft;
import com.udea.graphqludea.Repository.AircraftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService{
    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public List<Aircraft> allAircrafts() {
        return aircraftRepository.findAll();
    }

    public Aircraft findAircraftById(Long id){
        return aircraftRepository.findById(id).orElseThrow(() -> new RuntimeException("Aircraft not found"));
    }

    public Aircraft createAircraft(String aircraftModel, Integer maxSeats, String seatConfiguration){
        Aircraft aircraft = new Aircraft();
        aircraft.setAircraftModel(aircraftModel);
        aircraft.setMaxSeats(maxSeats);
        aircraft.setSeatConfiguration(seatConfiguration);
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id, String aircraftModel, Integer maxSeats, String seatConfiguration){
        Aircraft aircraft = findAircraftById(id);
        if(aircraft != null){
            if(aircraftModel != null){
                aircraft.setAircraftModel(aircraftModel);
            }
            if (maxSeats != null){
                aircraft.setMaxSeats(maxSeats);
            }
            if (seatConfiguration != null){
                aircraft.setSeatConfiguration(seatConfiguration);
            }
            return aircraftRepository.save(aircraft);
        }else{
            throw new RuntimeException("Aircraft not found");
        }
    }

    public void deleteAircraft(Long id){
        if(!aircraftRepository.existsById(id)){
            throw new RuntimeException("Aircraft not found");
        }
        aircraftRepository.deleteById(id);
    }
}
