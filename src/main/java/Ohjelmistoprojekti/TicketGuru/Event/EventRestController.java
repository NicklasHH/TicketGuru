package Ohjelmistoprojekti.TicketGuru.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import Ohjelmistoprojekti.TicketGuru.Ticket.Ticket;
import Ohjelmistoprojekti.TicketGuru.Ticket.TicketRepository;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketType;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketTypeRepository;
import Ohjelmistoprojekti.TicketGuru.Venue.Venue;

@RestController
@RequestMapping("/api/events")
public class EventRestController {

	private final EventRepository eventRepository;
	private final EventService eventService;

	@Autowired
	public EventRestController(EventRepository eventRepository, EventService eventService) {
		this.eventRepository = eventRepository;
		this.eventService = eventService;

	}

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TicketTypeRepository ticketTypeRepository;

	// Palauttaa kaikki tapahtumat tietokannasta http://localhost:8080/api/events
	@GetMapping
	ResponseEntity<List<Event>> all() {
		List<Event> events = eventRepository.findAll(); // Hae kaikki tapahtumat tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id perusteella http://localhost:8080/api/events/1
	@GetMapping("/{id}")
	public ResponseEntity<Event> getEvent(@PathVariable Long id) {
		Optional<Event> event = eventRepository.findById(id); // Hae tapahtuma ID:n perusteella
		if (event.isPresent()) {
			return ResponseEntity.ok(event.get()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id:n perusteella sen nimen
	// http://localhost:8080/api/events/1/eventName
	@GetMapping("/{id}/eventName")
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
	// http://localhost:8080/api/events/1/eventDate
	@GetMapping("/{id}/eventDate")
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
				return ResponseEntity.ok("eventDate ei ole saatavilla"); // HTTP 200 OK
			}
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id:n perusteella sen kellonajan
	// http://localhost:8080/api/events/1/eventTime
	@GetMapping("/{id}/eventTime")
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
				return ResponseEntity.ok("eventTime ei ole saatavilla"); // HTTP 200 OK
			}
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtuman id:n perusteella sen lippumäärän
	// http://localhost:8080/api/events/1/ticketCount
	@GetMapping("/{id}/ticketCount")
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
	// http://localhost:8080/api/events/1/description
	@GetMapping("/{id}/description")
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
	// http://localhost:8080/api/events/1/venue
	@GetMapping("/{id}/venue")
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

	// muokataan olemassa olevaa tapahtumaa http://localhost:8080/api/events/id
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEvent(@Valid @RequestBody Event editedEvent, @PathVariable Long id) {
		if (!eventRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Eventtiä ei löytynyt id:llä " + id);
		}

		ResponseEntity<Object> validationResponse = eventService.validateEvent(editedEvent);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse;
		}

		editedEvent.setEventId(id);
		Event updatedEvent = eventRepository.save(editedEvent);

		return ResponseEntity.status(HttpStatus.CREATED).body(updatedEvent);
	}

	// lisätään uusi tapahtuma http://localhost:8080/api/events
	@PostMapping
	public ResponseEntity<Object> createEvent(@Valid @RequestBody Event newEvent) {
		ResponseEntity<Object> validationResponse = eventService.validateEvent(newEvent);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse;
		}

		Event savedEvent = eventRepository.save(newEvent);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
	}

	// Poistaa tapahtuman id:n perusteella http://localhost:8080/api/events/1
	@DeleteMapping("/{id}")
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

			eventRepository.deleteById(id); // Poista eventti id:n perusteella
			return ResponseEntity.ok(event);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Validointi virheiden käsittely
	@ResponseStatus(HttpStatus.BAD_REQUEST) // HTTP 400 Bad request
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> { // Hakee kaikki virheet
			String fieldName = ((FieldError) error).getField(); // Haetaan virheen aiheuttaneen kentän nimi
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
