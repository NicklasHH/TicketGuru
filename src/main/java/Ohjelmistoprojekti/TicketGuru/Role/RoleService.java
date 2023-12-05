package Ohjelmistoprojekti.TicketGuru.Role;

import java.util.List;

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

	// Tarkistus olemassa olevan roolin yhteydessä
	public ResponseEntity<Object> checkDuplicatePut(Role editedRole, long id) {
		List<Role> allRoles = roleRepository.findAll(); // Hae kaikki Role-tiedot

		for (Role otherRole : allRoles) {
			if (otherRole.getRoleName().equals(editedRole.getRoleName())) {
				if (otherRole.getRoleId() != id) {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body("Rooli nimellä " + editedRole.getRoleName() + " on jo olemassa toisella id:llä.");
				}
			}
		}

		return ResponseEntity.ok(null);
	}

	// Tarkistus uuden roolin yhteydessä
	public ResponseEntity<Object> checkDuplicatePost(Role newRole) {
		List<Role> duplicateRoles = roleRepository.findByRoleName(newRole.getRoleName());

		for (Role existingRole : duplicateRoles) {
			if (existingRole.getRoleId() != 0) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Rooli nimelä " + newRole.getRoleName() + " on jo olemassa toisella id:llä.");
			}
		}

		return ResponseEntity.ok(null);
	}

	// Yleiset tarkistukset
	public ResponseEntity<Object> validateRole(Role role) {
		if (role.getRoleName() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Roolin nimi ei voi olla tyhjä");
		}

		return ResponseEntity.ok(null); // Kaikki tarkistukset menivät läpi
	}

}