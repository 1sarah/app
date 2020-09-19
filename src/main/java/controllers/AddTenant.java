package controllers;

import database.HibernateHelper;
import models.Contact;
import models.Person;
import models.Tenant;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/tenants_add"})
public class AddTenant extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Tenant tenant = new Tenant();


            String name = request.getParameter("name");
            String houseno = request.getParameter("houseno");
            String rent = request.getParameter("rent");
            String email = request.getParameter("email");
            String Id_No = request.getParameter("id_no");

            tenant.setPerson(new Person());
            tenant.setContact(new Contact());
            tenant.getPerson().setName(name);
            tenant.setHouseNo(houseno);
            tenant.setRentPaid(rent);
            tenant.getContact().setEmail(email);
            tenant.getPerson().setIdNo(Id_No);

            session.save(tenant);
            tx.commit();
            response.getWriter().println("Tenant added succesfully");
            response.sendRedirect("admin/index.jsp");

        } catch (Exception e) {
            // TODO: handle exception properly
            response.getWriter().println("An error has occurred..Try again");
            e.printStackTrace();
        }
    }
}
