package com.backend.trip.application;

import com.backend.trip.infraestructure.dto.TripInputDto;
import com.backend.trip.infraestructure.dto.TripOutputDto;

import java.util.List;

public interface TripService {

    TripOutputDto addTrip(TripInputDto tripInputDto);
    List<TripOutputDto> getAllTrip();
    TripOutputDto getTripById(Integer id);
    TripOutputDto updateTrip(Integer id, TripInputDto tripInputDto);
    void deleteTrip(Integer id);
    public Integer countPassengers(Integer trip_id);
    public TripOutputDto addPassenger(Integer idTrip, Integer idClient);
    TripOutputDto changeStatus(Integer idTrip, Boolean status);
    public boolean checkStatus(Integer idTrip);
}