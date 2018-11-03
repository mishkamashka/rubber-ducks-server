package se.ifmo.ru.model;

import javax.persistence.*;

@Entity
@Table(name="REQUESTS")
public class Request {      //TODO: user-request one-many; duck-request one-many

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long duckId;

    @Column(name = "is_approved")
    private boolean isApproved = false;

    public Request() {
    }

    public Request(Long userId, Long duckId){
        this.duckId = duckId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getDuckId() {
        return duckId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
