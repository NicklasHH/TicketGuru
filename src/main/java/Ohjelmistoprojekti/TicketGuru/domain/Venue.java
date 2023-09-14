package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Venue {
	//venueId(pk) place(char 150) streetAddress(char 150), postalCode(fk, char 5), cityId(fk)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long venueId;
	
	private String place, streetAddress, postalCode;
	
	private int cityId; 
	
	/*  //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;  */

}
