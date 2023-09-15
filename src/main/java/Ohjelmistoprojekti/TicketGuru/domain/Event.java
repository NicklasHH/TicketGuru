package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eventId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event") 
	private List<Ticket> tickets;
	
	
	private String eventName;
	private String date;
	private int ticketCount;
	private int venueId;
	private String description;

	public Event() {

	}
	
	

	public Event(long eventId, List<Ticket> tickets, String eventName, String date, int ticketCount, int venueId,
			String description) {
		super();
		this.eventId = eventId;
		this.tickets = tickets;
		this.eventName = eventName;
		this.date = date;
		this.ticketCount = ticketCount;
		this.venueId = venueId;
		this.description = description;
	}
	
	



	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	


}