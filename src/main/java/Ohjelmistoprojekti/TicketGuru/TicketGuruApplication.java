package Ohjelmistoprojekti.TicketGuru;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketGuruApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketGuruApplication.class, args);
		
		LocalDate lt = LocalDate.now();
		
		LocalDateTime ldt = LocalDateTime.now();
		
		System.out.println("TicketGuru Spring Boot Application - Localdate: " + lt);
		System.out.println("Localdatetime: " + ldt.getHour() + ":"+ ldt.getMinute()); 
	}

}