package Ohjelmistoprojekti.TicketGuru.Ticket;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Ohjelmistoprojekti.TicketGuru.TicketGuruApplication;
import Ohjelmistoprojekti.TicketGuru.Event.Event;
import Ohjelmistoprojekti.TicketGuru.Event.EventRepository;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketType;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketTypeRepository;
import Ohjelmistoprojekti.TicketGuru.Transaction.Transaction;
import Ohjelmistoprojekti.TicketGuru.Transaction.TransactionRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

	private final TicketRepository ticketRepository;
	private final TicketService ticketService;

	@Autowired
	public TicketRestController(TicketRepository ticketRepository, TicketService ticketService) {
		this.ticketRepository = ticketRepository;
		this.ticketService = ticketService;
	}

	@Autowired
	private TicketTypeRepository ticketTypeRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private EventRepository eventRepository;

	// Palauttaa kaikki liput 
	// http://localhost:8080/api/tickets
	@GetMapping
	ResponseEntity<List<Ticket>> all() {
		List<Ticket> tickets = ticketRepository.findAll(); // Hae kaikki liput tietokannasta
		if (!tickets.isEmpty()) {
			return ResponseEntity.ok(tickets);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}



	// Palauttaa lipun id perusteella 
	// http://localhost:8080/api/tickets/1
	@GetMapping("/{id}")
	public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		if (ticket.isPresent()) {
			return ResponseEntity.ok(ticket.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Lipun isChecked arvon muuttaminen trueksi
	// http://localhost:8080/api/tickets/1/isChecked
	@GetMapping("/{id}/isChecked")
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

	// Palauttaa lipun tyypin id perusteella
	// http://localhost:8080/api/tickets/1/ticketType
	@GetMapping("/{id}/ticketType")
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

	// Palauttaa eventin id perusteella 
	// http://localhost:8080/api/tickets/1/event
	@GetMapping("/{id}/event")
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

	// Palauttaa transactionin id perusteella
	// http://localhost:8080/api/tickets/1/transaction
	@GetMapping("/{id}/transaction")
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

	// Muokkaa lippua id perusteella 
	// http://localhost:8080/api/tickets/1
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTicket(@Valid @RequestBody Ticket editedTicket, @PathVariable Long id) {
		if (!ticketRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tickettiä ei löytynyt id:llä " + id);
		}

		ResponseEntity<Object> validationResponse = ticketService.validateTicket(editedTicket);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse; // Palauta virhe, jos tarkistuksissa on ongelmia
		}

		editedTicket.setTicketId(id);
		Ticket updatedTicket = ticketRepository.save(editedTicket);

		return ResponseEntity.status(HttpStatus.CREATED).body(updatedTicket);
	}

	// Uusi lippu 
	// http://localhost:8080/api/tickets/
	@PostMapping
	public ResponseEntity<Object> createTicket(@Valid @RequestBody Ticket newTicket) {
		ResponseEntity<Object> validationResponse = ticketService.validateTicket(newTicket);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse; // Palauta virhe, jos tarkistuksissa on ongelmia
		}

		Ticket savedTicket = ticketRepository.save(newTicket);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
	}

	// Poista lippu id perusteella
	// http://localhost:8080/api/tickets/1
	@DeleteMapping("/{id}")
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
			return ResponseEntity.ok(ticket); // HTTP 200 OK, palauttaa poistetun lipun tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}
	
	// Palauttaa QR koodin lipusta 
	// http://localhost:8080/api/tickets/qr/1
	@GetMapping(value = "/qr/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public BufferedImage barbecueEAN13Barcode(@PathVariable Long id) throws Exception {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		if (ticket.isPresent()) {
			String info;
			info = ticket.get().getTicketId().toString() + "\n";
			info = info + ticket.get().getIsChecked() + "\n";
			info = info + ticket.get().getEvent().getEventName() + "\n";

			return (TicketGuruApplication.generateQRCodeImage(info));
		} else {
			return (TicketGuruApplication.generateQRCodeImage("Ei lippua"));
		}

	}

	// Haetaan jäljellä olevien lippujen määrä eventId:n mukaan
	// http://localhost:8080/api/tickets/ticketsLeft/1
	@GetMapping("/ticketsLeft/{id}")
	ResponseEntity<Integer> getAllTickets(@PathVariable Long id) {
		List<Ticket> tickets = ticketRepository.findByEvent_EventId(id);
		int ticketsLeft = 0;
		if (!tickets.isEmpty()) {
			Optional<Event> event = eventRepository.findById(id);
			if (!event.isEmpty()) {
				int allTickets = event.get().getTicketCount();
				int soldTickets = tickets.size();
				ticketsLeft = allTickets - soldTickets;
				return ResponseEntity.ok(ticketsLeft);// HTTP 200 OK
			}

			else {
				return ResponseEntity.ok(event.get().getTicketCount());
			}
		} else {
			Optional<Event> event = eventRepository.findById(id);
			if (!event.isEmpty()) {
				return ResponseEntity.ok(event.get().getTicketCount());
			}
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}
	
	// Merkitään id perusteella lippu käytetyksi
	// http://localhost:8080/api/tickets/1/check
	@PatchMapping("/{id}/check")
	public ResponseEntity<?> checkTicket(@PathVariable Long id) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		if (ticketOptional.isPresent()) {
			Ticket ticket = ticketOptional.get();
			ticket.setIsChecked(true); // Asetaa isChecked arvon true
			ticketRepository.save(ticket);
			return ResponseEntity.ok(ticket);
		} else {
			return ResponseEntity.notFound().build(); // Jos lippua ei löydy 404
		}
	}

	// Haetaan kaikki liput tietyn eventId:n mukaan
	// http://localhost:8080/api/tickets/eventSales/1
	@GetMapping("/eventSales/{id}")
	ResponseEntity<List<Ticket>> allTickets(@PathVariable Long id) {
		List<Ticket> tickets = ticketRepository.findByEvent_EventId(id);
		if (!tickets.isEmpty()) {
			return ResponseEntity.ok(tickets);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
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