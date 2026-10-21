package de.fi.webapp.event;

import java.util.UUID;

public record PersonCreatedEvent(UUID id, String vorname, String nachname) {
}
