package Ohjelmistoprojekti.TicketGuru.Postalcode;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postalcodes")
public class PostalcodeRestController {

	private final PostalcodeRepository postalcodeRepository;

	@Autowired
	public PostalcodeRestController(PostalcodeRepository postalcodeRepository) {
		this.postalcodeRepository = postalcodeRepository;
	}

	@GetMapping("/") // http://localhost:8080/api/postalcodes
	ResponseEntity<List<Postalcode>> all() {
		List<Postalcode> postalcodes = postalcodeRepository.findAll(); // Hae kaikki postinumerot tietokannasta
		if (!postalcodes.isEmpty()) {
			return ResponseEntity.ok(postalcodes);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	@GetMapping("/{postalcode}") // PostalCode oli määritelty String tietotyypiksi eikä Long.
	public ResponseEntity<Postalcode> getPostalcode(@PathVariable String postalcode) {
		Optional<Postalcode> foundPostalcode  = postalcodeRepository.findByPostalcode(postalcode);
		if (foundPostalcode .isPresent()) {
			return ResponseEntity.ok(foundPostalcode .get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{postalcode}") // http://localhost:8080/api/postalcodes/00100
	public ResponseEntity<Postalcode> deletePostalcode(@PathVariable String postalcode) { // Hae postinumero tietokannasta ja palauta vastaus
		Optional<Postalcode> foundPostalcode  = postalcodeRepository.findByPostalcode(postalcode); // Palauttaa postinumeron, joka toimii myös Id:nä
		if (foundPostalcode.isPresent()) {
			Postalcode postalcodeEntity = foundPostalcode.get();
			postalcodeRepository.delete(postalcodeEntity); // Poistaa postinumeron

			return ResponseEntity.ok(postalcodeEntity); // HTTP 200 OK, palauttaa poistetun postinumeron tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	@PostMapping("/")
	public ResponseEntity<Postalcode> addPostalCode(@RequestBody Postalcode postalCode) {
		Optional<Postalcode> foundPostalcode = postalcodeRepository.findByPostalcode(postalCode.getPostalcode());

		if (foundPostalcode.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); // HTTP 409 jos postikoodi on jo olemassa
		}

		Postalcode savedPostalcode = postalcodeRepository.save(postalCode);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPostalcode); // HTTP 201
	}

}