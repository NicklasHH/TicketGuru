package Ohjelmistoprojekti.TicketGuru.Venue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Ohjelmistoprojekti.TicketGuru.Event.Event;
import Ohjelmistoprojekti.TicketGuru.Event.EventRepository;
import Ohjelmistoprojekti.TicketGuru.Postalcode.Postalcode;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/venues")
public class VenueRestController {

	private final VenueRepository venueRepository;
	private final VenueService venueService;

	@Autowired
	public VenueRestController(VenueRepository venueRepository, VenueService venueService) {
		this.venueRepository = venueRepository;
		this.venueService = venueService;
	}

	@Autowired
	private EventRepository eventRepository;

	// Hae kaikki tapahtumapaikat GET http://localhost:8080/api/venues
	@GetMapping 
	ResponseEntity<List<Venue>> all() {
		List<Venue> venues = venueRepository.findAll(); // Hae kaikki tapahtumapaikat tietokannasta
		if (!venues.isEmpty()) {
			return ResponseEntity.ok(venues);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtumapaikan id:n perusteella GET http://localhost:8080/api/venues/1
	@GetMapping("/{id}") 
	public ResponseEntity<Venue> getVenue(@PathVariable Long id) {
		Optional<Venue> venue = venueRepository.findById(id); // Hae venue ID:n perusteella
		if (venue.isPresent()) {
			return ResponseEntity.ok(venue.get()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa tapahtumapaikan postinumeron id:n perusteella GET http://localhost:8080/api/venues/1/postalcode
	@GetMapping("/{id}/postalcode") 
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

	// Palauttaa tapahtumapaikan id:n perusteella GET http://localhost:8080/api/venues/1/place
	@GetMapping("/{id}/place") 
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

	// Palauttaa katuosoitteen id:n perusteella GET http://localhost:8080/api/venues/1/streetaddress
	@GetMapping("/{id}/streetaddress")
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

	// Muokataan olemassa olevaa tapahtumapaikkaa id:n perusteella PUT http://localhost:8080/api/venues/id
	@PutMapping("/{id}") 
	public ResponseEntity<Object> updateVenue(@Valid @RequestBody Venue editedVenue, @PathVariable long id) {
		if (!venueRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tapahtumapaikkaa ei löytynyt id:llä " + id);
		}

		// Kutsu VenueService:n checkDuplicatePut-metodia
		ResponseEntity<Object> checkDuplicate = venueService.checkDuplicatePut(editedVenue, id);
		if (checkDuplicate.getStatusCode() != HttpStatus.OK) {
			return checkDuplicate;
		}

		ResponseEntity<Object> validationResponse = venueService.validateVenue(editedVenue);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse;
		}

		editedVenue.setVenueId(id);
		Venue updatedVenue = venueRepository.save(editedVenue);

		return ResponseEntity.status(HttpStatus.CREATED).body(updatedVenue);
	}

	// Lisätään uusi tapahtumapaikka  POST http://localhost:8080/api/venues
	@PostMapping
	public ResponseEntity<Object> createVenue(@Valid @RequestBody Venue newVenue) {
		ResponseEntity<Object> validationResponse = venueService.validateVenue(newVenue);

		// Kutsu VenueService:n checkDuplicatePost-metodia
		ResponseEntity<Object> checkDuplicate = venueService.checkDuplicatePost(newVenue);
		if (checkDuplicate.getStatusCode() != HttpStatus.OK) {
			return checkDuplicate;
		}

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse;
		}

		Venue savedVenue = venueRepository.save(newVenue);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedVenue);
	}

	// Poista tapahtumapaikka id:n perusteella DELETE http://localhost:8080/api/venues/1
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deleteVenue(@PathVariable Long id) { // Hae tapahtumapaikka tietokannasta ja palauta vastaus
		Optional<Venue> venueOptional = venueRepository.findById(id);// Palauttaa tapahtumapaikan id:n perusteella
		if (venueOptional.isPresent()) {
			Venue venue = venueOptional.get();

			List<Event> events = eventRepository.findByVenue_VenueId(id);
			for (Event event : events) {
				event.setVenue(null);
				eventRepository.saveAll(events);
			}

			venueRepository.deleteById(id); // Poistaa tapahtumapaikan id:n perusteella
			return ResponseEntity.ok(venue); // HTTP 200 OK, palauttaa poistetun tapahtumapaikan tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Validointivirheiden käsittely
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

//Käyty läpi 01-11-2023