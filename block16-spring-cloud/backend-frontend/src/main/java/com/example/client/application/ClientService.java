package com.example.client.application;

import com.example.client.feign.FeignBack;
import com.example.client.infraestructure.dto.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService  {
    @Autowired
    FeignBack feignBack;

    public ClientOutputDto getClientById(Integer id) {
        return feignBack.showById(id);
    }

    public List<ClientOutputDto> getPassenger(){
        return feignBack.findall();
    }
}