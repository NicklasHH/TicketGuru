package Ohjelmistoprojekti.TicketGuru.Role;

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

import Ohjelmistoprojekti.TicketGuru.AppUser.AppUser;
import Ohjelmistoprojekti.TicketGuru.AppUser.AppUserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

	private final RoleRepository roleRepository;
	private final RoleService roleService;

	@Autowired
	public RoleRestController(RoleRepository roleRepository, RoleService roleService) {
		this.roleRepository = roleRepository;
		this.roleService = roleService;
	}

	@Autowired
	private AppUserRepository appUserRepository;

	@GetMapping // http://localhost:8080/api/roles
	ResponseEntity<List<Role>> all() {
		List<Role> roles = roleRepository.findAll(); // Hae kaikki roolit tietokannasta
		if (!roles.isEmpty()) {
			return ResponseEntity.ok(roles);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa roolin id perusteella http://localhost:8080/api/roles/1
	@GetMapping("/{id}")
	public ResponseEntity<Role> getRole(@PathVariable Long id) {
		Optional<Role> role = roleRepository.findById(id);
		if (role.isPresent()) {
			return ResponseEntity.ok(role.get());// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa roolin id:n perusteella roolin nimen http://localhost:8080/api/roles/1/rolename
	@GetMapping("/{id}/rolename") 
	public ResponseEntity<Object> getRole(@PathVariable long id) {
		Optional<Role> roleOptional = roleRepository.findById(id);
		if (roleOptional.isPresent()) {
			Role role = roleOptional.get();
			Map<String, String> jsonResponse = new HashMap<>(); // Luo Map-olio JSON-muotoa varten
			jsonResponse.put("rolename", role.getRoleName());
			return ResponseEntity.ok(jsonResponse); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// muokataan olemassa olevaa roolia http://localhost:8080/api/roles/id
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateRole(@Valid @RequestBody Role editedRole, @PathVariable Long id) {
		if (!roleRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Roolia ei löytynyt id:llä " + id);
		}

		// Kutsu RoleService:n checkDuplicatePut-metodia
		ResponseEntity<Object> checkDuplicate = roleService.checkDuplicatePut(editedRole, id);
		if (checkDuplicate.getStatusCode() != HttpStatus.OK) {
			return checkDuplicate;
		}

		ResponseEntity<Object> validationResponse = roleService.validateRole(editedRole);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse; // Palauta virhe, jos tarkistuksissa on ongelmia
		}

		editedRole.setRoleId(id);
		Role updatedRole = roleRepository.save(editedRole);

		return ResponseEntity.status(HttpStatus.CREATED).body(updatedRole);
	}

	// lisätään uusi rooli http://localhost:8080/api/roles
	@PostMapping
	public ResponseEntity<Object> createRole(@Valid @RequestBody Role newRole) {
		ResponseEntity<Object> validationResponse = roleService.validateRole(newRole);

		// Kutsu VenueService:n checkDuplicatePost-metodia
		ResponseEntity<Object> checkDuplicate = roleService.checkDuplicatePost(newRole);
		if (checkDuplicate.getStatusCode() != HttpStatus.OK) {
			return checkDuplicate;
		}

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse; // Palauta virhe, jos tarkistuksissa on ongelmia
		}

		Role savedRole = roleRepository.save(newRole);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
	}

	@DeleteMapping("/{id}") // http://localhost:8080/api/roles/1
	public ResponseEntity<?> deleteRole(@PathVariable Long id) { // Hae appuser tietokannasta ja palauta vastaus
		Optional<Role> roleOptional = roleRepository.findById(id);// Palauttaa appuserin Id:N perusteella
		if (roleOptional.isPresent()) {
			Role role = roleOptional.get();

			// Hae ja päivitä kaikki liittyvät roolit
			List<AppUser> appUsers = appUserRepository.findByRole_RoleId(id);
			for (AppUser appUser : appUsers) {
				appUser.setRole(null);
			}
			appUserRepository.saveAll(appUsers);

			roleRepository.deleteById(id); // Poistaa appuserin Id:n perusteella
			return ResponseEntity.ok(role); // HTTP 200 OK, palauttaa poistetun appuserin tiedot
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