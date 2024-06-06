package org.example;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
})

@Table(name = "Buguri")

public class Buguri extends org.example.Entity<UUID> {
    @Column(name = "denumire", nullable = false,table = "Buguri")
    private String denumire;
    @Column(name = "descriere", nullable = false,table = "Buguri")
    private String descriere;
    @Column(name = "data", nullable = false,table = "Buguri")
    private String data;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;



    public Buguri() {
    }
    public Buguri(String denumire, String descriere, String data) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.data = data;
        this.setId(UUID.randomUUID());
    }



    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }


    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

}
