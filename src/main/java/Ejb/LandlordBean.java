package Ejb;

import models.Landlord;


import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class LandlordBean {

    @PersistenceContext
    private EntityManager em;

    public Landlord save(Landlord landlord) throws Exception{
        if (landlord == null || StringUtils.isBlank(landlord.getPerson().getName()))
            throw new Exception("Invalid department details");

        return em.merge(landlord);

    }

    public Landlord load(int landlordId) throws Exception{
        if (landlordId == 0)
            return new Landlord();

        Landlord tenant = em.find(Landlord.class, landlordId);

        if (tenant == null)
            return new Landlord();

        return tenant;

    }

    @SuppressWarnings({"unchecked"})
    public List<Landlord> list(Landlord filter){
        String hql = "SELECT d FROM Landlord d WHERE d.id is not null";

        if (filter != null){

            if (StringUtils.isNotBlank(filter.getPerson().getName()))
                hql += " AND d.name like '%" + StringUtils.trim(filter.getPerson().getName()) + "%'";

            if (StringUtils.isNotBlank(filter.getContact().getAddress()))
                hql += " AND d.location like '%" + StringUtils.trim(filter.getContact().getAddress()) + "%'";
        }

        return em.createQuery(hql).getResultList();
    }

    public void delete(int departmentId) throws Exception{
        if (departmentId == 0)
            throw new Exception("Invalid department Id..");

        em.remove(em.find(Landlord.class, departmentId));
    }

}

