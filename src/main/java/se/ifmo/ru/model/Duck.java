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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_set_id", nullable = false)
    private FeatureSet featureSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFeatureSet(FeatureSet featureSetId) {
        this.featureSet = featureSetId;
    }
}
