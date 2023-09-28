package Ohjelmistoprojekti.TicketGuru.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ohjelmistoprojekti.TicketGuru.Ticket.Ticket;
import Ohjelmistoprojekti.TicketGuru.Ticket.TicketRepository;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketType;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketTypeRepository;
import Ohjelmistoprojekti.TicketGuru.Venue.Venue;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

	private final EventRepository eventRepository;

	@Autowired
	public EventRestController(EventRepository eventRepository) {
		this.eventRepository = eventRepository;

	}

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TicketTypeRepository ticketTypeRepository;

	// Palauttaa kaikki tapahtumat tietokannasta.
	@GetMapping // http://localhost:8080/api/events
	ResponseEntity<List<Event>> all() {
		List<Event> events = eventRepository.findAll(); // Hae kaikki tapahtumat tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id perusteella
	@GetMapping("/{id}") // http://localhost:8080/api/events/1
	public ResponseEntity<Event> getEvent(@PathVariable Long id) {
		Optional<Event> event = eventRepository.findById(id); // Hae tapahtuma ID:n perusteella
		if (event.isPresent()) {
			return ResponseEntity.ok(event.get()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id:n perusteella sen nimen
	@GetMapping("/{id}/eventName") // http://localhost:8080/api/events/1/eventName
	public ResponseEntity<Object> getEventName(@PathVariable long id) {
		Optional<Event> eventOptional = eventRepository.findById(id);
		if (eventOptional.isPresent()) {
			Event event = eventOptional.get();
			Map<String, String> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			jsonResponse.put("eventName", event.getEventName());
			return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id:n perusteella sen päivämäärän
	@GetMapping("/{id}/eventDate") // http://localhost:8080/api/events/1/eventDate
	public ResponseEntity<Object> getEventDate(@PathVariable long id) {
		Optional<Event> eventOptional = eventRepository.findById(id);
		if (eventOptional.isPresent()) {
			Event event = eventOptional.get();
			Map<String, String> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			String eventDate = event.getEventDate();
			if (eventDate != null) {
				jsonResponse.put("eventDate", eventDate);
				return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
			} else {
				return ResponseEntity.ok("Event date not available"); // HTTP 200 OK
			}
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id:n perusteella sen kellonajan
	@GetMapping("/{id}/eventTime") // http://localhost:8080/api/events/1/eventTime
	public ResponseEntity<Object> getEventTime(@PathVariable long id) {
		Optional<Event> eventOptional = eventRepository.findById(id);
		if (eventOptional.isPresent()) {
			Event event = eventOptional.get();
			Map<String, String> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			String eventTime = event.getEventTime();
			if (eventTime != null) {
				jsonResponse.put("eventTime", eventTime);
				return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
			} else {
				return ResponseEntity.ok("Event time not available"); // HTTP 200 OK
			}
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id:n perusteella sen lippumäärän
	@GetMapping("/{id}/ticketCount") // http://localhost:8080/api/events/1/ticketCount
	public ResponseEntity<Object> getTicketCount(@PathVariable long id) {
		Optional<Event> eventOptional = eventRepository.findById(id);
		if (eventOptional.isPresent()) {
			Event event = eventOptional.get();
			Map<String, Integer> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			jsonResponse.put("ticketCount", event.getTicketCount());
			return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found, jos tapahtumaa ei löytynyt
		}
	}

	// Palauttaa tapahtuman id:n perusteella sen lisätiedot
	@GetMapping("/{id}/description") // http://localhost:8080/api/events/1/description
	public ResponseEntity<Object> getDescription(@PathVariable long id) {
		Optional<Event> eventOptional = eventRepository.findById(id);
		if (eventOptional.isPresent()) {
			Event event = eventOptional.get();
			Map<String, String> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			jsonResponse.put("description", event.getDescription());
			return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id:n perusteella sen tapahtumapaikan
	@GetMapping("/{id}/venue") // http://localhost:8080/api/events/1/venue
	public ResponseEntity<Venue> getVenue(@PathVariable long id) {
		Optional<Event> eventOptional = eventRepository.findById(id);
		if (eventOptional.isPresent()) {
			Event event = eventOptional.get();
			if (event.getVenue() != null) {
				return ResponseEntity.ok(event.getVenue()); // HTTP 200 OK
			} else {
				return ResponseEntity.notFound().build(); // HTTP 404 Not Found
			}
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// lisätään uusi tapahtuma
	@PostMapping // http://localhost:8080/api/events
	Event newEvent(@RequestBody Event newEvent) {

		System.out.println("Adding new event: " + newEvent);

		return eventRepository.save(newEvent);
	}

	// muokataan olemassa olevaa tapahtumaa
	@PutMapping("/{id}") // http://localhost:8080/api/events/id
	public ResponseEntity<Object> editEvent(@RequestBody Event editedEvent, @PathVariable Long id) {

		if (!eventRepository.existsById(id)) {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
		editedEvent.setEventId(id);
		Event updatedEvent = eventRepository.save(editedEvent);
		return ResponseEntity.ok(updatedEvent);
	}

	// Poistaa tapahtuman id:n perusteella
	@DeleteMapping("/{id}") // http://localhost:8080/api/events/1
	public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
		Optional<Event> eventOptional = eventRepository.findById(id);
		if (eventOptional.isPresent()) {
			Event event = eventOptional.get();

			// Hae ja päivitä kaikki liittyvät tickets
			List<Ticket> tickets = ticketRepository.findByEvent_EventId(id);
			for (Ticket ticket : tickets) {
				ticket.setEvent(null); // Aseta event-kenttä nulliksi
			}
			ticketRepository.saveAll(tickets);

			// Hae ja päivitä kaikki liittyvät tickettypes
			List<TicketType> ticketTypes = ticketTypeRepository.findByEvent_EventId(id);
			for (TicketType ticketType : ticketTypes) {
				ticketType.setEvent(null); // Aseta event-kenttä nulliksi
			}
			ticketTypeRepository.saveAll(ticketTypes);

			eventRepository.deleteById(id); // Poista eventti
			return ResponseEntity.ok(event);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

}
