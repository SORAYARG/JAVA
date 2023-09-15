package com.example.client.feign;

import com.example.client.infraestructure.dto.ClientOutputDto;
import com.example.trip.infraestructure.dto.TripOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="backend", url = "http://localhost:8080/")
public interface FeignBack {
    @GetMapping(value = "client/{id}")
    public ClientOutputDto showById(@PathVariable("id") Integer idClient);

    @GetMapping("client/getall")
    public List<ClientOutputDto> findall();

    @GetMapping("trip/{id}")
    public TripOutputDto getTripById(@PathVariable Integer id);

}