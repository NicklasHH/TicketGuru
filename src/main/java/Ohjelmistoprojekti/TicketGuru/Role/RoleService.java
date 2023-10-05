package Ohjelmistoprojekti.TicketGuru.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public ResponseEntity<Object> validateRole(Role role) {

		if (roleRepository.existsByRoleName(role.getRoleName())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Roolin nimi on jo käytössä.");
		}

		if (role.getRoleName() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Roolin nimi ei voi olla tyhjä");
		}

		return ResponseEntity.ok(null); // Kaikki tarkistukset menivät läpi
	}

}