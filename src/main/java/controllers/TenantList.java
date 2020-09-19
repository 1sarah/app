package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.HibernateHelper;
import models.Tenant;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/tenants_list"})
public class TenantList extends HttpServlet {
    /**
     * Get all users available
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = HibernateHelper.getSessionFactory().openSession();
        List<Tenant> tenants = session.createQuery("FROM Tenant s ").getResultList();
        ObjectMapper objectMapper = new ObjectMapper();

        String name = request.getParameter("name");
        System.out.println("==========================================");
        System.out.println(name);

        try{
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tenants);
            response.getWriter().println(json);
        } catch(Exception e) {
            //e.printStackTrace();
            response.getWriter().println(e.getMessage());

        }
    }

        public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String id = request.getParameter("id");
            Session session = HibernateHelper.getSessionFactory().openSession();
            Transaction tx = session.getTransaction();
            tx.begin();
            try {
            Tenant tenants = session.load(Tenant.class, Integer.parseInt(id));
            session.remove(tenants);
            tx.commit();


            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            json.put("deleted", true);

                String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
               response.getWriter().println(id);
            } catch (Exception e) {
                // TODO Handle exceptions
               response.getWriter().println(e.getMessage());

            } finally {
                session.close();
            }
        }
    }

