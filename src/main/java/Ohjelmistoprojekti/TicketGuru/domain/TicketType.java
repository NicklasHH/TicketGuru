package Ohjelmistoprojekti.TicketGuru.domain;

import java.util.List;

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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TicketTypes")
public class TicketType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long ticketTypeId;

	@Size(min = 1, max = 50)
	@Column(nullable = false)
	private String ticketType;

	@OneToMany(mappedBy = "ticketType")
	private List<Ticket> tickets;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eventId")
	private Event event;

	@Column(nullable = false)
	private double price;

	public TicketType() {
	}

	public TicketType(double price, List<Ticket> tickets) {
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

	@Override
	public String toString() {
		return "TicketType [ticketTypeId=" + ticketTypeId + ", price=" + price + ", tickets=" + tickets + "]";
	}

}
