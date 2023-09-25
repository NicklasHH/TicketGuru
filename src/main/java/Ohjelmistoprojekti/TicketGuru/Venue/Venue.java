package Ohjelmistoprojekti.TicketGuru.Venue;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Ohjelmistoprojekti.TicketGuru.Postalcode.Postalcode;
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

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "postalcode")
	private Postalcode postalcode;

	public Venue() {
	}

	public Venue(String place, String streetAddress, Postalcode postalcode) {
		this.place = place;
		this.streetAddress = streetAddress;
		this.postalcode = postalcode;
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

	@Override
	public String toString() {
		return "Venue [venueId=" + venueId + ", place=" + place + ", streetAddress=" + streetAddress + ", postalcode="
				+ postalcode + "]";
	}

}
