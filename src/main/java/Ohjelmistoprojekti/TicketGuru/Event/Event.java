package Ohjelmistoprojekti.TicketGuru.Event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Ohjelmistoprojekti.TicketGuru.Venue.Venue;
import Ohjelmistoprojekti.TicketGuru.Ticket.Ticket;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private long eventId;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Ticket> tickets;

	@Column(nullable = false)
	@Size(min = 1, max = 100)
	private String eventName;

	@Size(min = 1, max = 500)
	private String description;

	@Column(nullable = false)
	private String eventDate;

	@Column(nullable = false)
	private String eventTime;

	@Column(nullable = false)
	private int ticketCount;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "venueId")
	private Venue venue;

	public Event() {

	}

	public Event(List<Ticket> tickets, String eventName, String description, String eventDate, String eventTime,
			int ticketCount, Venue venue) {
		super();
		this.tickets = tickets;
		this.eventName = eventName;
		this.description = description;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.ticketCount = ticketCount;
		this.venue = venue;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", tickets=" + tickets + ", eventName=" + eventName + ", description="
				+ description + ", eventDate=" + eventDate + ", eventTime=" + eventTime + ", ticketCount=" + ticketCount
				+ ", venue=" + venue + "]";
	}

}