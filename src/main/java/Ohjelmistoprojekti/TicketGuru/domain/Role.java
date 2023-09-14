package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId", nullable = false, updatable = false)
    private Long roleId;

    @Column(name = "roleName", nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "userRole")
    private List<AppUser> users;


    public Role() {
    }

    public Role(Long roleId, String role) {
        this.roleId = roleId;
        this.roleName = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return roleName;
    }

    public void setRole(String role) {
        this.roleName = role;
    }
}
