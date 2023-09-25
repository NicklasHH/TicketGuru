package Ohjelmistoprojekti.TicketGuru.Event;

import java.util.List;
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

import Ohjelmistoprojekti.TicketGuru.Venue.Venue;
import Ohjelmistoprojekti.TicketGuru.Ticket.*;
import Ohjelmistoprojekti.TicketGuru.TicketType.*;

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

	@GetMapping // http://localhost:8080/api/events
	ResponseEntity<List<Event>> all() {
		List<Event> events = eventRepository.findAll(); // Hae kaikki tapahtumat tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
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

	@GetMapping("/{id}/eventName") // http://localhost:8080/api/events/1/eventName
	public ResponseEntity<String> getEventName(@PathVariable long id) {
		Event event = eventRepository.findById(id).orElse(null);
		if (event != null) {
			return ResponseEntity.ok(event.getEventName()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	@GetMapping("/{id}/eventDate") // http://localhost:8080/api/events/1/eventDate
	public ResponseEntity<String> getEventDate(@PathVariable long id) {
		Event event = eventRepository.findById(id).orElse(null);
		if (event != null) {
			return ResponseEntity.ok(event.getEventDate()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	@GetMapping("/{id}/eventTime") // http://localhost:8080/api/events/1/eventTime
	public ResponseEntity<String> getEventTime(@PathVariable long id) {
		Event event = eventRepository.findById(id).orElse(null);
		if (event != null) {
			return ResponseEntity.ok(event.getEventTime()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	@GetMapping("/{id}/ticketCount") // http://localhost:8080/api/events/1/ticketCount
	public ResponseEntity<Integer> getTicketCount(@PathVariable long id) {
		Event event = eventRepository.findById(id).orElse(null);
		if (event != null) {
			return ResponseEntity.ok(event.getTicketCount()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	@GetMapping("/{id}/description") // http://localhost:8080/api/events/1/description
	public ResponseEntity<String> getDescription(@PathVariable long id) {
		Event event = eventRepository.findById(id).orElse(null);
		if (event != null) {
			return ResponseEntity.ok(event.getDescription()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	@GetMapping("/{id}/venue") // http://localhost:8080/api/events/1/venue
	public ResponseEntity<Venue> getVenue(@PathVariable long id) {
		Event event = eventRepository.findById(id).orElse(null);
		if (event != null && event.getVenue() != null) {
			return ResponseEntity.ok(event.getVenue()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// lisätään uusi event
	@PostMapping("/newEvent") // http://localhost:8080/api/events/newEvent
	Event newEvent(@RequestBody Event newEvent) {

		System.out.println("Adding new event: " + newEvent);

		return eventRepository.save(newEvent);
	}

	// muokataan olemassa olevaa tapahtumaa
	@PutMapping("/{id}") // http://localhost:8080/api/events/id
	Event editEvent(@RequestBody Event editedEvent, @PathVariable Long id) {

		editedEvent.setEventId(id);
		System.out.println("Editing event: " + editedEvent);
		return eventRepository.save(editedEvent);
	}

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
