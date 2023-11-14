package Ohjelmistoprojekti.TicketGuru;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest //When testing with other than development database
//@DataJpaTest //@DataJpaTest annotation will configure in-memory database, JPA and SpringData. It also turns on SQL logging
public class TicketGuruRepositoryTests {

}
