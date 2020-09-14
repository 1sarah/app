package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "houses")
@DynamicInsert
@DynamicUpdate
public class House extends BaseEntity{

    @Column
    private String property_id;

    @Column
    private String house_no;

    @Column
    private String size;

    @Column
    private String rent_amount;

    @OneToOne(mappedBy = "house",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    //private List<Tenant> tenants = new ArrayList<Tenant>();
    private Tenant tenant;

    public String getProperty_id(String s) {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getHouse_no(String s) {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getSize(String s) {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRent_amount(String s) {
        return rent_amount;
    }

    public void setRent_amount(String rent_amount) {
        this.rent_amount = rent_amount;
    }

//    public List<Tenant> getTenants() {
//        return tenants;
//    }
//
//    public void setTenants(List<Tenant> tenants) {
//        this.tenants = tenants;
//    }
//
//    public Tenant getTenant() {
//        return tenant;
//    }
//
//    public void setTenant(Tenant tenant) {
//        this.tenant = tenant;
//    }
}
