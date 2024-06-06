package org.example;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
})

@Table(name = "Testeri")
public class Tester extends org.example.Entity<UUID> {
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    public Tester(String username, String password) {
        this.username = username;
        this.password = password;
        this.setId(UUID.randomUUID());
    }

    public Tester() {
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

    public void setId(UUID id) {
        this.id = id;
    }

    @Id
    public UUID getId() {
        return id;
    }
}
