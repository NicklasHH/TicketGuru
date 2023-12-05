// Koodi pitää kommentoida ulos, jotta julkaisu onnistuu. Jkuben skipTests ei toimi jostain syystä.

package Ohjelmistoprojekti.TicketGuru;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import Ohjelmistoprojekti.TicketGuru.Role.Role;
import Ohjelmistoprojekti.TicketGuru.Role.RoleRepository;

@DataJpaTest
class RoleTests {

	@Autowired
	RoleRepository roleRepository;

	// testataan uuden roolin lisäys
	@Test
	@Order(1)
	public void lisaaRooli() {
		Role role = new Role();
		role.setRoleName("testaaja");
		roleRepository.save(role);
		List<Role> roles = roleRepository.findByRoleName("testaaja");
		assertThat(roles.get(0).getRoleName().equals("testaaja"));
	}

	// testataan roolin muuttaminen
	@Test
	@Order(2)
	public void muutaRooli() {
		Role role = new Role();
		role.setRoleName("testaaja");
		roleRepository.save(role);
		role.setRoleName("Enpä testaakkaan");
		roleRepository.save(role);
		List<Role> roles = roleRepository.findByRoleName("Enpä testaakkaan");
		assertThat(roles.get(0).getRoleName().equals("Enpä testaakkaan"));
	}

	// testataan uuden roolin poisto
	@Test
	@Order(3)
	public void poistaRooli() {
		Role role = new Role();
		role.setRoleName("testaaja");
		roleRepository.save(role);
		roleRepository.deleteById(role.getRoleId());
		List<Role> roles = roleRepository.findByRoleName("testaaja");
		assertEquals(0, roles.size());
	}

	// testataan kaikkien roolien haku
	@Test
	@Order(4)
	public void teeRoolit() {
		Role role = new Role();
		role.setRoleName("testaaja");
		roleRepository.save(role);
		Role role2 = new Role();
		role2.setRoleName("testaaja2");
		roleRepository.save(role2);
		Role role3 = new Role();
		role3.setRoleName("testaaja3");
		roleRepository.save(role3);

		Iterable<Role> roles = roleRepository.findAll();
		System.out.println("roolit" + roles);
		assertEquals(3, roleRepository.findAll().size());
	}

	// testataan roolin nimen hakeminen
	@Test
	@Order(5)
	public void haeRooli() {
		Role role = new Role();
		role.setRoleName("testaaja");
		roleRepository.save(role);
		Role role2 = new Role();
		role2.setRoleName("testaaja2");
		roleRepository.save(role2);
		Role role3 = new Role();
		role3.setRoleName("testaaja3");
		roleRepository.save(role3);

		List<Role> roles = roleRepository.findByRoleName("testaaja2");
		assertThat(roles.get(0).getRoleName().equals("testaaja2"));
	}

}
