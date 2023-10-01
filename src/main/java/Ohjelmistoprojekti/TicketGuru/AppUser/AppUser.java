package Ohjelmistoprojekti.TicketGuru.AppUser;

import Ohjelmistoprojekti.TicketGuru.Role.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "App_users")
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long appUserId;

	@Size(min = 1, max = 25)
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Size(min = 1, max = 50)
	@Column(name = "password", nullable = false)
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	public AppUser() {
	}

	public AppUser(@Size(min = 1, max = 25) String username, @Size(min = 1, max = 50) String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AppUser [appUserId=" + appUserId + ", username=" + username + ", password=" + password + ", role="
				+ role + "]";
	}

}
