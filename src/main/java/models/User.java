package models;

import javax.persistence.*;

//@NamedQueries({
//        @NamedQuery(name = "", query = "SELECT u FROM User u WHERE u.email=:email and u.password=:pwd")
//})
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;


    @Column
    private String password;


    @Column
    private String role;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
