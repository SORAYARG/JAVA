package com.backend.client.infraestructure.repository;

import com.backend.client.domain.Client;
import org.hibernate.annotations.NotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @NotFound
    Client findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(java.lang.String email, java.lang.Integer id);
}