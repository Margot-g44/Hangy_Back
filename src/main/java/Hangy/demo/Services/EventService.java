package Hangy.demo.Services;

import Hangy.demo.Entities.Event;
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
    public Event createEvent(Event event) {
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
    public Event updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = getEventById(id);

        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());
        existingEvent.setGroup(updatedEvent.getGroup());
        existingEvent.setCreatedBy(updatedEvent.getCreatedBy());

        validateEvent(existingEvent);

        return eventRepository.save(existingEvent);
    }

    // DELETE
    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

    // LOGIQUE MÉTIER
    private void validateEvent(Event event) {

        if (event.getTitle() == null || event.getTitle().isBlank()) {
            throw new RuntimeException("Title is required");
        }

        if (event.getStartTime() == null || event.getEndTime() == null) {
            throw new RuntimeException("Start and end time are required");
        }

        if (event.getStartTime().isAfter(event.getEndTime())) {
            throw new RuntimeException("Start time must be before end time");
        }

        if (event.getStartTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Event cannot be in the past");
        }

        if (event.getGroup() == null) {
            throw new RuntimeException("Event must be linked to a group");
        }

        if (event.getCreatedBy() == null) {
            throw new RuntimeException("Event must have a creator");
        }
    }
}