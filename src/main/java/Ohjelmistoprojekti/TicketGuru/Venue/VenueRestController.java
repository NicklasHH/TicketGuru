package Ohjelmistoprojekti.TicketGuru.Venue;

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

import Ohjelmistoprojekti.TicketGuru.Event.Event;
import Ohjelmistoprojekti.TicketGuru.Event.EventRepository;
import Ohjelmistoprojekti.TicketGuru.Postalcode.Postalcode;

@RestController
@RequestMapping("/api/venues")
public class VenueRestController {

	private final VenueRepository venueRepository;

	@Autowired
	public VenueRestController(VenueRepository venueRepository) {
		this.venueRepository = venueRepository;
	}

	@Autowired
	private EventRepository eventRepository;

	@GetMapping // http://localhost:8080/api/venues
	ResponseEntity<List<Venue>> all() {
		List<Venue> venues = venueRepository.findAll(); // Hae kaikki tapahtumapaikat tietokannasta
		if (!venues.isEmpty()) {
			return ResponseEntity.ok(venues);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtumapaikan id perusteella
	@GetMapping("/{id}") // http://localhost:8080/api/venues/1
	public ResponseEntity<Venue> getVenue(@PathVariable Long id) {
		Optional<Venue> venue = venueRepository.findById(id); // Hae venue ID:n perusteella
		if (venue.isPresent()) {
			return ResponseEntity.ok(venue.get()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtumapaikan postinumeron id perustella
	@GetMapping("/{id}/postalcode") // http://localhost:8080/api/venues/1/postalcode
	public ResponseEntity<Postalcode> getPostalcode(@PathVariable long id) {
		Optional<Venue> venueOptional = venueRepository.findById(id);
		if (venueOptional.isPresent()) {
			Venue venue = venueOptional.get();
			if (venue.getPostalcode() != null) {
				return ResponseEntity.ok(venue.getPostalcode()); // HTTP 200 OK
			} else {
				return ResponseEntity.notFound().build(); // HTTP 404 Not Found
			}
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtumapaikan id perusteella
	@GetMapping("/{id}/place") // http://localhost:8080/api/venues/1/place
	public ResponseEntity<Object> getPlace(@PathVariable long id) {
		Optional<Venue> venueOptional = venueRepository.findById(id);
		if (venueOptional.isPresent()) {
			Venue venue = venueOptional.get();
			Map<String, String> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			jsonResponse.put("Place", venue.getPlace());
			return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa osoitteen id perusteella
	@GetMapping("/{id}/streetaddress") // http://localhost:8080/api/venues/1/streetaddress
	public ResponseEntity<Object> getstreetaddress(@PathVariable long id) {
		Optional<Venue> venueOptional = venueRepository.findById(id);
		if (venueOptional.isPresent()) {
			Venue venue = venueOptional.get();
			Map<String, String> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			jsonResponse.put("streetaddress", venue.getStreetAddress());
			return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// muokataan olemassa olevaa tapahtumapaikkaa
	@PutMapping("/{id}") // http://localhost:8080/api/venues/id
	public ResponseEntity<Object> editVenue(@RequestBody Venue editedVenue, @PathVariable Long id) {

		if (!venueRepository.existsById(id)) {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
		editedVenue.setVenueId(id);
		Venue updatedVenue = venueRepository.save(editedVenue);
		return ResponseEntity.ok(updatedVenue);
	}

	// lisätään uusi venue
	@PostMapping // http://localhost:8080/api/venues
	Venue newVenue(@RequestBody Venue newVenue) {

		System.out.println("Adding new venue: " + newVenue);

		return venueRepository.save(newVenue);
	}

	// Poista tapahtumapaikka id perusteella
	@DeleteMapping("/{id}") // http://localhost:8080/api/venues/1
	public ResponseEntity<?> deleteVenue(@PathVariable Long id) { // Hae tapahtumapaikka tietokannasta ja palauta vastaus
		Optional<Venue> venueOptional = venueRepository.findById(id);// Palauttaa tapahtumapaikan Id:N perusteella
		if (venueOptional.isPresent()) {
			Venue venue = venueOptional.get();

			List<Event> events = eventRepository.findByVenue_VenueId(id);
			for (Event event : events) {
				event.setVenue(null);
				eventRepository.saveAll(events);
			}

			venueRepository.deleteById(id); // Poistaa tapahtumapaikan Id:n perusteella
			return ResponseEntity.ok(venue); // HTTP 200 OK, palauttaa poistetun tapahtumapaikan tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

}