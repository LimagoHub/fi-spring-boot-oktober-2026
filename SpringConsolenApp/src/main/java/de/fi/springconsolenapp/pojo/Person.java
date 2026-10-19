package de.fi.springconsolenapp.pojo;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
public class Person {

    private UUID id;
    private String firstName;
    private String lastName;


}
