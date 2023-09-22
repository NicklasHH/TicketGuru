package Ohjelmistoprojekti.TicketGuru.Ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

	private final TicketRepository ticketRepository;

	@Autowired
	public TicketRestController(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@GetMapping // http://localhost:8080/api/tickets
	ResponseEntity<List<Ticket>> all() {
		List<Ticket> events = ticketRepository.findAll(); // Hae kaikki liput tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

}