package Ohjelmistoprojekti.TicketGuru.Transaction;

//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id", nullable = false)
	private Long transactionId;

	@NotNull
	private double amount; // voisiko/miten Ticket lähettää tämän tiedon Transactionille?

	@NotNull
	@Column(name = "transaction_ok") // Voisiko tämä tieto olla yhteydessä Ticketiin?
	private boolean transactionOk = false;

	// Jos käytetään: LOCALDATE, LOCALDATETIME
	// @Column(name = "transaction_date")
	// @JsonFormat(pattern="yyyy-MM-dd")
	// private LocalDate transactionDate = LocalDate.now(); jos tämä käytössä,
	// muista muuttaa myös setTransactionOk()
	// private LocalDate transactionDate;

	// @Column(name = "transaction_time")
	// private LocalDateTime transactionTime = LocalDateTime.now(); jos tämä
	// käytössä, muista muuttaa myös setTransactionOk()
	// private LocalDateTime transactionTime;

	// Jos käytetään: STRING
	@NotNull
	@Size(min = 10, max = 10) // "yyyy-dd-mm"
	@Column(name = "transaction_date")
	private String transactionDate;

	@NotNull
	@Size(min = 8, max = 8) // "hh:mm:ss"
	@Column(name = "transaction_time")
	private String transactionTime;

//	private String timestamp; //for testing, prints the last time transaction has been edited

	@JsonIgnore
	@OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER)
	private List<Ticket> tickets;

	public Transaction() {
	}

	/*
	 * public Transaction(double amount, boolean transactionOk, String
	 * transactionDate, String transactionTime, String timestamp, List<Ticket>
	 * tickets) { super(); this.amount = amount; this.transactionOk = transactionOk;
	 * this.transactionDate = transactionDate; this.transactionTime =
	 * transactionTime; this.timestamp = timestamp; this.tickets = tickets;
	 * 
	 * }
	 */

	public Transaction(double amount, boolean transactionOk, String transactionDate, String transactionTime,
			List<Ticket> tickets) {
		super();
		this.amount = amount;
		this.transactionOk = transactionOk;
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

	public boolean isTransactionOk() {
		return transactionOk;
	}

	public void setTransactionOk(boolean transactionOk) {
		this.transactionOk = transactionOk;

		// LOCALDATE, LOCALDATETIME
		// LocalDate ld = LocalDate.now();
		// LocalDateTime ldt = LocalDateTime.now();

		// setTransactionDate(ld); //Myyntitapahtuma-aika merkitään vasta kun
		// myyntitapahtuma ok
		// setTransactionTime(ldt);

		// STRING
		System.out.println("Transaction.java:");

		// kellonaika
		int min = LocalDateTime.now().getMinute();
		int h = LocalDateTime.now().getHour();
		int secs = LocalDateTime.now().getSecond();

		String sMin;
		String sH;
		String sSecs;

		if (min < 10) {
			sMin = "0" + LocalDateTime.now().getMinute();
		} else {
			sMin = LocalDateTime.now().getMinute() + "";
		}

		if (h < 10) {
			sH = "0" + LocalDateTime.now().getHour();
		} else {
			sH = LocalDateTime.now().getHour() + "";
		}
		if (secs < 10) {
			sSecs = "0" + LocalDateTime.now().getSecond();
		} else {
			sSecs = LocalDateTime.now().getSecond() + "";
		}

		String sldtTime = sH + ":" + sMin + ":" + sSecs;
		System.out.println(sldtTime);

		// pvm
		String yyyyString = LocalDateTime.now().getYear() + "-";
		int dd = LocalDateTime.now().getDayOfMonth();
		int mm = LocalDateTime.now().getMonthValue();
		String ddString;
		String mmString;

		if (dd < 10) {
			ddString = "0" + LocalDateTime.now().getDayOfMonth() + "";
		} else {
			ddString = LocalDateTime.now().getDayOfMonth() + "";
		}

		if (mm < 10) {
			mmString = "0" + LocalDateTime.now().getMonthValue() + "-";
		} else {
			mmString = LocalDateTime.now().getMonthValue() + "-";
		}
		String sldtDate = yyyyString + mmString + ddString;
		System.out.println(sldtDate);

		if (transactionOk) {
			System.out.println("TRANSACTION TRUE");
			setTransactionDate(sldtDate); // Myyntitapahtuma-aika merkitään vasta kun myyntitapahtuma ok
			setTransactionTime(sldtTime);
			// setTimestamp("Last time edited: " + sldtDate + " " + sldtTime);

		} else if (!transactionOk) {
			System.out.println("TRANSACTION FALSE");
			setTransactionDate(sldtDate);
			setTransactionTime(sldtTime);
			// setTimestamp("Last time edited: " + sldtDate + " " + sldtTime);

		}

	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		// this.transactionDate = LocalDate.now();
		this.transactionDate = transactionDate;
		System.out.println("setTransactionDate: " + transactionDate + "-" + isTransactionOk());

	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		// this.transactionTime = LocalDateTime.now();
		this.transactionTime = transactionTime;
		System.out.println("setTransactionTime: " + transactionDate + "-" + isTransactionOk());

	}

	/*
	 * public String getTimestamp() { return timestamp; }
	 * 
	 * public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
	 */

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", transactionOk=" + transactionOk
				+ ", transactionDate=" + transactionDate + ", transactionTime=" + transactionTime + ", tickets="
				+ tickets + "]";
	}

	/*
	 * @Override public String toString() { return "Transaction [transactionId=" +
	 * transactionId + ", amount=" + amount + ", transactionOk=" + transactionOk +
	 * ", transactionDate=" + transactionDate + ", transactionTime=" +
	 * transactionTime + ", timestamp=" + timestamp + ", tickets=" + tickets + "]";
	 * }
	 */

	/*
	 * @Override public String toString() { return "Transaction [transactionId=" +
	 * transactionId + ", amount=" + amount + ", transactionOk=" + transactionOk +
	 * ", transactionDate=" + transactionDate + ", transactionTime=" +
	 * transactionTime + ", tickets=" + tickets + "]"; }
	 */

}
