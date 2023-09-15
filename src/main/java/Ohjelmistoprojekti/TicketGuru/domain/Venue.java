package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

public class Venue {
	//venueId(pk) place(char 150) streetAddress(char 150), postalCode(fk, char 5), cityId(fk)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name =  "venueId")
	private long venueId;
	
	@Column(name = "place")
	@Size(min = 1, max = 150)
	private String place;
	
	@Column(name = "streetAddress")
	@Size(min = 1, max = 150)
	private String streetAddress;
	
	@Column(name = "postalCode")
	@ManyToOne
    @JoinColumn(name = "postalCode")
	private String postalCode;
	
	@Column(name = "cityId")
	@ManyToOne
    @JoinColumn(name = "cityId")
	private int cityId; 
	
	/*  //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;  */

}
