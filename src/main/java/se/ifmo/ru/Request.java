package se.ifmo.ru;

import javax.persistence.*;

@Entity
@Table(name="REQUEST")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int userId;

    @Column
    private int duckId;

    @Column
    private boolean isApproved = false;

}
