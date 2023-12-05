package Ohjelmistoprojekti.TicketGuru.Event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Ohjelmistoprojekti.TicketGuru.Venue.Venue;
import Ohjelmistoprojekti.TicketGuru.Ticket.Ticket;
import Ohjelmistoprojekti.TicketGuru.TicketType.TicketType;
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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id", nullable = false, updatable = false)
	private long eventId;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Ticket> tickets;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<TicketType> ticketTypes;

	@Column(name = "event_name", nullable = false)
	@NotEmpty(message = "Eventin nimi ei voi olla thjä")
	@Size(min = 1, max = 100)
	private String eventName;

	@Column(name = "description")
	@Size(min = 1, max = 500, message = "Lisätietojen merkkimäärä 1-500")
	private String description;

	@Column(name = "event_date", nullable = false)
	private String eventDate;

	@Column(name = "event_time", nullable = false)
	private String eventTime;

	@Column(name = "ticket_count", nullable = false)
	private int ticketCount;

	@ManyToOne
	@JoinColumn(name = "venue_id")
	private Venue venue;

	public Event() {

	}

	public Event(List<Ticket> tickets, List<TicketType> ticketTypes, @Size(min = 1, max = 100) String eventName,
			@Size(min = 1, max = 500) String description, String eventDate, String eventTime, int ticketCount,
			Venue venue) {
		super();
		this.tickets = tickets;
		this.ticketTypes = ticketTypes;
		this.eventName = eventName;
		this.description = description;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.ticketCount = ticketCount;
		this.venue = venue;
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

	public long getEventId() { // luotu getterit ja setterit eventId:lle Rest apia varten
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

	public List<TicketType> getTicketTypes() {
		return ticketTypes;
	}

	public void setTicketTypes(List<TicketType> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", tickets=" + tickets + ", ticketTypes=" + ticketTypes + ", eventName="
				+ eventName + ", description=" + description + ", eventDate=" + eventDate + ", eventTime=" + eventTime
				+ ", ticketCount=" + ticketCount + ", venue=" + venue + "]";
	}

}