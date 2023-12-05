package Ohjelmistoprojekti.TicketGuru.AppUser;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	List<AppUser> findAll();

	List<AppUser> findByRole_RoleId(Long roleId);

	List<AppUser> findByUsername(String username);

	// Suorittaa JPQL-kyselyn käyttäjänimen perusteella
	@Query("SELECT u FROM AppUser u WHERE u.username = :username")
	AppUser findByUsername2(@Param("username") String username);
}