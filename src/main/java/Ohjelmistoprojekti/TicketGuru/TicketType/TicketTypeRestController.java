package Ohjelmistoprojekti.TicketGuru.TicketType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickettypes")
public class TicketTypeRestController {

	private final TicketTypeRepository ticketTypeRepository;

	@Autowired
	public TicketTypeRestController(TicketTypeRepository ticketTypeRepository) {
		this.ticketTypeRepository = ticketTypeRepository;
	}

	@GetMapping // http://localhost:8080/api/tickettypes
	ResponseEntity<List<TicketType>> all() {
		List<TicketType> events = ticketTypeRepository.findAll(); // Hae kaikki lipputyypit tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

}