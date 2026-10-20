package de.fi.webapp.presentation.controller.v1;


import de.fi.webapp.presentation.dto.PersonDto;
import de.fi.webapp.presentation.error.IdMismatchException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
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

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if(id.toString().endsWith("1"))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();

    }

    @PostMapping(path="", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody PersonDto personDto, UriComponentsBuilder uriBuilder) {
        System.out.println(personDto + " wurde erfasst");

        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path="/{id}", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody PersonDto personDto) {

        if(! personDto.getId().equals(id)) throw new IdMismatchException("Upps");

        if(id.toString().endsWith("1"))
            return ResponseEntity.notFound().build();

        System.out.println(personDto + " wurde geaendert");
        return ResponseEntity.ok().build();
    }
}
