package Ohjelmistoprojekti.TicketGuru.Postalcode;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostalcodeRepository extends CrudRepository<Postalcode, Long> {
	List<Postalcode> findAll();
}
