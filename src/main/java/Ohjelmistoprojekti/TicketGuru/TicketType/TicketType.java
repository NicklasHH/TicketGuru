package Ohjelmistoprojekti.TicketGuru.TicketType;

import java.util.List;

import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Ohjelmistoprojekti.TicketGuru.Event.Event;
import Ohjelmistoprojekti.TicketGuru.Ticket.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Ticket_types")
public class TicketType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_type_id", nullable = false, updatable = false)
	private Long ticketTypeId;

	@Column(name = "ticket_type", nullable = false)
	@NotNull(message = "Tyyppi ei voi olla tyhjä")
	@Size(min = 1, max = 50)
	private String ticketType;

	@JsonIgnore
	@OneToMany(mappedBy = "ticketType", fetch = FetchType.EAGER)
	private List<Ticket> tickets;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	private Event event;

	@Column(name = "price", nullable = false)
	@NotNull(message = "Hinta ei voi olla tyhjä")
	@Digits(integer = 10, fraction = 2)
	private double price;

	public TicketType() {
	}

	public TicketType(String ticketType, double price, List<Ticket> tickets) {
		this.ticketType = ticketType;
		this.price = price;
		this.tickets = tickets;
	}

	public Long getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(Long ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "TicketType [ticketTypeId=" + ticketTypeId + ", price=" + price + ", tickets=" + tickets + "]";
	}

}
