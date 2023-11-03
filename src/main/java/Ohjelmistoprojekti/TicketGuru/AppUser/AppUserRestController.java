package Ohjelmistoprojekti.TicketGuru.AppUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import Ohjelmistoprojekti.TicketGuru.Role.Role;
import Ohjelmistoprojekti.TicketGuru.Role.RoleRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appusers")
public class AppUserRestController {

	private final AppUserRepository appUserRepository;
	private final AppUserService appUserService;

	@Autowired
	public AppUserRestController(AppUserRepository appUserRepository, AppUserService appUserService) {
		this.appUserRepository = appUserRepository;
		this.appUserService = appUserService;
	}

	@Autowired
	private RoleRepository roleRepository;

	// Palauttaa kaikki appuserit http://localhost:8080/api/appusers
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping
	ResponseEntity<List<AppUser>> all() {
		List<AppUser> appusers = appUserRepository.findAll(); // Hae kaikki appuserit tietokannasta
		if (!appusers.isEmpty()) {
			return ResponseEntity.ok(appusers);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa appuserin id perusteella http://localhost:8080/api/appusers/1
	@GetMapping("/{id}")
	public ResponseEntity<AppUser> getEvent(@PathVariable Long id) {
		Optional<AppUser> appUser = appUserRepository.findById(id); // Hae appuser ID:n perusteella
		if (appUser.isPresent()) {
			return ResponseEntity.ok(appUser.get()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa appuserin id:n perusteella sen käyttäjätunnuksen http://localhost:8080/api/appusers/1/username
	@GetMapping("/{id}/username")
	public ResponseEntity<Object> getUsername(@PathVariable long id) {
		Optional<AppUser> appUserOptional = appUserRepository.findById(id);
		if (appUserOptional.isPresent()) {
			AppUser appUser = appUserOptional.get();
			Map<String, String> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			jsonResponse.put("username", appUser.getUsername());
			return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa appuserin id:n perusteella sen salasanan http://localhost:8080/api/appusers/1/password
	@GetMapping("/{id}/password")
	public ResponseEntity<Object> getPassword(@PathVariable long id) {
		Optional<AppUser> appUserOptional = appUserRepository.findById(id);
		if (appUserOptional.isPresent()) {
			AppUser appUser = appUserOptional.get();
			Map<String, String> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			jsonResponse.put("password", appUser.getPassword());
			return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa appuserin id:n perusteella sen roolin http://localhost:8080/api/appusers/1/role
	@GetMapping("/{id}/role")
	public ResponseEntity<Role> getRole(@PathVariable long id) {
		Optional<AppUser> appUserOptional = appUserRepository.findById(id);
		if (appUserOptional.isPresent()) {
			AppUser appUser = appUserOptional.get();
			if (appUser.getRole() != null) {
				Role role = appUser.getRole();
				return ResponseEntity.ok(role); // Palauta koko Role-olio JSON-muodossa
			} else {
				return ResponseEntity.notFound().build(); // HTTP 404 Not Found
			}
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// lisätään uusi appuser http://localhost:8080/api/appusers
	@PostMapping
	public ResponseEntity<Object> createAppUser(@Valid @RequestBody AppUser newAppUser) {
		ResponseEntity<Object> validationResponse = appUserService.validateAppUser(newAppUser);

		// Kutsu AppUserService:n checkDuplicatePost-metodia
		ResponseEntity<Object> checkDuplicate = appUserService.checkDuplicatePost(newAppUser);
		if (checkDuplicate.getStatusCode() != HttpStatus.OK) {
			return checkDuplicate;
		}

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse; // Palauta virhe, jos tarkistuksissa on ongelmia
		}

		AppUser savedAppUser = appUserRepository.save(newAppUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAppUser);
	}

	// muokataan olemassa olevaa appuseria http://localhost:8080/api/appusers/id
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAppUser(@Valid @RequestBody AppUser editedAppUser, @PathVariable Long id) {
		if (!appUserRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AppUseria ei löytynyt id:llä " + id);
		}

		// Kutsu AppUserService:n checkDuplicatePut-metodia
		ResponseEntity<Object> checkDuplicate = appUserService.checkDuplicatePut(editedAppUser, id);
		if (checkDuplicate.getStatusCode() != HttpStatus.OK) {
			return checkDuplicate;
		}

		ResponseEntity<Object> validationResponse = appUserService.validateAppUser(editedAppUser);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse; // Palauta virhe, jos tarkistuksissa on ongelmia
		}

		editedAppUser.setAppUserId(id);
		AppUser updatedAppUser = appUserRepository.save(editedAppUser);

		return ResponseEntity.status(HttpStatus.CREATED).body(updatedAppUser);
	}

	// Poista appuser id:n perusteella http://localhost:8080/api/appusers/1
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deleteAppUser(@PathVariable Long id) { // Hae appuser tietokannasta ja palauta vastaus
		Optional<AppUser> appUserOptional = appUserRepository.findById(id);// Palauttaa appuserin Id:N perusteella
		if (appUserOptional.isPresent()) {
			AppUser appUser = appUserOptional.get();

			// Hae ja päivitä kaikki liittyvät roolit
			List<Role> roles = roleRepository.findByAppUser_AppUserId(id);
			for (Role role : roles) {
				role.setAppUsers(null);
			}
			roleRepository.saveAll(roles);

			appUserRepository.deleteById(id); // Poistaa appuserin Id:n perusteella
			return ResponseEntity.ok(appUser); // HTTP 200 OK, palauttaa poistetun appuserin tiedot
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
