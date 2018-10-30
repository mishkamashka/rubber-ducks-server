package se.ifmo.ru;

import javax.persistence.*;

@Entity
@Table(name="REQUEST")
public class Request {      //TODO: user-request one-many; duck-request one-many

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long duckId;

    @Column
    private boolean isApproved = false;

    Request(Long userId, Long duckId){
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
