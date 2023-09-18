package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Venues")
public class Venue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private long venueId;

	@Size(min = 1, max = 150)
	@Column(nullable = false)
	private String place;

	@Size(min = 1, max = 150)
	@Column(nullable = false)
	private String streetAddress;

	@ManyToOne
	@JoinColumn(name = "postalcode")
	private Postalcode postalcode;

	@ManyToOne
	@JoinColumn(name = "cityId")
	private City city;

	public Venue() {
	}

	public Venue(String place, String streetAddress, Postalcode postalcode, City city) {
		this.place = place;
		this.streetAddress = streetAddress;
		this.postalcode = postalcode;
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

	public Postalcode getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(Postalcode postalcode) {
		this.postalcode = postalcode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Venue [venueId=" + venueId + ", place=" + place + ", streetAddress=" + streetAddress + ", postalcode="
				+ postalcode + ", city=" + city + "]";
	}

}
