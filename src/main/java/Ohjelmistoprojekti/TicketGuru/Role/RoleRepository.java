package Ohjelmistoprojekti.TicketGuru.Role;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	List<Role> findAll();

	List<Role> findByAppUser_AppUserId(Long appUserId);

	// Role findByRole(String role); //laitoin piiloon, kun herjasi virhettä
	// konsolissa -Nadja
}
