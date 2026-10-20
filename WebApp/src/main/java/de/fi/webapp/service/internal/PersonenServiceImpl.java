package de.fi.webapp.service.internal;

import de.fi.webapp.persistence.repository.PersonenRepository;
import de.fi.webapp.service.PersonenService;
import de.fi.webapp.service.exception.AlreadyExistsException;
import de.fi.webapp.service.exception.NotFoundException;
import de.fi.webapp.service.exception.PersonenServiceException;
import de.fi.webapp.service.mapper.PersonMapper;
import de.fi.webapp.service.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor =  PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository repo;
    private final PersonMapper mapper;


    /*
        person ist null -> PSE
        vorname ist null -> PSE
        vorname zu kurz -> PSE
        das selbe für Nachname
        vorname Attila -> PSE
        technische Exceptions aller art wandeln in PSE
        Happy Day wird person an repo uebergeben

     */



    @Override
    public void speichern(final Person person) throws PersonenServiceException {
        try {
            validatePerson(person);

            businessCheck(person);
            if (repo.existsById(person.getId())) throw new AlreadyExistsException("Datensatz existiert bereits");
            repo.save(mapper.convert(person));
        } catch (PersonenServiceException e) {
            throw e;
        } catch (AlreadyExistsException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Speichern der Personen", e);
        }
    }

    private static void businessCheck(final Person person) throws PersonenServiceException {
        if(person.getVorname().equals("Attila"))  throw new PersonenServiceException("Antipath");
    }

    private static void validatePerson(final Person person) throws PersonenServiceException {
        if (person == null) throw new PersonenServiceException("Person darf nicht null sein");
        if (person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
        if (person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");
    }

    @Override
    public void aendern(final Person person) throws PersonenServiceException {
        try {
            validatePerson(person);

            businessCheck(person);
            if (! repo.existsById(person.getId())) throw new NotFoundException("Datensatz existiert nicht");
            repo.save(mapper.convert(person));
        } catch (PersonenServiceException e) {
            throw e;
        } catch (NotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Speichern der Personen", e);
        }
    }

    @Override
    public void loeschen(final UUID uuid) throws PersonenServiceException {
        try {
            if(! repo.existsById(uuid)) throw new NotFoundException("Datensatz existiert nicht");
            repo.deleteById(uuid);
        } catch (NotFoundException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Loeschen der Personen", e);
        }
    }

    @Transactional(rollbackFor =  PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Optional<Person> findeNachId(final UUID uuid) throws PersonenServiceException {
        try {
            return repo.findById(uuid).map(mapper::convert);
        }catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Suchen der Personen", e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(repo.findAll());
        }catch (RuntimeException e) {
            throw new PersonenServiceException("Fehler beim Suchen der Personen", e);
        }
    }
}
