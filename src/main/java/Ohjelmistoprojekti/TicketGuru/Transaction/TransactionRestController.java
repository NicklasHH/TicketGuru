package Ohjelmistoprojekti.TicketGuru.Transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
			System.out.println("200 - Listataan myyntitapahtumat - TransactionRestController");
			return ResponseEntity.ok(transactions);// HTTP 200 OK
		} else {
			System.out.println("404 - Listattavia myyntitapahtumia ei löytynyt - TransactionRestController");
			return ResponseEntity.notFound().build();// HTTP 404 Not Found
		}
	}

	// Hae myyntitapahtuma id:llä
	@GetMapping("/{id}") // http://localhost:8080/api/transactions/1
	public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
		Optional<Transaction> transaction = transactionRepository.findById(id); // Hae tapahtuma ID:n perusteella
		if (transaction.isPresent()) {
			System.out.println("200 - haettu id löytyi - TransactionRestController, id: " + id );
			return ResponseEntity.ok(transaction.get()); // HTTP 200 OK
		} else {
			System.out.println("404 - haettua id:tä ei löytynyt - TransactionRestController, haettu id: " + id);
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	}

	// Lisää uusi myyntitapahtuma
	@PostMapping // http://localhost:8080/api/transactions
	Transaction newTransaction(@RequestBody Transaction newTransaction) {

		System.out.println("200 - luotu uusi myyntitapahtuma - TransactionRestController:  " + newTransaction);

		return transactionRepository.save(newTransaction);
	}
	
	// Muokkaa myyntitapahtumaa
	@PutMapping("/{id}") // http://localhost:8080/api/appusers/id
	public ResponseEntity<Object> editTransaction(@RequestBody Transaction editedTransaction, @PathVariable Long id) {
		// Jos id:tä ei ole 
		if (!transactionRepository.existsById(id)) {
			
			System.out.println("404 - ei voi muokata, id:tä ei ole olemassa - TransactionRestController");
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
		
		//jos id jota halutaan muokata on olemassa
		editedTransaction.setTransactionId(id);		
		transactionRepository.save(editedTransaction);
		System.out.println("200 - muokkaus onnistui - TransactionRestController, id: " + id);
		return ResponseEntity.ok(editedTransaction); // HTTP 200 OK
	}

	@DeleteMapping("/{id}") // http://localhost:8080/api/transactions/1

														// perusteella
	public ResponseEntity<?> deleteTransaction(@PathVariable Long id) { // Hae myyntitapahtuma tietokannasta ja palauta vastaus
		Optional<Transaction> transactionOptional = transactionRepository.findById(id);// Palauttaa myyntitapahtuman Id:N perusteella
		if (transactionOptional.isPresent()) {
			Transaction transaction = transactionOptional.get();
			transactionRepository.deleteById(id); // Poistaa myyntitapahtuman Id:n perusteella
			System.out.println("200 - myyntitapahtuman poisto onnistui - TransactionRestController, id: " + id);
			return ResponseEntity.ok(transaction); // HTTP 200 OK, palauttaa poistetun myyntitapahtuman tiedot
		} else {
			System.out.println("404 - Ei löytynyt poistettavaa - TransactionRestController, id: " + id);
			return ResponseEntity.notFound().build(); // HTTP 404 Not Found
		}
	} 
	


}