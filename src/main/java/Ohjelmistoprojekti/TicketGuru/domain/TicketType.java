package Ohjelmistoprojekti.TicketGuru.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TicketType {
	//ticketTypeId(pk) ticketType(char50) price(char10)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketTypeId;

	@Column(nullable = false)
	private String price;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketType")
	private List<Ticket> tickets;

	public TicketType() {
	}

	public TicketType(String price, List<Ticket> tickets) {
		this.price = price;
		this.tickets = tickets;
	}

	public Long getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(Long ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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
