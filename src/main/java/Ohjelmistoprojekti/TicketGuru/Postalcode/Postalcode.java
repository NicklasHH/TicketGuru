package Ohjelmistoprojekti.TicketGuru.Postalcode;

import java.util.List;

import Ohjelmistoprojekti.TicketGuru.Venue.Venue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Postalcodes")
public class Postalcode {
	@Id
	@Column(nullable = false)
	private String postalcode;

	@Column(nullable = false)
	private String postOffice;

	@JsonIgnore
	@OneToMany(mappedBy = "postalcode", fetch = FetchType.EAGER)
	private List<Venue> venues;

	public Postalcode() {
	}

	public Postalcode(String postalcode, String postOffice, List<Venue> venues) {
		super();
		this.postalcode = postalcode;
		this.postOffice = postOffice;
		this.venues = venues;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

	public List<Venue> getVenues() {
		return venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	@Override
	public String toString() {
		return "Postalcode [postalcode=" + postalcode + ", postOffice=" + postOffice + ", venues=" + venues + "]";
	}

}
