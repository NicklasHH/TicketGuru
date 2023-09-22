package Ohjelmistoprojekti.TicketGuru.Venue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		List<Venue> events = venueRepository.findAll(); // Hae kaikki tapahtumapaikat tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

}