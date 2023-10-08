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

	@Autowired
	public TicketTypeRestController(TicketTypeRepository ticketTypeRepository) {
		this.ticketTypeRepository = ticketTypeRepository;
	}
	
	@Autowired
	private TicketRepository ticketRepository;

	// Listaa kaikki lipputyypit
	@GetMapping // http://localhost:8080/api/tickettypes
	ResponseEntity<List<TicketType>> all() {
		List<TicketType> ticketTypes = ticketTypeRepository.findAll(); // Hae kaikki lipputyypit tietokannasta
		if (!ticketTypes.isEmpty()) {
			return ResponseEntity.ok(ticketTypes);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Palauttaa lipputyypin id:n perusteella
	@GetMapping("/{id}") // http://localhost:8080/api/tickettypes/1
	public ResponseEntity<TicketType> getTicketType(@PathVariable Long id) {
		Optional<TicketType> tickettypeOptional = ticketTypeRepository.findById(id);
		if (tickettypeOptional.isPresent()) {
			return ResponseEntity.ok(tickettypeOptional.get()); // HTTP OK 200
		} else {
			return ResponseEntity.notFound().build(); // HTTP ERROR 404 NOT FOUND
		}
	}
	
	// Palauttaa lipputyypin id:n perusteella sen hinnan
	@GetMapping("/{id}/price") // http://localhost:8080/api/ticekttypes/1/price
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
	
	// Luodaan uusi lipputyyppi
	@PostMapping // http://localhost8080/api/tickettypes
	public ResponseEntity<TicketType> addTicketType(@Valid @RequestBody TicketType ticketType) {
		Optional<TicketType> foundTicketType = ticketTypeRepository.findByTicketType(ticketType.getTicketType());
		
		if(foundTicketType.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); // HTTP 409 jos lipputyyppi on jo olemassa
		}
		
		TicketType savedTicketType = ticketTypeRepository.save(ticketType);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTicketType); // HTTP 201
	}
	
	// Muokataan lipputyyppiä
	@PutMapping("/{id}") // http://localhost808/api/tickettypes/1
	public ResponseEntity<Object> updateTicketType(@Valid @RequestBody TicketType editedTicketType, @PathVariable long id) {
		if (!ticketTypeRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tickettypea ei löytynyt id:llä " + id); // HTTP ERROR 404 NOT FOUND
		}

		editedTicketType.setTicketTypeId(id);
		TicketType updatedTicketType = ticketTypeRepository.save(editedTicketType);
		return ResponseEntity.ok(updatedTicketType); // HTTP OK 200
	}

	@DeleteMapping("/{id}") // http://localhost:8080/api/tickettypes/1
	public ResponseEntity<?> deleteTicketType(@PathVariable Long id) { // Hae lipputyyppi tietokannasta ja palauta
																		// vastaus
		Optional<TicketType> ticketTypeOptional = ticketTypeRepository.findById(id); // Palauttaa lipputyypin Id:N
																						// perusteella
		if (ticketTypeOptional.isPresent()) {
			TicketType ticketType = ticketTypeOptional.get();
			
			// Hae ja päivitä liittyvät liput
			List<Ticket> tickets = ticketRepository.findByTicketType_TicketTypeId(id);
			for(Ticket ticket : tickets) {
				ticket.setTicketType(null); // asettta lipputyypin nulliksi
			}
			
			ticketTypeRepository.deleteById(id); // Poistaa lipputyypin Id:n perusteella
			return ResponseEntity.ok(ticketType); // HTTP 200 OK, palauttaa poistetun lipputyypin tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
		
		
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationdExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}