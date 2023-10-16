package Ohjelmistoprojekti.TicketGuru.Postalcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postalcodes")
public class PostalcodeRestController {

	private final PostalcodeRepository postalcodeRepository;
	private final JdbcTemplate jdbcTemplate; // SQL päivitykseen

	@Autowired
	public PostalcodeRestController(PostalcodeRepository postalcodeRepository, JdbcTemplate jdbcTemplate) {
		this.postalcodeRepository = postalcodeRepository;
		this.jdbcTemplate = jdbcTemplate;
	}

	@GetMapping
	// http://localhost:8080/api/postalcodes
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
		Optional<Postalcode> foundPostalcode = postalcodeRepository.findByPostalcode(postalcode);
		if (foundPostalcode.isPresent()) {
			return ResponseEntity.ok(foundPostalcode.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Postalcode> addPostalCode(@Valid @RequestBody Postalcode postalCode) {
		Optional<Postalcode> foundPostalcode = postalcodeRepository.findByPostalcode(postalCode.getPostalcode());

		if (foundPostalcode.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); // HTTP 409 jos postikoodi on jo olemassa
		}

		Postalcode savedPostalcode = postalcodeRepository.save(postalCode);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPostalcode); // HTTP 201
	}

	@PutMapping("/{postalcode}") // Muuta vain post officea. Ei postalcodea joka toimii pääavaimena.
	public ResponseEntity<Postalcode> updatePostalCode(@PathVariable String postalcode,
			@Valid @RequestBody Postalcode newPostalcode) {
		Optional<Postalcode> foundPostalcode = postalcodeRepository.findByPostalcode(postalcode);

		if (!foundPostalcode.isPresent()) {
			return ResponseEntity.notFound().build(); // HTTP 404 jos postikoodia ei löydy
		}

		Postalcode putPostalcode = foundPostalcode.get();
		putPostalcode.setPostOffice(newPostalcode.getPostOffice());

		postalcodeRepository.save(putPostalcode);
		return ResponseEntity.ok(putPostalcode); // HTTP 200
	}

	private void setVenuePostalCodeToNull(String postalcode) { //
		String sql = "UPDATE VENUES SET POSTALCODE = NULL WHERE POSTALCODE = ?";
		jdbcTemplate.update(sql, postalcode);
	}

	@DeleteMapping("/{postalcode}")
	public ResponseEntity<Postalcode> deletePostalcode(@PathVariable String postalcode) {
		Optional<Postalcode> foundPostalcode = postalcodeRepository.findByPostalcode(postalcode);
		if (foundPostalcode.isPresent()) {

			// Metodi päivittää venues taulun postikoodin NULL:iksi
			setVenuePostalCodeToNull(postalcode);

			Postalcode postalcodeEnt = foundPostalcode.get();
			postalcodeRepository.delete(postalcodeEnt);
			return ResponseEntity.ok(postalcodeEnt); // HTTP 200 OK, palauttaa poistetun p-numeron
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Validointi virheiden käsittely
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