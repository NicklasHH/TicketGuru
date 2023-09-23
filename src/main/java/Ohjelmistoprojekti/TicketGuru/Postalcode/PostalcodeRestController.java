package Ohjelmistoprojekti.TicketGuru.Postalcode;

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
@RequestMapping("/api/postalcodes")
public class PostalcodeRestController {

	private final PostalcodeRepository postalcodeRepository;

	@Autowired
	public PostalcodeRestController(PostalcodeRepository postalcodeRepository) {
		this.postalcodeRepository = postalcodeRepository;
	}

	@GetMapping // http://localhost:8080/api/postalcodes
	ResponseEntity<List<Postalcode>> all() {
		List<Postalcode> postalcodes = postalcodeRepository.findAll(); // Hae kaikki postinumerot tietokannasta
		if (!postalcodes.isEmpty()) {
			return ResponseEntity.ok(postalcodes);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	@DeleteMapping("/{postalcode}") // http://localhost:8080/api/postalcodes/00100
	public ResponseEntity<?> deletePostalcode(@PathVariable String postalcode) { // Hae postinumero tietokannasta ja palauta vastaus
		Optional<Postalcode> postalcodeOptional = postalcodeRepository.findByPostalcode(postalcode); // Palauttaa postinumeron, joka toimii myös Id:nä
		if (postalcodeOptional.isPresent()) {
			Postalcode postalcodeEntity = postalcodeOptional.get();
			postalcodeRepository.delete(postalcodeEntity); // Poistaa postinumeron

			return ResponseEntity.ok(postalcodeEntity); // HTTP 200 OK, palauttaa poistetun postinumeron tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

}