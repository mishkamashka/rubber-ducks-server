package se.ifmo.ru;

import javax.persistence.*;

@Entity
@Table(name = "DUCK")
public class Duck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "feature_set_id", nullable = false)
    private Long featureSetId;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getFeatureSetId() {
        return featureSetId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFeatureSetId(long featureSetId) {
        this.featureSetId = featureSetId;
    }
}
