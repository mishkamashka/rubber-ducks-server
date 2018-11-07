package se.ifmo.ru.model;

import se.ifmo.ru.util.DateFormatter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENTS")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(unique = true)
    private Date date;

    @Column
    private double cost;

    public Event() {
    }

    public Event(String name) {
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String dateString) {
        try {
            this.date = DateFormatter.stringToDate(dateString);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Not valid date");
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
