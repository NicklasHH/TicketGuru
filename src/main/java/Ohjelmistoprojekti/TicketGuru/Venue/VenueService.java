package Ohjelmistoprojekti.TicketGuru.Venue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Ohjelmistoprojekti.TicketGuru.Postalcode.PostalcodeRepository;
import Ohjelmistoprojekti.TicketGuru.Postalcode.Postalcode;

@Service
public class VenueService {

	private final VenueRepository venueRepository;

	@Autowired
	public VenueService(VenueRepository venueRepository) {
		this.venueRepository = venueRepository;
	}

	@Autowired
	private PostalcodeRepository postalcodeRepository;

	public ResponseEntity<Object> checkDuplicatePut(Venue editedVenue, long id) {
		List<Venue> allVenues = venueRepository.findAll();

		for (Venue otherVenue : allVenues) {
			if (otherVenue.getPlace().equals(editedVenue.getPlace())) {
				if (otherVenue.getVenueId() != id) {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body("Paikka nimellä " + editedVenue.getPlace() + " on jo olemassa toisella id:llä.");
				}
			}
		}

		return ResponseEntity.ok(null);
	}

	public ResponseEntity<Object> checkDuplicatePost(Venue newVenue) {
		List<Venue> duplicateVenues = venueRepository.findByPlace(newVenue.getPlace());

		for (Venue existingVenue : duplicateVenues) {
			if (existingVenue.getVenueId() != 0) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Paikka nimelä " + newVenue.getPlace() + " on jo olemassa toisella id:llä.");
			}
		}

		return ResponseEntity.ok(null);
	}

	public ResponseEntity<Object> validateVenue(Venue venue) {
		Optional<Postalcode> postalcodeOptional = postalcodeRepository
				.findByPostalcode(venue.getPostalcode().getPostalcode());
		if (postalcodeOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Postinumeroa ei löytynyt");
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

		if (venue.getPostalcode() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Postinumero ei voi olla tyhjä");
		}

		if (venue.getPostalcode().getPostalcode().length() != 5) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Paikan postinumeron on oltava 5 numeroa");
		}

		return ResponseEntity.ok(null); // Kaikki tarkistukset menivät läpi
	}

}