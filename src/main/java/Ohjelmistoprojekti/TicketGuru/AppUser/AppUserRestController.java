package Ohjelmistoprojekti.TicketGuru.AppUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/api/appusers")
public class AppUserRestController {

	private final AppUserRepository appUserRepository;

	@Autowired
	public AppUserRestController(AppUserRepository appUserRepository ) {
		this.appUserRepository = appUserRepository;
	}

	@GetMapping // http://localhost:8080/api/appusers
	ResponseEntity<List<AppUser>> all() {
		List<AppUser> appusers = appUserRepository.findAll(); // Hae kaikki appuserit tietokannasta
		if (!appusers.isEmpty()) {
			return ResponseEntity.ok(appusers);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}
	
	// lisätään uusi appuser
	@PostMapping("/newAppUser") // http://localhost:8080/api/appusers/newAppUser
	AppUser newAppUser(@RequestBody AppUser newAppUser) {

		System.out.println("Adding new App user: " + newAppUser);

		return appUserRepository.save(newAppUser);
	}

}
