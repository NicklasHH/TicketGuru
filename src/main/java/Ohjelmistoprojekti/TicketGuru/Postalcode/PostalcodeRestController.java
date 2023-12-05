package Ohjelmistoprojekti.TicketGuru.Postalcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/postalcodes")
public class PostalcodeRestController {

	private final PostalcodeRepository postalcodeRepository;
	private final PostalcodeService postalcodeService;
	private final JdbcTemplate jdbcTemplate; // SQL päivitykseen

	@Autowired
	public PostalcodeRestController(PostalcodeRepository postalcodeRepository, JdbcTemplate jdbcTemplate,
			PostalcodeService postalcodeService) {
		this.postalcodeRepository = postalcodeRepository;
		this.jdbcTemplate = jdbcTemplate;
		this.postalcodeService = postalcodeService;
	}

	// Hae kaikki posinumerot 
	// http://localhost:8080/api/postalcodes
	@GetMapping
	ResponseEntity<List<Postalcode>> all() {
		List<Postalcode> postalcodes = postalcodeRepository.findAll(); // Hae kaikki postinumerot tietokannasta
		if (!postalcodes.isEmpty()) {
			return ResponseEntity.ok(postalcodes);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa kaikki postinumerot postinumeron perusteella
	// http://localhost:8080/api/postalcodes/00100
	@GetMapping("/{postalcode}")
	public ResponseEntity<Postalcode> getPostalcode(@PathVariable String postalcode) {
		Optional<Postalcode> foundPostalcode = postalcodeRepository.findByPostalcode(postalcode);
		if (foundPostalcode.isPresent()) {
			return ResponseEntity.ok(foundPostalcode.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Muokkaa postinumeroa 
	// http://localhost:8080/api/postalcode/00100
	@PutMapping("/{postalcode}")
	public ResponseEntity<Object> updatePostalCode(@RequestBody Postalcode editedPostalcode,
			@PathVariable String postalcode) {
		editedPostalcode.setPostalcode(postalcode);

		if (!postalcodeRepository.findByPostalcode(postalcode).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Postinumeroa ei löydy: " + postalcode);
		}

		ResponseEntity<Object> validationResponse = postalcodeService.validatePostalcode(editedPostalcode);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse;
		}

		editedPostalcode.setPostalcode(postalcode);
		Postalcode updatedPostalcode = postalcodeRepository.save(editedPostalcode);

		return ResponseEntity.status(HttpStatus.OK).body(updatedPostalcode);
	}

	// Lisätään uusi postinumero 
	// http://localhost:8080/api/postalcodes
	@PostMapping
	public ResponseEntity<Object> createPostalcode(@Valid @RequestBody Postalcode newPostalcode) {
		ResponseEntity<Object> validationResponse = postalcodeService.validatePostalcode(newPostalcode);

		// Kutsu postalcodeServicen:n checkDuplicatePost-metodia
		ResponseEntity<Object> checkDuplicate = postalcodeService.checkDuplicatePost(newPostalcode);
		if (checkDuplicate.getStatusCode() != HttpStatus.OK) {
			return checkDuplicate;
		}

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse;
		}

		Postalcode savedPostalcode = postalcodeRepository.save(newPostalcode);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPostalcode);
	}

	private void setVenuePostalCodeToNull(String postalcode) { //
		String sql = "UPDATE VENUES SET POSTALCODE = NULL WHERE POSTALCODE = ?";
		jdbcTemplate.update(sql, postalcode);
	}

	// Poista postinumero postinumeron perusteella
	// http://localhost:8080/api/venues/00100
	@DeleteMapping("/{postalcode}")
	public ResponseEntity<Postalcode> deletePostalcode(@PathVariable String postalcode) {
		Optional<Postalcode> foundPostalcode = postalcodeRepository.findByPostalcode(postalcode);
		if (foundPostalcode.isPresent()) {

			setVenuePostalCodeToNull(postalcode);

			Postalcode postalcodeEnt = foundPostalcode.get();
			postalcodeRepository.delete(postalcodeEnt);
			return ResponseEntity.ok(postalcodeEnt); // HTTP 200 OK, palauttaa poistetun postinumeron tiedot
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