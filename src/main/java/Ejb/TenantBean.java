package Ejb;

import models.Tenant;


import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class TenantBean {

    @PersistenceContext
    private EntityManager em;

    public Tenant save(Tenant tenant) throws Exception{
        if (tenant == null || StringUtils.isBlank(tenant.getPerson().getName()))
            throw new Exception("Invalid tenant details");

        return em.merge(tenant);

    }

    public Tenant load(int tenantId) throws Exception{
        if (tenantId == 0)
            return new Tenant();

       Tenant tenant = em.find(Tenant.class, tenantId);

        if (tenant == null)
            return new Tenant();

        return tenant;

    }

    @SuppressWarnings({"unchecked"})
    public List<Tenant> list() throws Exception {
        String hql = "SELECT s FROM Tenant s";

        List<Tenant> tenants = em.createQuery(hql).getResultList();

        if(tenants.isEmpty())
            throw new Exception("No tenant found");

        return tenants;
    }

    public void delete(int departmentId) throws Exception{
        if (departmentId == 0)
            throw new Exception("Invalid tenant Id..");

        em.remove(em.find(Tenant.class, departmentId));
    }

}

