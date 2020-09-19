package controllers;

import database.HibernateHelper;
import models.Tenant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/tenants_edit"})
public class EditTenant extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String houseno = request.getParameter("houseno");
        String rent = request.getParameter("rent");
        String id = request.getParameter("id");
        //String Id_No = request.getParameter("id_no");

        try {
            Session session = HibernateHelper.getSessionFactory().openSession();
            Transaction tx = session.getTransaction();

            tx.begin();

            // query db by email to retrieve tenant
            //String hql = "update Tenant t where t.id=" +id;
            String hql="insert into Tenant t SET t.id = " +id;
            Query query = session.createQuery(hql);
            Tenant tenant = (Tenant) query.getSingleResult();

//            Tenant tenant = (Tenant) session
//                    .createQuery("SELECT t FROM Tenant t WHERE t.email =:email")
//                    .setParameter("email", email)
//                    .uniqueResult();

            // update values using setter methods
            tenant.getPerson().setName(name);
            tenant.setHouseNo(houseno);
            tenant.setRentPaid(rent);
            //tenant.getContact().setEmail(email);
            //tenant.getPerson().setIdNo(Id_No);

            session.save(tenant);
            tx.commit();
            response.getWriter().println("Tenant updated succesfully");
            response.sendRedirect("admin/index.jsp");

        } catch (Exception e) {
            // TODO: handle exception properly
            response.getWriter().println("An error has occurred..Try again");
            e.printStackTrace();
        }
    }
}
