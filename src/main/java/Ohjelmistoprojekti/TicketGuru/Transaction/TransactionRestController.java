package Ohjelmistoprojekti.TicketGuru.Transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionRestController {

	private final TransactionRepository transactionRepository;

	@Autowired
	public TransactionRestController(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	// Listaa kaikki myyntitapahtumat
	@GetMapping // http://localhost:8080/api/transactions
	ResponseEntity<List<Transaction>> all() {
		List<Transaction> transactions = transactionRepository.findAll(); // Hae kaikki transaktiot tietokannasta
		if (!transactions.isEmpty()) {
			return ResponseEntity.ok(transactions);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Hae myyntitapahtuma id:llä
	@GetMapping("/{id}") // http://localhost:8080/api/transactions/1
	public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
		Optional<Transaction> transaction = transactionRepository.findById(id); // Hae tapahtuma ID:n perusteella
		if (transaction.isPresent()) {
			return ResponseEntity.ok(transaction.get()); // HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Lisää uusi myyntitapahtuma
	@PostMapping("/") // http://localhost:8080/api/transactions/
	Transaction newTransaction(@RequestBody Transaction newTransaction) {

		System.out.println("Adding new transaction: " + newTransaction);

		return transactionRepository.save(newTransaction);
	}

	@DeleteMapping("/{id}") // http://localhost:8080/api/transactions/1
	public ResponseEntity<?> deleteTransaction(@PathVariable Long id) { // Hae transaktio tietokannasta ja palauta
																		// vastaus
		Optional<Transaction> transactionOptional = transactionRepository.findById(id);// Palauttaa transaktion Id:N
																						// perusteella
		if (transactionOptional.isPresent()) {
			Transaction transaction = transactionOptional.get();
			transactionRepository.deleteById(id); // Poistaa transaktion Id:n perusteella
			return ResponseEntity.ok(transaction); // HTTP 200 OK, palauttaa poistetun transaktion tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

}