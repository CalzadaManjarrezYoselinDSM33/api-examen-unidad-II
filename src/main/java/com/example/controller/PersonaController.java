package com.example.controller;

import com.example.entity.Persona;
import com.example.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    @GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable Long id) {
    	return personaService.getPersonaById(id)
    	        .orElseThrow(() -> new RuntimeException("Persona not found with id: " + id));
    }

    @GetMapping("/name/{name}")
    public List<Persona> getPersonasByName(@PathVariable String name) {
        return personaService.getPersonasByName(name);
    }

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.createPersona(persona);
    }

    @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        return personaService.updatePersona(id, personaDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
    }
}
