package com.example.service;

import com.example.entity.Persona;
import com.example.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    public List<Persona> getPersonasByName(String name) {
        return personaRepository.findByName(name);
    }

    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona updatePersona(Long id, Persona personaDetails) {
        return personaRepository.findById(id).map(persona -> {
            persona.setName(personaDetails.getName());
            return personaRepository.save(persona);
        }).orElseThrow(() -> new RuntimeException("Persona not found with id: " + id));
    }
    public void deletePersona(Long id) {
        personaRepository.findById(id).ifPresentOrElse(
            persona -> personaRepository.delete(persona),
            () -> {
                throw new RuntimeException("Persona not found with id: " + id);
            }
        );
    }


}
