package Ohjelmistoprojekti.TicketGuru.AppUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appusers")
public class AppUserRestController {

	private final AppUserRepository appUserRepository;

	@Autowired
	public AppUserRestController(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@GetMapping // http://localhost:8080/api/appusers
	ResponseEntity<List<AppUser>> all() {
		List<AppUser> events = appUserRepository.findAll(); // Hae kaikki appuserit tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

}
