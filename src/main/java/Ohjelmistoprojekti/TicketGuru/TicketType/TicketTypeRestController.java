package Ohjelmistoprojekti.TicketGuru.TicketType;

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

import Ohjelmistoprojekti.TicketGuru.Ticket.Ticket;
import Ohjelmistoprojekti.TicketGuru.Ticket.TicketRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickettypes")
public class TicketTypeRestController {

	private final TicketTypeRepository ticketTypeRepository;
	private final TicketTypeService ticketTypeService;

	@Autowired
	public TicketTypeRestController(TicketTypeRepository ticketTypeRepository, TicketTypeService ticketTypeService) {
		this.ticketTypeRepository = ticketTypeRepository;
		this.ticketTypeService = ticketTypeService;
	}

	@Autowired
	private TicketRepository ticketRepository;

	// Palauttaa kaikki lipputyypit 
	// http://localhost:8080/api/tickettypes
	@GetMapping
	ResponseEntity<List<TicketType>> all() {
		List<TicketType> ticketTypes = ticketTypeRepository.findAll(); // Hae kaikki lipputyypit tietokannasta
		if (!ticketTypes.isEmpty()) {
			return ResponseEntity.ok(ticketTypes);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa lipputyypin id:n perusteella
	// http://localhost:8080/api/tickettypes/1
	@GetMapping("/{id}")
	public ResponseEntity<TicketType> getTicketType(@PathVariable Long id) {
		Optional<TicketType> tickettypeOptional = ticketTypeRepository.findById(id);
		if (tickettypeOptional.isPresent()) {
			return ResponseEntity.ok(tickettypeOptional.get()); // HTTP OK 200
		} else {
			return ResponseEntity.notFound().build(); // HTTP ERROR 404 NOT FOUND
		}
	}

	// Palauttaa lipputyypin id:n perusteella sen hinnan
	// http://localhost:8080/api/ticekttypes/1/price
	@GetMapping("/{id}/price")
	public ResponseEntity<Object> getPrice(@PathVariable Long id) {
		Optional<TicketType> tickettypeOptional = ticketTypeRepository.findById(id);
		if (tickettypeOptional.isPresent()) {
			TicketType tickettype = tickettypeOptional.get();
			Map<String, Double> jsonResponse = new HashMap<>();
			jsonResponse.put("price", tickettype.getPrice());
			return ResponseEntity.ok(jsonResponse); // HTTP OK 200
		} else {
			return ResponseEntity.notFound().build(); // HTTP ERROR 404 NOT FOUND
		}
	}

	// Muokataan lipputyyppiä id perusteella 
	// http://localhost808/api/tickettypes/1
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTicketType(@Valid @RequestBody TicketType editedTicketType,
			@PathVariable Long id) {
		if (!ticketTypeRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TicketTypeä ei löytynyt id:llä " + id);
		}

		ResponseEntity<Object> validationResponse = ticketTypeService.validateTicketType(editedTicketType);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse; // Palauta virhe, jos tarkistuksissa on ongelmia
		}

		editedTicketType.setTicketTypeId(id);
		TicketType updatedTicketType = ticketTypeRepository.save(editedTicketType);

		return ResponseEntity.status(HttpStatus.CREATED).body(updatedTicketType);
	}

	// Luodaan uusi lipputyyppi 
	// http://localhost8080/api/tickettypes
	@PostMapping
	public ResponseEntity<Object> createTicketType(@Valid @RequestBody TicketType newTicketType) {
		ResponseEntity<Object> validationResponse = ticketTypeService.validateTicketType(newTicketType);

		if (validationResponse.getStatusCode() != HttpStatus.OK) {
			return validationResponse; // Palauta virhe, jos tarkistuksissa on ongelmia
		}

		TicketType savedTicketType = ticketTypeRepository.save(newTicketType);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTicketType);
	}

	// Poistetaan lipputyyppi ID perusteella
	// http://localhost:8080/api/tickettypes/1
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deleteTicketType(@PathVariable Long id) { // Hae lipputyyppi tietokannasta ja palauta
																		// vastaus
		Optional<TicketType> ticketTypeOptional = ticketTypeRepository.findById(id); // Palauttaa lipputyypin Id:N
																						// perusteella
		if (ticketTypeOptional.isPresent()) {
			TicketType ticketType = ticketTypeOptional.get();

			// Hae ja päivitä liittyvät liput
			List<Ticket> tickets = ticketRepository.findByTicketType_TicketTypeId(id);
			for (Ticket ticket : tickets) {
				ticket.setTicketType(null); // asettta lipputyypin nulliksi
			}
			ticketRepository.saveAll(tickets);

			ticketTypeRepository.deleteById(id); // Poistaa lipputyypin Id:n perusteella
			return ResponseEntity.ok(ticketType); // HTTP 200 OK, palauttaa poistetun lipputyypin tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}