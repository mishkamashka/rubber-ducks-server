package se.ifmo.ru.model;

import javax.persistence.*;

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
}
