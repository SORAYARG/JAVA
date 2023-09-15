package com.example.client.infraestructure.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
public class ClientInputDto{

    @NotNull
    private String name;

    private String surname;

    private int age;

    @NotNull
    private String email;

    @NotNull
    private int phoneNumber;
}