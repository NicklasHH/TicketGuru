package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.*;

@Entity
@Table(name= "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId", nullable = false, updatable = false)
    private Long roleId;

    @Column(name = "role", nullable = false)
    private String role;


    public Role() {
    }

    public Role(Long roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
