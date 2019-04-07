package se.ifmo.ru.model;

import se.ifmo.ru.security.domain.Authority;
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

    @Column(name = "password")
    private String password;

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

    @Column
    private boolean active;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Duck> ducks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();

    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private List<Event> attendingEvents = new ArrayList<>();

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> organizedEvents = new ArrayList<>();

    public User() {
        this.active = false;
    }

    public User(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
        this.active = false;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setDucks(List<Duck> ducks) {
        this.ducks = ducks;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public void setOrganizedEvents(List<Event> organizedEvents) {
        this.organizedEvents = organizedEvents;
    }

    public void setAttendingEvents(List<Event> attendingEvents) {
        this.attendingEvents = attendingEvents;
    }

    public void deleteDuck(Duck duck) {
        for (int i = 0; i <= ducks.size(); i++) {
            if (duck.equals(ducks.get(i))) {
                ducks.remove(i);
                break;
            }
        }
    }

    public void deleteRequest(Request request) {
        for (int i = 0; i <= requests.size(); i++) {
            if (request.equals(requests.get(i))) {
                requests.remove(i);
                break;
            }
        }
    }

    public void deleteOrganizedEvent(Event event) {
        for (int i = 0; i <= organizedEvents.size(); i++) {
            if (event.equals(organizedEvents.get(i))) {
                organizedEvents.remove(i);
                break;
            }
        }
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

    @Override
    public boolean equals(Object obj) {
        return (this.getClass().equals(obj.getClass()) && this.id.equals(((User) obj).id));
    }
}
