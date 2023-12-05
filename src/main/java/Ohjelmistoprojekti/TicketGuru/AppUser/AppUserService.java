package Ohjelmistoprojekti.TicketGuru.AppUser;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Ohjelmistoprojekti.TicketGuru.Role.RoleRepository;
import Ohjelmistoprojekti.TicketGuru.Role.Role;

@Service
public class AppUserService {

	private final AppUserRepository appUserRepository;

	@Autowired
	public AppUserService(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@Autowired
	private RoleRepository roleRepository;

	// Tarkistus olemassa olevan appuserin yhteydessä
	public ResponseEntity<Object> checkDuplicatePut(AppUser editedAppUser, long id) {
		List<AppUser> allAppUsers = appUserRepository.findAll();

		for (AppUser otherAppUser : allAppUsers) {
			if (otherAppUser.getUsername().equals(editedAppUser.getUsername())) {
				if (otherAppUser.getAppUserId() != id) {
					return ResponseEntity.status(HttpStatus.CONFLICT).body(
							"Appuser nimellä " + editedAppUser.getUsername() + " on jo olemassa toisella id:llä.");
				}
			}
		}

		return ResponseEntity.ok(null);
	}

	// Tarkistus uuden appuserin yhteydessä
	public ResponseEntity<Object> checkDuplicatePost(AppUser newAppUser) {
		List<AppUser> duplicateAppUsers = appUserRepository.findByUsername(newAppUser.getUsername());

		for (AppUser existingAppUser : duplicateAppUsers) {
			if (existingAppUser.getAppUserId() != 0) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Appuser nimelä " + newAppUser.getUsername() + " on jo olemassa toisella id:llä.");
			}
		}

		return ResponseEntity.ok(null);
	}

	// Yleiset tarkistukset
	public ResponseEntity<Object> validateAppUser(AppUser appUser) {
		Optional<Role> roleOptional = roleRepository.findById(appUser.getRole().getRoleId());
		if (roleOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Roolin ID:tä ei löytynyt");
		}

		if (appUser.getRole().getRoleId() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Roolin ID ei voi olla tyhjä");
		}

		if (appUser.getPassword().isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Salasana ei voi olla tyhjä");
		}

		if (appUser.getUsername().isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Käyttäjätunnus ei voi olla tyhjä");
		}

		if (appUser.getRole() == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Rooli ei voi olla tyhjä");
		}

		return ResponseEntity.ok(null); // Kaikki tarkistukset menivät läpi
	}

}