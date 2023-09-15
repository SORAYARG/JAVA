package com.backend.trip.infraestructure.controller;

import com.backend.trip.application.TripService;
import com.backend.trip.infraestructure.dto.TripInputDto;
import com.backend.trip.infraestructure.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    TripService tripService;

    @PostMapping
    public TripOutputDto addTrip(@RequestBody TripInputDto tripInputDto) {
        return tripService.addTrip(tripInputDto);
    }

    @GetMapping
    public List<TripOutputDto> findAll() {
        return tripService.getAllTrip();
    }

    @GetMapping("/{id}")
    public TripOutputDto getTripById(@PathVariable Integer id) {
        return tripService.getTripById(id);
    }

    @GetMapping("/passenger/count/{id}")
    public ResponseEntity<Integer> countPassengers(@PathVariable Integer id){
        return new ResponseEntity<>(tripService.countPassengers(id), HttpStatus.OK);
    }

    @GetMapping("/status/{trip_id}/{status}")
    public TripOutputDto changeStatus(@PathVariable Integer trip_id, @PathVariable Boolean status){
        return tripService.changeStatus(trip_id, status);
    }

    @GetMapping("/verify/{idTrip}")
    public ResponseEntity<?> checkStatus(@PathVariable("idTrip") Integer idTrip){
        return ResponseEntity.ok().body(tripService.checkStatus(idTrip));
    }

    @PutMapping("/{id}")
    public TripOutputDto updateTrip(@PathVariable Integer id, @RequestBody TripInputDto tripInputDto) {
        return tripService.updateTrip(id, tripInputDto);
    }

    @PutMapping("/addPassenger/{idTrip}/{idClient}")
    public TripOutputDto addPassenger(@PathVariable Integer idTrip,@PathVariable Integer idClient){
        return tripService.addPassenger(idTrip, idClient);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Integer id) {
        tripService.deleteTrip(id);
    }
}