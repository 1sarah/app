package controllers;

import database.HibernateHelper;
import org.hibernate.Session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/create"})
public class CreateTables extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = HibernateHelper.getSessionFactory().openSession();
//        Tenant tenant = new  Tenant();
//        tenant.setPerson(new Person());
//        tenant.setContact(new Contact());
//        tenant.setHouseNo("123");
//        tenant.setRentPaid("50000");
//        tenant.getPerson().setName("Mbuya");
//        tenant.getPerson().setGender(Gender.FEMALE);
//        tenant.getContact().setEmail("eugenias@mail.com");
//        tenant.getPerson().setIdNo("423425");
//        tenant.getContact().setAddress("dwfaf");
//        tenant.getContact().setCountry("Kinya");
//        tenant.getContact().setPhoneNo("4543363");
//        tenant.getContact().setTown("fhhgwfef");
//        tenant.getPerson().setIdNo("22425");
//
//        session.save(tenant);
    }
}
