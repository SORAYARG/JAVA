package com.backend.trip.application;

import com.backend.client.domain.Client;
import com.backend.client.infraestructure.repository.ClientRepository;
import com.backend.exceptions.CustomUnprocessableException;
import com.backend.trip.domain.Trip;
import com.backend.trip.infraestructure.dto.TripOutputDto;
import com.backend.trip.infraestructure.dto.TripInputDto;
import com.backend.trip.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, ClientRepository clientRepository) {
        this.tripRepository = tripRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public TripOutputDto addTrip(TripInputDto tripInputDto) {
        Trip trip = new Trip(tripInputDto);
        tripRepository.save(trip);
        return new TripOutputDto(trip);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripOutputDto> getAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(TripOutputDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TripOutputDto getTripById(Integer id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ningún cliente con el id: " + id));
        return new TripOutputDto(trip);
    }

    @Override
    @Transactional
    public TripOutputDto updateTrip(Integer id, TripInputDto tripInputDto) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado ningún cliente con el id: " + id));
        trip.update(tripInputDto);
        tripRepository.save(trip);
        return new TripOutputDto(trip);
    }

    @Override
    @Transactional
    public void deleteTrip(Integer id) {
        tripRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countPassengers(Integer trip_id) {
        Trip trip = tripRepository.findById(trip_id)
                .orElseThrow(() -> new EntityNotFoundException("No trips found with id: " + trip_id));
        return trip.getPassengers().size();
    }

    @Override
    @Transactional
    public TripOutputDto addPassenger(Integer idTrip, Integer idClient) {
        Trip trip = tripRepository.findById(idTrip)
                .orElseThrow(() -> new EntityNotFoundException("Trip does not exist"));
        if (trip.getPassengers().size() == 40) {
            throw new CustomUnprocessableException("No passenger seats available");
        }
        if (trip.getPassengers().stream().anyMatch(client -> Objects.equals(client.getIdClient(), idClient))) {
            throw new CustomUnprocessableException("Passenger already exists within the trip");
        }

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new CustomUnprocessableException("Customer does not exist"));

        trip.getPassengers().add(client);
        tripRepository.save(trip);

        return new TripOutputDto(trip);
    }

    @Override
    @Transactional
    public TripOutputDto changeStatus(Integer idTrip, Boolean status) {
        Trip trip = tripRepository.findById(idTrip)
                .orElseThrow(() -> new EntityNotFoundException("Trip does not exist"));
        trip.setStatus(status);
        tripRepository.save(trip);
        return new TripOutputDto(trip);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkStatus(Integer idTrip) {
        return tripRepository.findById(idTrip)
                .orElseThrow(() -> new EntityNotFoundException("Trip does not exist"))
                .isStatus();
    }
}