package Ohjelmistoprojekti.TicketGuru.Role;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	List<Role> findAll();

	List<Role> findByAppUser_AppUserId(Long appUserId);

	List<Role> findByRoleName(String roleName);
}
