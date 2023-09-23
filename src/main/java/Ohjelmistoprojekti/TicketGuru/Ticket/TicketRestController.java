package Ohjelmistoprojekti.TicketGuru.Ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		List<Ticket> tickets = ticketRepository.findAll(); // Hae kaikki liput tietokannasta
		if (!tickets.isEmpty()) {
			return ResponseEntity.ok(tickets);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	@DeleteMapping("/{id}") // http://localhost:8080/api/tickets/1
	public ResponseEntity<?> deleteTicket(@PathVariable Long id) { // Hae lippu tietokannasta ja palauta vastaus
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);// Palauttaa lipun Id:N perusteella
		if (ticketOptional.isPresent()) {
			Ticket ticket = ticketOptional.get();
			ticketRepository.deleteById(id); // Poistaa lipun Id:n perusteella
			return ResponseEntity.ok(ticket); // HTTP 200 OK, palauttaa poistetun lipun tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

}