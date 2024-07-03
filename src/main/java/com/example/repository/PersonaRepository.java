package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByName(String name);
}

