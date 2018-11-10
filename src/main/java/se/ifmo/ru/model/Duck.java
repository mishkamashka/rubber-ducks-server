package se.ifmo.ru.model;

import javax.persistence.*;

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
    @JoinColumn(name = "feature_set_id", nullable = false)
    private FeatureSet featureSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

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
        return owner;
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

    @Override
    public boolean equals(Object obj) {
        return (this.getClass().equals(obj.getClass()) && this.id.equals(((Duck) obj).id));
    }
}
