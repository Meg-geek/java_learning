package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operationsList;

    public User() {
    }

    public User(String name) {
        this.name = name;
        operationsList = new ArrayList<>();
    }

    public void addOperation(Operation operation) {
        operation.setUser(this);
        operationsList.add(operation);
    }

    public void removeOperation(Operation operation) {
        operationsList.remove(operation);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Operation> getOperationsList() {
        return operationsList;
    }

    public void setOperationsList(List<Operation> operationsList) {
        this.operationsList = operationsList;
    }

    @Override
    public String toString() {
        return "models.User{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
