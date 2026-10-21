package de.fi.webapp.service.config;

import de.fi.webapp.persistence.repository.PersonenRepository;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.internal.PersonenServiceImpl;
import de.fi.webapp.service.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
public class PersonenConfig {


    @Bean
    @Qualifier("antipathen")
    public List<String> createAntipathen() {
        return List.of("Attila", "Batman", "Robin");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> createFruits() {
        return List.of("Apple", "Cherry", "Strawberry");
    }

    /*@Bean
    public PersonenService createPersonenService(final PersonenRepository repo, final PersonMapper mapper, @Qualifier("antipathen") final List<String> antipathen) {
        return new PersonenServiceImpl(repo, mapper, antipathen);
    }

     */
}
