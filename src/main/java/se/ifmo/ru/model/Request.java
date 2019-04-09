package se.ifmo.ru.model;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
@Table(name="REQUESTS")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="duck_id", nullable = false)
    private Duck duck;

    @Column(name = "is_approved")
    private boolean isApproved = false;

    public Request() {
    }

    public Request(User user, Duck duck){
        this.duck = duck;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Duck getDuck() {
        return duck;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDuck(Duck duck) {
        this.duck = duck;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getClass().equals(obj.getClass()) && this.id.equals(((Request) obj).id));
    }
}
