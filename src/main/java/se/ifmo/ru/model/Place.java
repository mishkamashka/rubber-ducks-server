package se.ifmo.ru.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLACES")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private String city;

    @Column                  // with street/avenue/square stuff to use search in maps
    private String street;

    @Column
    private int buiding;

    @Column(name = "building_letter")
    private char buildingLetter = '-';

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>();

    public Place() {
    }

    public Place(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuiding() {
        return buiding;
    }

    public void setBuiding(int buiding) {
        this.buiding = buiding;
    }

    public char getBuildingLetter() {
        return buildingLetter;
    }

    public void setBuildingLetter(char buildingLetter) {
        this.buildingLetter = buildingLetter;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getClass().equals(obj.getClass()) && this.id.equals(((Place) obj).id));
    }
}
