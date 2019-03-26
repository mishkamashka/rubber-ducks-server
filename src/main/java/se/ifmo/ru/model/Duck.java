package se.ifmo.ru.model;

import se.ifmo.ru.service.FeatureSetService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DUCKS")
public class Duck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_set_id", nullable = false, unique = true)
    private FeatureSet featureSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "duck", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();

    @Column(nullable = false, name="accessibility")
    private boolean isAccessible = false;

    public Duck() {}

    Duck(String name, FeatureSet featureSet, User owner) {
        this.name = name;
        this.featureSet = featureSet;
        this.owner = owner;
    }

    Duck(String name, FeatureSet featureSet, User owner, String description) {
        this.name = name;
        this.featureSet = featureSet;
        this.owner = owner;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public FeatureSet getFeatureSet() {
        return featureSet;
    }

    public User getOwner() {
        return this.owner;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessibility(boolean isAccessible) {
        this.isAccessible = isAccessible;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFeatureSet(FeatureSet featureSet) {
        this.featureSet = featureSet;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void deleteRequest(Request request) {
        for (int i = 0; i <= requests.size(); i++) {
            if (request.equals(requests.get(i))) {
                requests.remove(i);
                break;
            }
        }
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getClass().equals(obj.getClass()) && this.id.equals(((Duck) obj).id));
    }
}
