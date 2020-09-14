package models;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plots")
@DynamicInsert
@DynamicUpdate
public class Plot extends BaseEntity{

    @Embedded
    private static Contact contact;

    @Column(name = "plot_id")
    private String plot_id;

    public String getPlot_id() {
        return plot_id;
    }

    public String getPlot_no() {
        return plot_no;
    }

    public void setPlot_id(String plot_id) {
        this.plot_id = plot_id;
    }

    @Column
    private String plot_no;

    @OneToMany(mappedBy = "plot",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tenant> tenants = new ArrayList<Tenant>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="landlord_id", insertable = false, updatable = false)
    private Landlord landlord;

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public static Contact getContact() {
        return contact;
    }

    public static void setContact(Contact contact) {
        Plot.contact = contact;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public String getPlot_no(String s) {
        return plot_no;
    }

    public void setPlot_no(String plot_no) {
        this.plot_no = plot_no;
    }
}
