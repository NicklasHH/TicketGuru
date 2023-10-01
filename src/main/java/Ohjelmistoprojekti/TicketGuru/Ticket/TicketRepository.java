package Ohjelmistoprojekti.TicketGuru.Ticket;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
	List<Ticket> findAll();

	List<Ticket> findByEvent_EventId(Long eventId);
	
	List<Ticket> findByTicketType_TicketTypeId(Long ticketTypeId);

}
