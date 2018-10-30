package se.ifmo.ru;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private char gender;

    @Column
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
    private int buiding;

    @Column(name = "building_letter")
    private char buildingLetter;
}
