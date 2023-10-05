package Ohjelmistoprojekti.TicketGuru.Role;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Ohjelmistoprojekti.TicketGuru.AppUser.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false, updatable = false)
	private Long roleId;

	@Size(min = 1, max = 50)
	@NotNull
	@Column(name = "role_name", nullable = false)
	private String roleName;

	@JsonIgnore
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private List<AppUser> appUser;

	public Role() {
	}

	public Role(@Size(min = 1, max = 50) String roleName, List<AppUser> appUser) {
		super();
		this.roleName = roleName;
		this.appUser = appUser;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<AppUser> getAppUser() {
		return appUser;
	}

	public void setAppUsers(List<AppUser> appUser) {
		this.appUser = appUser;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", appUser=" + appUser + "]";
	}

}
