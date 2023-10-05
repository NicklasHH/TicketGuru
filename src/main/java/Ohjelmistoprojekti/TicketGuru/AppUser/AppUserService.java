package Ohjelmistoprojekti.TicketGuru.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

	private final AppUserRepository appUserRepository;

	@Autowired
	public AppUserService(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	public ResponseEntity<Object> validateAppUser(AppUser appUser) {

		if (appUserRepository.existsByUsername(appUser.getUsername())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Käyttäjätunnus on jo käytössä.");
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