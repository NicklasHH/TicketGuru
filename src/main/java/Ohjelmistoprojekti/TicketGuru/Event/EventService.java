package Ohjelmistoprojekti.TicketGuru.Event;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Ohjelmistoprojekti.TicketGuru.Venue.Venue;
import Ohjelmistoprojekti.TicketGuru.Venue.VenueRepository;

@Service
public class EventService {

	@Autowired
	private VenueRepository venueRepository;

	// Yleiset tarkistukset
	public ResponseEntity<Object> validateEvent(Event event) {
		Optional<Venue> venueOptional = venueRepository.findById(event.getVenue().getVenueId());
		if (venueOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Venue ID:tä ei löytynyt");
		}

		String eventDate = event.getEventDate();
		if (!isValidEventDate(eventDate)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Virheellinen päivämäärämuotoilu. Oikea muoto on: 2023-10-27");
		}

		return ResponseEntity.ok(null); // Kaikki tarkistukset menivät läpi
	}

	// pvm tarkistus
	private boolean isValidEventDate(String eventDate) {
		String datePattern = "\\d{4}-\\d{2}-\\d{2}";
		return eventDate.matches(datePattern);
	}
}