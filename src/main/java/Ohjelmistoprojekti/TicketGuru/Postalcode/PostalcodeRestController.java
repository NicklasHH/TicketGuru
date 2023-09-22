package Ohjelmistoprojekti.TicketGuru.Postalcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postalcodes")
public class PostalcodeRestController {

	private final PostalcodeRepository postalcodeRepository;

	@Autowired
	public PostalcodeRestController(PostalcodeRepository postalcodeRepository) {
		this.postalcodeRepository = postalcodeRepository;
	}

	@GetMapping // http://localhost:8080/api/postalcodes
	ResponseEntity<List<Postalcode>> all() {
		List<Postalcode> events = postalcodeRepository.findAll(); // Hae kaikki postinumerot tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

}