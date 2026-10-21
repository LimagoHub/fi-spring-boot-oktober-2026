package de.fi.webapp.demo;


import de.fi.webapp.persistence.entity.PersonEntity;
import de.fi.webapp.persistence.repository.PersonenRepository;
import de.fi.webapp.service.MailServiceDummy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    private final MailServiceDummy mailService;


    @PostConstruct
    public void foo() {
        mailService.send("Hallo", "Welt");
    }


}
