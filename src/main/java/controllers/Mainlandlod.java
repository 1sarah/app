package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.HibernateHelper;
import org.hibernate.Session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/landlord_details"})
public class Mainlandlod extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = HibernateHelper.getSessionFactory().openSession();
        List<models.Landlord> landlord = session.createQuery("FROM Landlord s").getResultList();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(landlord);
            response.getWriter().println(json);
        } catch(Exception e) {
            //e.printStackTrace();
            response.getWriter().println(e.getMessage());

        }
    }
}
