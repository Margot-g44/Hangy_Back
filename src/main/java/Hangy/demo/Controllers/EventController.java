package Hangy.demo.Controllers;

import Hangy.demo.Entities.Event;
import Hangy.demo.Services.EventService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) throws BadRequestException {
        Event created = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestBody Event event) throws BadRequestException {

        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}