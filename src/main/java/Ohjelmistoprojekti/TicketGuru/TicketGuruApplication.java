package Ohjelmistoprojekti.TicketGuru;

import Ohjelmistoprojekti.TicketGuru.domain.Event;
import Ohjelmistoprojekti.TicketGuru.domain.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketGuruApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketGuruApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EventRepository eventRepository) {
        return (args) -> {
            // Luodaan Event entiteetti jos tietokanta on tyhjä
            if (eventRepository.count() == 0) {
                Event event = new Event();
                event.setEventName("Esimerkkitapahtuma");
                event.setDescription("Esim");
                event.setEventDate("12-12-2023");
                event.setEventTime("18:00");
                event.setTicketCount(100);

                eventRepository.save(event);
            }
        };
    }
}



