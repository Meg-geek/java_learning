package models;

import javax.persistence.*;

@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Operation(){}

    public Operation(double sum){
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public double getSum() {
        return sum;
    }

    public User getUser() {
        return user;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
