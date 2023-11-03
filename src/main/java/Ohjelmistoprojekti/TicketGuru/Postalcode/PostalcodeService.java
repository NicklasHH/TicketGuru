package Ohjelmistoprojekti.TicketGuru.Postalcode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PostalcodeService {

	private final PostalcodeRepository postalcodeRepository;

	@Autowired
	public PostalcodeService(PostalcodeRepository postalcodeRepository) {
		this.postalcodeRepository = postalcodeRepository;
	}


	public ResponseEntity<Object> checkDuplicatePost(Postalcode newPostalcode) {
		Optional<Postalcode> postalcodeOptional = postalcodeRepository.findByPostalcode(newPostalcode.getPostalcode());
		List<Postalcode> duplicatePostalcodes = postalcodeOptional.map(Collections::singletonList).orElse(Collections.emptyList());

		for (Postalcode existinPostalcode : duplicatePostalcodes) {
			if (existinPostalcode.getPostalcode() != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Postinumero " + newPostalcode.getPostalcode() + " on jo olemassa.");
			}
		}

		return ResponseEntity.ok(null);
	}

	// Lisää tähän tarkistuksia jos on tarve
	public ResponseEntity<Object> validatePostalcode(Postalcode postalcode) {
		if (postalcode.getPostalcode().length() != 5) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Postinumeron on oltava 5 numeroa.");
		}

		if (postalcode.getPostOffice().length()< 2) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Postitoimipaikan on oltava vähintään 2 kirjainta");
		}


		return ResponseEntity.ok(null); // Kaikki tarkistukset menivät läpi
	}

}
