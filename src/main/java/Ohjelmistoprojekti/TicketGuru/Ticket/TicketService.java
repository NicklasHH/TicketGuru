package Ohjelmistoprojekti.TicketGuru.Ticket;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Ohjelmistoprojekti.TicketGuru.TicketType.TicketType;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketTypeRepository;
import Ohjelmistoprojekti.TicketGuru.Transaction.Transaction;
import Ohjelmistoprojekti.TicketGuru.Transaction.TransactionRepository;
import Ohjelmistoprojekti.TicketGuru.Event.Event;
import Ohjelmistoprojekti.TicketGuru.Event.EventRepository;

@Service
public class TicketService {

	@Autowired
	private TicketTypeRepository ticketTypeRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private EventRepository eventRepository;

	public ResponseEntity<Object> validateTicket(Ticket ticket) {

		Optional<TicketType> ticketTypeOptional = ticketTypeRepository
				.findById(ticket.getTicketType().getTicketTypeId());
		if (ticketTypeOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ticket Type ID:tä ei löytynyt");
		}

		Optional<Transaction> transactionOptional = transactionRepository
				.findById(ticket.getTransaction().getTransactionId());
		if (transactionOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Transaction ID:tä ei löytynyt");
		}

		Optional<Event> eventOptional = eventRepository.findById(ticket.getEvent().getEventId());
		if (eventOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Event ID:tä ei löytynyt");
		}

		return ResponseEntity.ok(null); // Kaikki tarkistukset menivät läpi
	}

}