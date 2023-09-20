package Ohjelmistoprojekti.TicketGuru.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ohjelmistoprojekti.TicketGuru.domain.Event;
import Ohjelmistoprojekti.TicketGuru.domain.EventRepository;

@RestController
@RequestMapping("/api/events")
public class TicketRestController {
    private final EventRepository eventRepository;

    @Autowired
    public TicketRestController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @GetMapping("/{id}") // http://localhost:8080/api/event/1
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        // Hae tapahtuma tietokannasta ja palauta vastaus
        Optional<Event> event = eventRepository.findById(id); // Palauttaa eventin Id:N perustellaa
        if (event.isPresent()) { //
            return ResponseEntity.ok(event.get()); // HTTP 200 OK
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }
    // Lisää muut REST-endpointit tähän luokkaan POST,PUT,DELETE etc.
}
