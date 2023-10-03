package Ohjelmistoprojekti.TicketGuru.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import Ohjelmistoprojekti.TicketGuru.Ticket.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id", nullable = false)
	private Long transactionId;

	private double amount;

	@Column(name = "transaction_date")
	//@JsonFormat(pattern="yyyy-MM-dd")	
	private LocalDate transactionDate = LocalDate.now();
	
	@Column(name = "transaction_time")
	LocalDateTime transactionTime = LocalDateTime.now();

	@JsonIgnore
	@OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER)
	private List<Ticket> tickets;
	
	
	
	

	public Transaction() {
	}
	
	/*//Constructor ennen LocalDateTimea
	public Transaction(double amount, LocalDate transactionDate,  List<Ticket> tickets) {
		super();
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.tickets = tickets;
	}
	*/
	
	public Transaction(double amount, LocalDate transactionDate, LocalDateTime transactionTime, List<Ticket> tickets) {
		super();
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.tickets = tickets;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		//this.transactionDate = LocalDate.now();
		this.transactionDate = transactionDate;
		
	}
	
	public LocalDateTime getTransactionTime() {			
		return transactionTime;
	}
	
	public void setTransactionTime(LocalDateTime transactionTime) {
		//this.transactionTime = LocalDateTime.now();
		this.transactionTime = transactionTime;
		
		
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", transactionDate="
				+ transactionDate + ", transactionTime=" + transactionTime + ", tickets=" + tickets + "]";
	}

	
	/*@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", transactionDate="
				+ transactionDate + ", tickets=" + tickets + "]";
	}
	*/
}
