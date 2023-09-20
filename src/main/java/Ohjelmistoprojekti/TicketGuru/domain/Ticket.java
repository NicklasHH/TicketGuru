package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long ticketId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eventId")
	private Event event;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ticketTypeId")
	private TicketType ticketType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transactionId")
	private Transaction transaction;

	private Boolean isChecked;

	public Ticket() {
	}

	public Ticket(Event event, TicketType ticketType, Transaction transaction, Boolean isChecked) {
		super();
		this.event = event;
		this.ticketType = ticketType;
		this.transaction = transaction;
		this.isChecked = isChecked;
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

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", event=" + event + ", ticketType=" + ticketType + ", transaction="
				+ transaction + ", isChecked=" + isChecked + "]";
	}

}
