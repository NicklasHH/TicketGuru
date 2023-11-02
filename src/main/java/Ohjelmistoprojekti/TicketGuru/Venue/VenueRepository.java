package Ohjelmistoprojekti.TicketGuru.Venue;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Long> {
	List<Venue> findAll();

	List<Venue> findByPlace(String place);
}

//Käyty läpi 01-11-2023
