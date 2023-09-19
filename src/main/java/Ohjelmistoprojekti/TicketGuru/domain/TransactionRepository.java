package Ohjelmistoprojekti.TicketGuru.domain;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long > {

}
