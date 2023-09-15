package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Table(name = "Venues")
@Entity
public class Venue {
	
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
	private String postalCode;
	/*
	@ManyToOne
    @JoinColumn(name = "postalCode")
	private PostalCode postalCode; 
	*/
	
	@Column(name = "cityId")
	private String city;	
	/*
	@ManyToOne
    @JoinColumn(name = "city")
	private City city; */
	
	public Venue() {}
	
	

	public Venue(@Size(min = 1, max = 150) String place, @Size(min = 1, max = 150) String streetAddress,
			String postalCode, String city) {
		this.place = place;
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.city = city;
	}



	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
		this.venueId = venueId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



	@Override
	public String toString() {
		return "Venue [venueId=" + venueId + ", place=" + place + ", streetAddress=" + streetAddress + ", postalCode="
				+ postalCode + ", city=" + city + "]";
	}
	
	
	
	


	
	
	
	
	
	
	


}
