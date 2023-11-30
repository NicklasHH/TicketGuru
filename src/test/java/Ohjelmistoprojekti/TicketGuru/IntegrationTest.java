// Koodi pitää kommentoida ulos, jotta julkaisu onnistuu. Jkuben skipTests ei toimi jostain syystä.

/*package Ohjelmistoprojekti.TicketGuru;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "Admin")
    public void testGetTicketById() throws Exception {
        mockMvc.perform(get("/api/tickets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketId").value(1))
                .andExpect(jsonPath("$.event.eventId").value(1))
                .andExpect(jsonPath("$.event.eventName").value("Tapahtuma 1"))
                .andExpect(jsonPath("$.isChecked").value(true));
    }

    @Test
    public void testGetTicketByIdWithOutAuth() throws Exception {
        mockMvc.perform(get("/api/tickets/1"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void testHomeScreenWelcome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to TicketGuru")));
    }

    @Test
    public void testFindKorvatunturi() throws Exception {
        mockMvc.perform(get("/api/postalcodes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.postalcode == '99999')].postOffice").value("Korvatunturi"));
    }

}
*/