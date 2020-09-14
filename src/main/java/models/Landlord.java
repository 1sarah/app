package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "landlords")
public class Landlord extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
//    private int id;

    @Embedded
    private Person person;

    @Embedded
    private Contact contact;

    @Column(name = "landlord_id")
    private String landlord_id;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "landlord",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plot> plots = new ArrayList<Plot>();

    public String getLandlord_id() {
        return landlord_id;
    }

    public boolean setLandlord_id(String username, String landlord_id) {
        this.landlord_id = landlord_id;
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public List<Plot> getPlots() {
        return plots;
    }

    public void setPlots(List<Plot> plots) {
        this.plots = plots;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


}
