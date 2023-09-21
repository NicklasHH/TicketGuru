package Ohjelmistoprojekti.TicketGuru.Role;

import java.util.ArrayList;
import java.util.List;

import Ohjelmistoprojekti.TicketGuru.AppUser.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long roleId;

	@Size(min = 1, max = 50)
	@Column(nullable = false)
	private String roleName;

	@OneToMany(mappedBy = "role")
	private List<AppUser> users = new ArrayList<>();

	public Role() {
	}

	public Role(String roleName, List<AppUser> users) {
		this.roleName = roleName;
		this.users = users;
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

	public List<AppUser> getUsers() {
		return users;
	}

	public void setUsers(List<AppUser> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", users=" + users + "]";
	}

}
