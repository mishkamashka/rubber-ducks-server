package se.ifmo.ru;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DUCK")
public class Duck {

    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "biography")
    private String biography;

    @Column(name = "feature_set_id")
    private long featureSetId;

    @Column(name = "owner_id")
    private long ownerId;
}
