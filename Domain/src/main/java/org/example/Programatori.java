package org.example;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.UUID;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
})

@Table(name = "Programatori")
public class Programatori extends org.example.Entity<UUID> implements Serializable {

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public Programatori() {
    }

    public Programatori(String username, String password) {
        this.username = username;
        this.password = password;
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


    public UUID getId() {
        return id;
    }
}
