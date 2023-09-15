package Ohjelmistoprojekti.TicketGuru.domain;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>{

    //Role findByRole(String role); //laitoin piiloon, kun herjasi virhettä konsolissa -Nadja
}
