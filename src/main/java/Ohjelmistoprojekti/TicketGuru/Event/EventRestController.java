package Ohjelmistoprojekti.TicketGuru.Event;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventRestController {
<<<<<<< HEAD
	private final EventRepository eventRepository;
=======
	
	//@Autowired
    private final EventRepository eventRepository;
>>>>>>> 336fd99912e1c0d28aa7d7848408a618a890586f

	@Autowired
	public EventRestController(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@GetMapping("/{id}") // http://localhost:8080/api/events/1
	public ResponseEntity<Event> getEvent(@PathVariable Long id) { // Hae tapahtuma tietokannasta ja palauta vastaus
		Optional<Event> event = eventRepository.findById(id); // Palauttaa eventin Id:N perusteella
		if (event.isPresent()) {
			return ResponseEntity.ok(event.get()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// lisätään uusi event http://localhost:8080/api/events/newEvent
	@PostMapping("/newEvent")
	Event newEvent(@RequestBody Event newEvent) {

		System.out.println("Adding new event" + newEvent);

		return eventRepository.save(newEvent);
	}

	@DeleteMapping("/{id}") // http://localhost:8080/api/events/1
	public ResponseEntity<?> deleteEvent(@PathVariable Long id) { // Hae tapahtuma tietokannasta ja palauta vastaus
		Optional<Event> eventOptional = eventRepository.findById(id);// Palauttaa eventin Id:N perusteella
		if (eventOptional.isPresent()) {
			Event event = eventOptional.get();
			eventRepository.deleteById(id); // Poistaa eventin Id:n perusteella
			return ResponseEntity.ok(event); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		} 
	}

}
