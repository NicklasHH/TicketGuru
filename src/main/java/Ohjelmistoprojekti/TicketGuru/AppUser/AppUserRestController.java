package Ohjelmistoprojekti.TicketGuru.AppUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ohjelmistoprojekti.TicketGuru.Role.Role;
import Ohjelmistoprojekti.TicketGuru.Role.RoleRepository;

@RestController
@RequestMapping("/api/appusers")
public class AppUserRestController {

	private final AppUserRepository appUserRepository;

	@Autowired
	public AppUserRestController(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Autowired
	private RoleRepository roleRepository;

	@GetMapping // http://localhost:8080/api/appusers
	ResponseEntity<List<AppUser>> all() {
		List<AppUser> appusers = appUserRepository.findAll(); // Hae kaikki appuserit tietokannasta
		if (!appusers.isEmpty()) {
			return ResponseEntity.ok(appusers);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa appuserin id perusteella
	@GetMapping("/{id}") // http://localhost:8080/api/appusers/1
	public ResponseEntity<AppUser> getEvent(@PathVariable Long id) {
		Optional<AppUser> appUser = appUserRepository.findById(id); // Hae appuser ID:n perusteella
		if (appUser.isPresent()) {
			return ResponseEntity.ok(appUser.get()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Palauttaa appuserin id:n perusteella sen käyttäjätunnuksen
	@GetMapping("/{id}/username") // http://localhost:8080/api/appusers/1/username
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

	// Palauttaa appuserin id:n perusteella sen salasanan
	@GetMapping("/{id}/password") // http://localhost:8080/api/appusers/1/password
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

	// Palauttaa appuserin id:n perusteella sen roolin
	@GetMapping("/{id}/role") // http://localhost:8080/api/appusers/1/role
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

	// lisätään uusi appuser
	@PostMapping // http://localhost:8080/api/appusers
	AppUser newAppUser(@RequestBody AppUser newAppUser)
 {

		System.out.println("Adding new App user: " + newAppUser);

		return appUserRepository.save(newAppUser);
	}

	// muokataan olemassa olevaa appuseria
	@PutMapping("/{id}") // http://localhost:8080/api/appusers/id
	AppUser editAppUser(@RequestBody AppUser editedAppUser, @PathVariable Long id) {

		editedAppUser.setAppUserId(id);
		System.out.println("Editing AppUser: " + editedAppUser);
		return appUserRepository.save(editedAppUser);
	}

	// Poista appuser id:n perusteella
	@DeleteMapping("/{id}") // http://localhost:8080/api/appusers/1
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

}
