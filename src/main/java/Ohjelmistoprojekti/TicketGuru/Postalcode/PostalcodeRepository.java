package Ohjelmistoprojekti.TicketGuru.Postalcode;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PostalcodeRepository extends CrudRepository<Postalcode, String> {
	List<Postalcode> findAll();
	Optional<Postalcode> findByPostalcode(String postalcode);
}
