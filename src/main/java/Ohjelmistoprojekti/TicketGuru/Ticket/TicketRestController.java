package Ohjelmistoprojekti.TicketGuru.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Ohjelmistoprojekti.TicketGuru.TicketType.TicketType;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketTypeRepository;
import Ohjelmistoprojekti.TicketGuru.Transaction.Transaction;
import Ohjelmistoprojekti.TicketGuru.Transaction.TransactionRepository;
import jakarta.validation.Valid;
import Ohjelmistoprojekti.TicketGuru.Event.Event;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

	private final TicketRepository ticketRepository;

	@Autowired
	public TicketRestController(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Autowired
	private TicketTypeRepository ticketTypeRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	// kaikki liput
	@GetMapping // http://localhost:8080/api/tickets
	ResponseEntity<List<Ticket>> all() {
		List<Ticket> tickets = ticketRepository.findAll(); // Hae kaikki liput tietokannasta
		if (!tickets.isEmpty()) {
			return ResponseEntity.ok(tickets);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// lippu id:n perusteella
	@GetMapping("/{id}") // http://localhost:8080/api/tickets/1
	public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		if (ticket.isPresent()) {
			return ResponseEntity.ok(ticket.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// lippu tarkistettu
	@GetMapping("/{id}/isChecked") // http://localhost:8080/api/tickets/1/isChecked
	public ResponseEntity<Object> getPassword(@PathVariable long id) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		if (ticketOptional.isPresent()) {
			Ticket ticket = ticketOptional.get();
			Map<String, Boolean> jsonResponse = new HashMap<>();
			jsonResponse.put("isChecked", ticket.getIsChecked());
			return ResponseEntity.ok(jsonResponse);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// lipun tyyppi
	@GetMapping("/{id}/ticketType") // http://localhost:8080/api/tickets/1/ticketType
	public ResponseEntity<TicketType> getTicketType(@PathVariable long id) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		if (ticketOptional.isPresent()) {
			Ticket ticket = ticketOptional.get();
			if (ticket.getTicketType() != null) {
				TicketType ticketType = ticket.getTicketType();
				return ResponseEntity.ok(ticketType);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// lipun tapahtuma
	@GetMapping("/{id}/event") // http://localhost:8080/api/tickets/1/event
	public ResponseEntity<Event> getEvent(@PathVariable long id) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		if (ticketOptional.isPresent()) {
			Ticket ticket = ticketOptional.get();
			if (ticket.getEvent() != null) {
				Event event = ticket.getEvent();
				return ResponseEntity.ok(event);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// lipun myynti
	@GetMapping("/{id}/transaction") // http://localhost:8080/api/tickets/1/transaction
	public ResponseEntity<Transaction> getTransaction(@PathVariable long id) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		if (ticketOptional.isPresent()) {
			Ticket ticket = ticketOptional.get();
			if (ticket.getTransaction() != null) {
				Transaction transaction = ticket.getTransaction();
				return ResponseEntity.ok(transaction);
			} else {				
				return ResponseEntity.notFound().build();
			}
		} else {			
			return ResponseEntity.notFound().build();
		}
	}

	// uusi lippu
	@PostMapping // http://localhost:8080/api/tickets/
	Ticket newTicket(@Valid @RequestBody Ticket newTicket) {
		System.out.println("Adding new ticket: " + newTicket);
		return ticketRepository.save(newTicket);
	}

	// muokkaa lippua
	@PutMapping("/{id}") // http://localhost:8080/api/tickets/1
	public ResponseEntity<Object> editTicket(@Valid @RequestBody Ticket editedTicket, @PathVariable Long id) {
		if (!ticketRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tickettiä ei löytynyt id:llä " + id); // HTTP 404 Not Found
		}
		editedTicket.setTicketId(id);
		Ticket updatedTicket = ticketRepository.save(editedTicket);
		return ResponseEntity.ok(updatedTicket); // HTTP 200 OK
	}

	// poista lippu
	@DeleteMapping("/{id}") // http://localhost:8080/api/tickets/1
	public ResponseEntity<?> deleteTicket(@PathVariable Long id) { // Hae lippu tietokannasta ja palauta vastaus
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);// Palauttaa lipun Id:N perusteella
		if (ticketOptional.isPresent()) {
			Ticket ticket = ticketOptional.get();

			List<TicketType> ticketTypes = ticketTypeRepository.findByTickets_TicketId(id);
			for (TicketType ticketType : ticketTypes) {
				ticketType.setTickets(null);
			}
			ticketTypeRepository.saveAll(ticketTypes);

			List<Transaction> transactions = transactionRepository.findByTickets_TicketId(id);
			for (Transaction transaction : transactions) {
				transaction.setTickets(null);
			}
			transactionRepository.saveAll(transactions);

			ticketRepository.deleteById(id); // Poistaa lipun Id:n perusteella
			System.out.println("200 - lipun poisto ok - TicketRestController");
			return ResponseEntity.ok(ticket); // HTTP 200 OK, palauttaa poistetun lipun tiedot
		} else {
			System.out.println("404 - Ei löytynyt poistettavaa - TicketRestController");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tickettiä ei löytynyt id:llä " + id); // HTTP 404 Not Found

		}
	}
	    // Validointi virheiden käsittely
	    @ResponseStatus(HttpStatus.BAD_REQUEST) // HTTP 400 Bad request
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> { // Hakee kaikki virheet
	            String fieldName = ((FieldError) error).getField(); // Haetaan virheen aiheuttaneen kentän nimi
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return errors;
	    }
}