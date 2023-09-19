package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private long eventId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Ticket> tickets;

	@Column(nullable = false)
	@Size(min = 1, max = 100)
	private String eventName;

	@Size(min = 1, max = 500)
	private String description;

	@Column(nullable = false)
	private String date;
	
	@Column(nullable = false)
	private int ticketCount;
	
	@Column(nullable = false)
	private int venueId;

	public Event() {

	}

	public Event(List<Ticket> tickets, String eventName, String date, int ticketCount, int venueId,
			String description) {
		super();
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