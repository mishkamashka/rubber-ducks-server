package se.ifmo.ru.model;

import se.ifmo.ru.util.DateFormatter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "EVENTS")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column
    private Date date;

    @Column
    private double cost;

    /**
     * available for non-members or not
     */
    @Column
    private boolean isClubOnly = false;

    /**
     *  0 - no limit
     */
    @Column
    private int maxPeople = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EVENT_PARTICIPATION",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<User> participants = new ArrayList<>();

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isClubOnly() {
        return isClubOnly;
    }

    public void setClubOnly(boolean clubOnly) {
        isClubOnly = clubOnly;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    @Override
    public boolean equals(Object obj) {
        Event event = (Event) obj;
        return (this.getClass().equals(obj.getClass()) && this.id.equals(event.getId()));
    }
}
