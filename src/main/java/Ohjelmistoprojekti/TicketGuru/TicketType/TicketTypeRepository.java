package Ohjelmistoprojekti.TicketGuru.TicketType;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TicketTypeRepository extends CrudRepository<TicketType, Long> {
	List<TicketType> findAll();
	
	Optional<TicketType> findByTicketType(String ticketType);

	List<TicketType> findByEvent_EventId(Long eventId);

	List<TicketType> findByTickets_TicketId(Long ticketId);
}
