package Ohjelmistoprojekti.TicketGuru.Venue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/venues")
public class VenueRestController {

	private final VenueRepository venueRepository;

	@Autowired
	public VenueRestController(VenueRepository venueRepository) {
		this.venueRepository = venueRepository;
	}

	@GetMapping // http://localhost:8080/api/venues
	ResponseEntity<List<Venue>> all() {
		List<Venue> venues = venueRepository.findAll(); // Hae kaikki tapahtumapaikat tietokannasta
		if (!venues.isEmpty()) {
			return ResponseEntity.ok(venues);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	@DeleteMapping("/{id}") // http://localhost:8080/api/venues/1
	public ResponseEntity<?> deleteVenue(@PathVariable Long id) { // Hae tapahtumapaikka tietokannasta ja palauta vastaus
		Optional<Venue> venueOptional = venueRepository.findById(id);// Palauttaa tapahtumapaikan Id:N perusteella
		if (venueOptional.isPresent()) {
			Venue venue = venueOptional.get();
			venueRepository.deleteById(id); // Poistaa tapahtumapaikan Id:n perusteella
			return ResponseEntity.ok(venue); // HTTP 200 OK, palauttaa poistetun tapahtumapaikan tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

}