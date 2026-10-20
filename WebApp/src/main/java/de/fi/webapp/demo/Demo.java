package de.fi.webapp.demo;


import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.persistence.repository.PersonenRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    private final PersonenRepository personenRepository;


    @PostConstruct
    public void foo() {
        var johns = personenRepository.findByVorname("John");
        johns.forEach(System.out::println);
    }


}
