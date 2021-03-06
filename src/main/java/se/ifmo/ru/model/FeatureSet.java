package se.ifmo.ru.model;

import javax.persistence.*;

@Entity
@Table(name="FEATURE_SETS")
public class FeatureSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private char gender = '-';

    @Column
    private String colour;

    @Column(name = "beak_colour")
    private String beakColour;

    @Column
    private double length;

    @Column
    private double weight;

    @Column(name="swimming_skill")
    private byte swimmingSkill;

    public FeatureSet() {
    }

    public Long getId() {
        return id;
    }

    public char getGender() {
        return gender;
    }

    public String getColour() {
        return colour;
    }

    public String getBeakColour() {
        return beakColour;
    }

    public double getLength() {
        return length;
    }

    public double getWeight() {
        return weight;
    }

    public byte getSwimmingSkill() {
        return swimmingSkill;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setBeakColour(String beakColour) {
        this.beakColour = beakColour;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setSwimmingSkill(byte swimmingSkill) {
        this.swimmingSkill = swimmingSkill;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getClass().equals(obj.getClass()) && this.id.equals(((FeatureSet) obj).id));
    }
}
