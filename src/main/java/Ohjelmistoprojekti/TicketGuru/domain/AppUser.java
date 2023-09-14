package Ohjelmistoprojekti.TicketGuru.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "AppUsers")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appUserId", nullable = false, updatable = false)
    private Long id;

    // Username with unique constraint
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role userRole;


    public AppUser() {
    }

    public AppUser(Long id, String username, String passwordHash, Role userRole) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
}
