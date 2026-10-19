package de.fi.springconsolenapp.demo;


import de.fi.springconsolenapp.pojo.Person;
import de.fi.springconsolenapp.translator.Translator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
//@Named
@Scope("singleton")
//@Scope("prototype")
//@Lazy
@RequiredArgsConstructor
public class Demo {


    @Qualifier("upper")
    private final Translator translator;


    @Value("${Demo.message}")
    private final String message;

    @PostConstruct
    public void init() {
        System.out.println(translator.translate(message));
    }

    @PreDestroy
    public void dispose() {
        System.out.println("und Tschuess");
    }
}
