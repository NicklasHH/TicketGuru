package Ohjelmistoprojekti.TicketGuru.TicketType;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ohjelmistoprojekti.TicketGuru.Ticket.TicketRepository;

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
		List<TicketType> ticketTypes = ticketTypeRepository.findAll(); // Hae kaikki lipputyypit tietokannasta
		if (!ticketTypes.isEmpty()) {
			return ResponseEntity.ok(ticketTypes);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}
	
	// Palauttaa tickettypen id:n perusteella
	@GetMapping("/{id}") // http://localhost:8080/api/tickettypes/1
	public ResponseEntity<TicketType> getTicketType(@PathVariable Long id){
		Optional<TicketType> tickettype = ticketTypeRepository.findById(id);
		if (tickettype.isPresent()) {
			return ResponseEntity.ok(tickettype.get()); // HTTP OK 200
		} else {
			return ResponseEntity.notFound().build(); // HTTP ERROR 404 NOT FOUND
		}
	}	

	@DeleteMapping("/{id}") // http://localhost:8080/api/tickettypes/1
	public ResponseEntity<?> deleteTicketType(@PathVariable Long id) { // Hae lipputyyppi tietokannasta ja palauta vastaus
		Optional<TicketType> ticketTypeOptional = ticketTypeRepository.findById(id); // Palauttaa lipputyypin Id:N perusteella
		if (ticketTypeOptional.isPresent()) {
			TicketType ticketType = ticketTypeOptional.get();
			ticketTypeRepository.deleteById(id); // Poistaa lipputyypin Id:n perusteella
			return ResponseEntity.ok(ticketType); // HTTP 200 OK, palauttaa poistetun lipputyypin tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

}