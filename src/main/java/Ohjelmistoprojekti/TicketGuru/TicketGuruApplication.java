package Ohjelmistoprojekti.TicketGuru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
import Ohjelmistoprojekti.TicketGuru.AppUser.AppUser;
import Ohjelmistoprojekti.TicketGuru.AppUser.AppUserRepository;
import Ohjelmistoprojekti.TicketGuru.Event.Event;
import Ohjelmistoprojekti.TicketGuru.Event.EventRepository;
import Ohjelmistoprojekti.TicketGuru.Postalcode.Postalcode;
import Ohjelmistoprojekti.TicketGuru.Postalcode.PostalcodeRepository;
import Ohjelmistoprojekti.TicketGuru.Role.Role;
import Ohjelmistoprojekti.TicketGuru.Role.RoleRepository;
import Ohjelmistoprojekti.TicketGuru.Ticket.Ticket;
import Ohjelmistoprojekti.TicketGuru.Ticket.TicketRepository;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketType;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketTypeRepository;
import Ohjelmistoprojekti.TicketGuru.Transaction.Transaction;
import Ohjelmistoprojekti.TicketGuru.Transaction.TransactionRepository;
import Ohjelmistoprojekti.TicketGuru.Venue.Venue;
import Ohjelmistoprojekti.TicketGuru.Venue.VenueRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
*/


@SpringBootApplication
public class TicketGuruApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketGuruApplication.class, args);
	}
/*
	@Bean
	public CommandLineRunner initData(EventRepository eventRepository, PostalcodeRepository postalcodeRepository,
			AppUserRepository appUserRepository, RoleRepository roleRepository, TicketRepository ticketRepository,
			TicketTypeRepository ticketTyperepository, TransactionRepository transactionRepository,
			VenueRepository venueRepository) {
		return (args) -> {
			// Luodaan AppUser entiteetti jos tietokanta on tyhjä
			if (appUserRepository.count() == 0) {
				AppUser appUser = new AppUser();
				appUser.setUsername("käyttis");
				appUser.setPassword("Salasana");

				appUserRepository.save(appUser);
			}

			// Luodaan Event entiteetti jos tietokanta on tyhjä
			if (eventRepository.count() == 0) {
				Event event = new Event();
				event.setEventName("Esimerkkitapahtuma");
				event.setDescription("Esim");
				event.setEventDate("12-12-2023");
				event.setEventTime("18:00");
				event.setTicketCount(100);
				event.setVenue(null);

				eventRepository.save(event);
			}

			// Luodaan Postalcode entiteetti jos tietokanta on tyhjä
			if (postalcodeRepository.count() == 0) {
				Postalcode postalcode = new Postalcode();
				postalcode.setPostalcode("00100");
				postalcode.setPostOffice("Helsinki");

				postalcodeRepository.save(postalcode);
			}

			// Luodaan Role entiteetti jos tietokanta on tyhjä
			if (roleRepository.count() == 0) {
				Role role = new Role();
				role.setRoleName("Admin");

				roleRepository.save(role);
			}

			// Luodaan Ticket entiteetti jos tietokanta on tyhjä
			if (ticketRepository.count() == 0) {
				Ticket ticket = new Ticket();
				ticket.setEvent(null);
				ticket.setTicketType(null);
				ticket.setTransaction(null);
				ticket.setIsChecked(null);

				ticketRepository.save(ticket);
			}

			// Luodaan TicketType entiteetti jos tietokanta on tyhjä
			if (ticketTyperepository.count() == 0) {
				TicketType ticketType = new TicketType();
				ticketType.setTicketType("Aikuinen");

				ticketTyperepository.save(ticketType);
			}

			// Luodaan Transaction entiteetti jos tietokanta on tyhjä
			if (transactionRepository.count() == 0) {
				Transaction transaction = new Transaction();
				transaction.setAmount(50);
				transaction.setTransactionDate("22.09.2023");

				transactionRepository.save(transaction);
			}

			// Luodaan Venue entiteetti jos tietokanta on tyhjä
			if (venueRepository.count() == 0) {
				Venue venue = new Venue();
				venue.setPlace("jäähalli");
				venue.setStreetAddress("jäähallitie 1");

				venueRepository.save(venue);
			}
		};
	} */
}