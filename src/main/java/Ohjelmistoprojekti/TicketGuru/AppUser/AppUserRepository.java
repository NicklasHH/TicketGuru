package Ohjelmistoprojekti.TicketGuru.AppUser;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	List<AppUser> findAll();
	// AppUser findByUsername(String username);
}