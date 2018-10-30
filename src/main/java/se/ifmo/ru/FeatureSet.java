package se.ifmo.ru;
many
import javax.persistence.*;

@Entity
@Table(name="FEATURE_SET")
public class FeatureSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
