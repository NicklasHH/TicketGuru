package Ohjelmistoprojekti.TicketGuru.Role;

import java.util.List;
import java.util.Optional;

import Ohjelmistoprojekti.TicketGuru.Event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleRestController(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@GetMapping // http://localhost:8080/api/roles
	ResponseEntity<List<Role>> all() {
		List<Role> roles = roleRepository.findAll(); // Hae kaikki roolit tietokannasta
		if (!roles.isEmpty()) {
			return ResponseEntity.ok(roles);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Role> getRole(@PathVariable Long id) {
		Optional<Role> role = roleRepository.findById(id);
		if (role.isPresent()) {
			return ResponseEntity.ok(role.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}") // http://localhost:8080/api/roles/1
	public ResponseEntity<?> deleteRole(@PathVariable Long id) { // Hae rooli tietokannasta ja palauta vastaus
		Optional<Role> roleOptional = roleRepository.findById(id);// Palauttaa roolin Id:N perusteella
		if (roleOptional.isPresent()) {
			Role role = roleOptional.get();
			roleRepository.deleteById(id); // Poistaa roolin Id:n perusteella
			return ResponseEntity.ok(role); // HTTP 200 OK, palauttaa poistetun roolin tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

}