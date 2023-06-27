package com.example.block6personcontrollers;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {

    private List<Ciudad> ciudades = new ArrayList<>();

    public void addCiudad(Ciudad ciudad) {
        ciudades.add(ciudad);
    }

    @PostConstruct
    public void init() {
        ciudades = new ArrayList<>();
        ciudades.add(new Ciudad("Madrid", 167890));
        ciudades.add(new Ciudad("Jaén", 242424));
    }
    public List<Ciudad> getCiudades() {
        return ciudades;
    }
}
