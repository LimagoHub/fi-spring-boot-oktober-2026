package de.fi.webapp.service.model;

import lombok.*;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Person {


    private UUID id;

    private String vorname;

    private String nachname;
}
