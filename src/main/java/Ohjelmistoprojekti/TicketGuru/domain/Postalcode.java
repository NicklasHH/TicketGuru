package Ohjelmistoprojekti.TicketGuru.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Postalcode {
	
	@Id
	@Column(name="postalCode")
	private String postalCode;
	
	@Column(name="postOffice")
	private String postOffice;
	
	@OneToMany(mappedBy = "Venue")
    private List<Venue> venues;
	
	public Postalcode() {}

	public Postalcode(String postalCode, String postOffice) {
		super();
		this.postalCode = postalCode;
		this.postOffice = postOffice;
	}
	

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}
	
	
}
