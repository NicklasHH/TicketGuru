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
	//ticketId(pk) eventId(fk int) ticketTypeId(fk int)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;

	
    @ManyToOne
    @JoinColumn(name = "eventId")
    private Event event;

	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name = "ticketTypeId")
	private TicketType ticketType;

	public Ticket() {
	}

	public Ticket(Long ticketId, Event event, TicketType ticketType) {
		super();
		this.ticketId = ticketId;
		this.event = event;
		this.ticketType = ticketType;
	}

	public Ticket(Long ticketId, TicketType ticketType) {
		super();
		this.ticketId = ticketId;
		this.ticketType = ticketType;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", event=" + event + ", ticketType=" + ticketType + "]";
	}
	
	



}
