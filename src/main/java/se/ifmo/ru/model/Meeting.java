package se.ifmo.ru.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEETINGS")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "place_id", nullable = false)
    private Long placeId;

    @Column(unique = true)
    private Date date;

    @Column
    private double cost;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getClass().equals(obj.getClass()) && this.id.equals(((Meeting) obj).id));
    }
}
