package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.HibernateHelper;
import models.Tenant;
import org.hibernate.Session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
///@WebServlet(urlPatterns = {"/tenants_details"})
public class TenantServelet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = HibernateHelper.getSessionFactory().openSession();
        List<Tenant> tenants = session.createQuery("FROM Tenant s").getResultList();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tenants);
            response.getWriter().println(json);
        } catch(Exception e) {
            //e.printStackTrace();
            response.getWriter().println(e.getMessage());

        }
    }

//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Session session = HibernateHelper.getSessionFactory().openSession();
//        Transaction tx = session.getTransaction();
//        try {
//            tx.begin();
//            Tenant tenant = new Tenant();
//
//
//
//
//            String name = request.getParameter("name");
//            String houseno = request.getParameter("houseno");
//            String rent = request.getParameter("rent");
//
//            tenant.setPerson(new Person());
//            tenant.setContact(new Contact());
//            tenant.getPerson().setName(name);
//            tenant.setHouseNo(houseno);
//            tenant.setRentPaid(rent);
//
//            session.save(tenant);
//            tx.commit();
//            response.getWriter().println("Your payments details has been received please chech you email for verification");
//
//        } catch (Exception e) {
//            // TODO: handle exception properly
//            response.getWriter().println("An error has occurred..Try again");
//            e.printStackTrace();
//        }
//
//    }
}

