package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tenants")
@DynamicInsert
@DynamicUpdate
public class Tenant extends BaseEntity{

    @Embedded
    private Person person;

    @Embedded
    private Contact contact;

    @Column(name = "house_no")
    private  String houseNo;

    @Column(name = "rent_paid")
    private  String rentPaid;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="plot_id", insertable = false, updatable = false)
    @JsonBackReference
    private Plot plot;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="house_id", insertable = false, updatable = false)
    @JsonBackReference
    private House house;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
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

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getRentPaid() {
        return rentPaid;
    }

    public void setRentPaid(String rentPaid) {
        this.rentPaid = rentPaid;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }
}
