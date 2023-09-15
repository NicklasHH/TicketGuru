package Ohjelmistoprojekti.TicketGuru.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "eventName")
	private List<Event> events;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ticketTypeId")
	private TicketType ticketType;

	public Ticket() {
	}

	public Ticket(Long ticketId, List<Event> events, TicketType ticketType) {
		super();
		this.ticketId = ticketId;
		this.events = events;
		this.ticketType = ticketType;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", events=" + events + ", ticketType=" + ticketType + "]";
	}
	

}
