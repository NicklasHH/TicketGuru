package Ohjelmistoprojekti.TicketGuru.TicketType;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Ohjelmistoprojekti.TicketGuru.Event.Event;
import Ohjelmistoprojekti.TicketGuru.Event.EventRepository;

@Service
public class TicketTypeService {

	@Autowired
	private EventRepository eventRepository;

	public ResponseEntity<Object> validateTicketType(TicketType ticketType) {

		Optional<Event> eventOptional = eventRepository.findById(ticketType.getEvent().getEventId());
		if (eventOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Event ID:tä ei löytynyt");
		}

		return ResponseEntity.ok(null); // Kaikki tarkistukset menivät läpi
	}

}