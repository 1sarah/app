package controllers;

import database.HibernateHelper;
import models.Landlord;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/ogin"})
public class LoginServlet extends HttpServlet {



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Landlord user = null;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            String username = request.getParameter("name");
            String password = request.getParameter("password");
            try {

                if (username != null && password != null) {
                    session.setAttribute("us", username);
                    session.setAttribute("ps", password);

                    String thename = (String) request.getSession(true).getAttribute("us");
                    String thepassword = (String) request.getSession(true).getAttribute("ps");

                    Session sessions = HibernateHelper.getSessionFactory().openSession();
                    user = (Landlord) sessions.createQuery("FROM Landlord L WHERE L.person.name = :userName").setParameter("userName", thename)
                            .uniqueResult();
                    if (user != null && user.getPassword().equals(thepassword)) {
                        response.sendRedirect("admin/main.jsp");

                    } else {
//                        response.sendRedirect("admin/login.jsp");
                        System.out.println("you");
                    }


                }
            } catch (Exception ex) {
                out.println("Exception :" + ex.getMessage());
            }
        }
    }
}
