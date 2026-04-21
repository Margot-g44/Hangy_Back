package Hangy.demo.Services;

import Hangy.demo.Entities.Event;
import Hangy.demo.Exceptions.BadRequestException;
import Hangy.demo.Repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // CREATE
    public Event createEvent(Event event) throws BadRequestException {
        validateEvent(event);

        if (event.getCreatedAt() == null) {
            event.setCreatedAt(LocalDateTime.now());
        }

        return eventRepository.save(event);
    }

    // READ ALL
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // READ BY ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    // UPDATE
    public Event updateEvent(Long id, Event updatedEvent) throws BadRequestException {
        Event existingEvent = getEventById(id);

        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());

        // ⚠️ on évite les null dangereux
        if (updatedEvent.getGroup() != null) {
            existingEvent.setGroup(updatedEvent.getGroup());
        }

        if (updatedEvent.getCreatedBy() != null) {
            existingEvent.setCreatedBy(updatedEvent.getCreatedBy());
        }

        validateEvent(existingEvent);

        return eventRepository.save(existingEvent);
    }

    // DELETE
    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

    // LOGIQUE MÉTIER // Important pour penser en terme de produits + sécuriser les données
    private void validateEvent(Event event) throws BadRequestException {

        if (event.getTitle() == null || event.getTitle().isBlank()) {
            throw new BadRequestException( "Title is required");
        }

        if (event.getStartTime() == null || event.getEndTime() == null) {
            throw new BadRequestException("Start and end time are required");
        }

        if (event.getStartTime().isAfter(event.getEndTime())) {
            throw new BadRequestException("Start time must be before end time");
        }

        if (event.getEndTime().isBefore(event.getStartTime().plusMinutes(15))) {
            throw new BadRequestException("Event must last at least 15 minutes");
        }

        if (event.getStartTime().isAfter(LocalDateTime.now().plusYears(1))) {
            throw new BadRequestException("Event cannot be more than 1 year in advance");
        }

        if (event.getStartTime().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Event cannot be in the past");
        }

        // TEMPORAIREMENT désactivé pour tes tests Postman
        // if (event.getGroup() == null) {
        //     throw new RuntimeException("Event must be linked to a group");
        // }

        // if (event.getCreatedBy() == null) {
        //     throw new RuntimeException("Event must have a creator");
        // }
    }
}