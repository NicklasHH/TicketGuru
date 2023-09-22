package Ohjelmistoprojekti.TicketGuru.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		List<Transaction> events = transactionRepository.findAll(); // Hae kaikki transaktiot tietokannasta
		if (!events.isEmpty()) {
			return ResponseEntity.ok(events);// HTTP 200 OK
		} else {
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

}