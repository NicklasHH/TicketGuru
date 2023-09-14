package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
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
    private List<AppUser> users = new ArrayList<>();


    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
