package Ohjelmistoprojekti.TicketGuru.Role;

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

import Ohjelmistoprojekti.TicketGuru.AppUser.AppUser;
import Ohjelmistoprojekti.TicketGuru.AppUser.AppUserRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleRestController(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
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

	// Palauttaa roolin id perusteella
	@GetMapping("/{id}")
	public ResponseEntity<Role> getRole(@PathVariable Long id) {
		Optional<Role> role = roleRepository.findById(id);
		if (role.isPresent()) {
			return ResponseEntity.ok(role.get());// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa roolin id:n perusteella roolin nimen
	@GetMapping("/{id}/rolename") // http://localhost:8080/api/roles/1/rolename
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

	// lisätään uusi rooli
	@PostMapping // http://localhost:8080/api/appusers
	Role newRole(@RequestBody Role newRole) {

		System.out.println("Adding new role: " + newRole);

		return roleRepository.save(newRole);
	}

	// muokataan olemassa olevaa roolia
	@PutMapping("/{id}") // http://localhost:8080/api/roles/id
	public ResponseEntity<Object> editRole(@RequestBody Role editedRole, @PathVariable Long id) {

		if (!roleRepository.existsById(id)) {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
		editedRole.setRoleId(id);
		Role updatedRole = roleRepository.save(editedRole);
		return ResponseEntity.ok(updatedRole);
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

}