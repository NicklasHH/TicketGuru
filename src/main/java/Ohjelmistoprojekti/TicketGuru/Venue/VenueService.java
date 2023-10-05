package Ohjelmistoprojekti.TicketGuru.Venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VenueService {

	private final VenueRepository venueRepository;

	@Autowired
	public VenueService(VenueRepository venueRepository) {
		this.venueRepository = venueRepository;
	}

	public ResponseEntity<Object> validateVenue(Venue venue) {

		if (venueRepository.existsByPlace(venue.getPlace())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Paikan nimi on jo käytössä.");
		}

		if (venue.getPlace() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Paikan nimi ei voi olla tyhjä");
		}
		if (venue.getStreetAddress() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Paikan osoite ei voi olla tyhjä");
		}
		if (venue.getPostalcode().getPostalcode() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Paikan postinumero ei voi olla tyhjä");
		}

		if (venue.getPostalcode().getPostalcode().length() != 5) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Paikan postinumeron on oltava 5 numeroa");
		}

		return ResponseEntity.ok(null); // Kaikki tarkistukset menivät läpi
	}

}