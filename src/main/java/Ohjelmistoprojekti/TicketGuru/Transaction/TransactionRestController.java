package Ohjelmistoprojekti.TicketGuru.Transaction;

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
@RequestMapping("/api/transactions")
public class TransactionRestController {

	private final TransactionRepository transactionRepository;

	@Autowired
	public TransactionRestController(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Autowired
	private TicketRepository ticketRepository;

	// Listaa kaikki myyntitapahtumat  
	// http://localhost:8080/api/transactions
	@GetMapping
	ResponseEntity<List<Transaction>> all() {
		List<Transaction> transactions = transactionRepository.findAll(); // Hae kaikki transaktiot tietokannasta
		if (!transactions.isEmpty()) {
			System.out.println("200 - Listataan myyntitapahtumat - TransactionRestController");
			return ResponseEntity.ok(transactions);// HTTP 200 OK
		} else {
			System.out.println("404 - Listattavia myyntitapahtumia ei löytynyt - TransactionRestController");
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Hae myyntitapahtuma id:llä  
	// http://localhost:8080/api/transactions/1
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
		Optional<Transaction> transaction = transactionRepository.findById(id); // Hae tapahtuma ID:n perusteella
		if (transaction.isPresent()) {
			System.out.println("200 - haettu id löytyi - TransactionRestController, id: " + id);
			return ResponseEntity.ok(transaction.get()); // HTTP 200 OK
		} else {
			System.out.println("404 - haettua id:tä ei löytynyt - TransactionRestController, haettu id: " + id);
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Lisää uusi myyntitapahtuma  
	// http://localhost:8080/api/transactions
	@PostMapping
	Transaction newTransaction(@Valid @RequestBody Transaction newTransaction) {

		if (!newTransaction.isTransactionOk()) {
			System.out.println("200 - transactionOk == false -luotu uusi myyntitapahtuma - TransactionRestController:  "
					+ newTransaction);
		} else {
			System.out.println("200 - luotu uusi myyntitapahtuma - TransactionRestController:  " + newTransaction);
		}

		return transactionRepository.save(newTransaction);
	}

	// Muokkaa myyntitapahtumaa 
	// http://localhost:8080/api/appusers/id
	@PutMapping("/{id}")
	public ResponseEntity<Object> editTransaction(@Valid @RequestBody Transaction editedTransaction,
			@PathVariable Long id) {
		// Jos id:tä ei ole
		if (!transactionRepository.existsById(id)) {

			System.out.println("404 - ei voi muokata, id:tä ei ole olemassa - TransactionRestController");
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}

		// jos id jota halutaan muokata on olemassa
		editedTransaction.setTransactionId(id);
		transactionRepository.save(editedTransaction);
		System.out.println("200 - muokkaus onnistui - TransactionRestController, id: " + id);
		return ResponseEntity.ok(editedTransaction); // HTTP 200 OK
	}

	// Poista myyntitapahtuma id:n perusteella 
	// http://localhost:8080/api/transactions/1
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable Long id) { // Hae myyntitapahtuma tietokannasta ja palauta
																		// vastaus
		Optional<Transaction> transactionOptional = transactionRepository.findById(id);//
		if (transactionOptional.isPresent()) {
			Transaction transaction = transactionOptional.get();

			// Hae kaikki tähän kyseiseen myyntitapahtumaan liitetyt liput
			List<Ticket> tickets = ticketRepository.findByTransaction_TransactionId(id);
			for (Ticket ticket : tickets) {
				ticket.setTransaction(null);
			}
			ticketRepository.saveAll(tickets);

			transactionRepository.deleteById(id); // Poistetaan myyntitapahtuma id:n perusteella
			System.out.println("200 - myyntitapahtuman poisto onnistui - TransactionRestController, id: " + id);
			return ResponseEntity.ok(transaction); // 200
		} else {
			System.out.println("404 - Ei löytynyt poistettavaa - TransactionRestController, id: " + id);
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found

		}
	}

	// Validointivirheiden käsittely
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		System.out.println("VALIDOINTIVIRHE: " + errors);
		return errors;
	}

}

//Käyty läpi 01-11-2023