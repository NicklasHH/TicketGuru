package Ohjelmistoprojekti.TicketGuru.Role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		List<Role> events = roleRepository.findAll(); // Hae kaikki roolit tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

}