package se.ifmo.ru.model;

import se.ifmo.ru.service.DuckService;
import se.ifmo.ru.service.RequestService;
import se.ifmo.ru.service.UserService;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private char gender = '-';

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String country;

    @Column
    private String city;

    @Column                  // with street/avenue/square stuff to use search in maps
    private String street;

    @Column
    private int building;

    @Column(name = "building_letter")
    private char buildingLetter = '-';

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Duck> ducks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "EVENT_PARTICIPATION",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> attendingEvents = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "EVENT_ORGANIZER",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> organizedEvents = new ArrayList<>();

    public User() {}

    public User(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public char getBuildingLetter() {
        return buildingLetter;
    }

    public void setBuildingLetter(char buildingLetter) {
        this.buildingLetter = buildingLetter;
    }

    public List<Duck> getDucks() {
        return ducks;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<Event> getAttendingEvents() {
        return attendingEvents;
    }

    public List<Event> getOrganizedEvents() {
        return organizedEvents;
    }

    //TODO: test
    public void addRequest(Request request, Duck duck) {
        UserService userService = new UserService();
        DuckService duckService = new DuckService();
        RequestService requestService = new RequestService();
        User user = userService.getByIdWithDucksAndRequests(this.id);
        duck = duckService.getById(duck.getId());
        user.getRequests().add(request);
        duck.getRequests().add(request);
        requestService.save(request);
    }

    public void addAttendingEvent(Event event) {
        UserService userService = new UserService();
        this.attendingEvents.add(event);
        event.getParticipants().add(this);
        userService.update(this);
    }

    public void addOrganizedEvent(Event event) {
        UserService userService = new UserService();
        this.organizedEvents.add(event);
        event.getOrganizers().add(this);
        userService.update(this);
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getClass().equals(obj.getClass()) && this.id.equals(((User) obj).id));
    }
}
