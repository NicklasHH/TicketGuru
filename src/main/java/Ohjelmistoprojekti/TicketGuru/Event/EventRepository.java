package Ohjelmistoprojekti.TicketGuru.Event;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findAll();

	List<Event> findByVenue_VenueId(Long venueId);

}