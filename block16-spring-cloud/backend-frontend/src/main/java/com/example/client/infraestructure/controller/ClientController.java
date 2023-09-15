package com.example.client.infraestructure.controller;


import com.example.client.feign.FeignBack;
import com.example.client.infraestructure.dto.ClientOutputDto;
import com.example.trip.infraestructure.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    FeignBack feignBack;

    @GetMapping("/com/example/client/{id}")
    public ClientOutputDto getClientById(@PathVariable("id") Integer id){
        return feignBack.showById(id);
    }
    @GetMapping("/getall")
    public List<ClientOutputDto> findall(){
        return feignBack.findall();
    }

    @GetMapping("/trip/{id}")
    public TripOutputDto getTripById(@PathVariable Integer id){
        return feignBack.getTripById(id);
    }
}