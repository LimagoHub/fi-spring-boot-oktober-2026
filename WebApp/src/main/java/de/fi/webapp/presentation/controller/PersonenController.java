package de.fi.webapp.presentation.controller;


import de.fi.webapp.presentation.dto.PersonDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/personen")
public class PersonenController {


    @GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonDto getPerson(@PathVariable UUID id) {
        return PersonDto.builder().id(id).vorname("John").nachname("Doe").build();
    }
}
