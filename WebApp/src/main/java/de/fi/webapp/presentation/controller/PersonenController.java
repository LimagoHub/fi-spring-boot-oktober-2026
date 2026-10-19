package de.fi.webapp.presentation.controller;


import de.fi.webapp.presentation.dto.PersonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personen")
public class PersonenController {


    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDto.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})



    @GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> getPerson(@PathVariable UUID id) {

        if(id.toString().endsWith("1"))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(PersonDto.builder().id(id).vorname("John").nachname("Doe").build());
    }



    @GetMapping(path="",produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDto>> findAll(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Schmitt")String nachname
    ) {

        System.out.println("Vorname = " + vorname +", Nachname = " + nachname);

        var liste = List.of(
                PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build(),
                PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("Rambo").build(),
                PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("Wick").build(),
                PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("McClaine").build(),
                PersonDto.builder().id(UUID.randomUUID()).vorname("John Boy").nachname("Walton").build()

        );

        return ResponseEntity.ok(liste);
    }
}
