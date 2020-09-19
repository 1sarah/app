package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.HibernateHelper;
import models.Tenant;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/tenant"})
public class OneTenant extends HttpServlet {
    /**
     * Get all users available
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = (String) request.getAttribute("username");
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        try {
            Tenant tenant = (Tenant) session.createQuery("FROM Tenant s where s.person.name=:value")
                    .setParameter( "value", name ).getSingleResult();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tenant);
            response.getWriter().println(json);
        } catch (Exception e) {
            // TODO Handle exceptions
            response.getWriter().println(e.getMessage());

        } finally {
            session.close();
        }
    }
}

