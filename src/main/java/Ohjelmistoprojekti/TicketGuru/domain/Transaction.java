package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long transactionId;

    private double amount;
    private String transactionDate;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;

    public Transaction() {
    }

    public Transaction(double amount, String transactionDate, Ticket ticket) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.ticket = ticket;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
