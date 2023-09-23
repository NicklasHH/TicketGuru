package Ohjelmistoprojekti.TicketGuru.Transaction;

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
@RequestMapping("/api/transactions")
public class TransactionRestController {

	private final TransactionRepository transactionRepository;

	@Autowired
	public TransactionRestController(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@GetMapping // http://localhost:8080/api/transactions
	ResponseEntity<List<Transaction>> all() {
		List<Transaction> transactions = transactionRepository.findAll(); // Hae kaikki transaktiot tietokannasta
		if (!transactions.isEmpty()) {
			return ResponseEntity.ok(transactions);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	@DeleteMapping("/{id}") // http://localhost:8080/api/transactions/1
	public ResponseEntity<?> deleteTransaction(@PathVariable Long id) { // Hae transaktio tietokannasta ja palauta vastaus
		Optional<Transaction> transactionOptional = transactionRepository.findById(id);// Palauttaa transaktion Id:N perusteella
		if (transactionOptional.isPresent()) {
			Transaction transaction = transactionOptional.get();
			transactionRepository.deleteById(id); // Poistaa transaktion Id:n perusteella
			return ResponseEntity.ok(transaction); // HTTP 200 OK, palauttaa poistetun transaktion tiedot
		} else {
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

}