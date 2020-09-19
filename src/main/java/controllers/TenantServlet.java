package controllers;

import Ejb.TenantBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Tenant;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("tenants")
public class TenantServlet extends HttpServlet {

    @EJB
    private TenantBean tenantBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String errorMsg = "";
        boolean success = true;

        Tenant tenant = new Tenant();
        try {
            BeanUtils.populate(tenant, request.getParameterMap());
            tenantBean.save(tenant);

        }catch (Exception ex){
            ex.printStackTrace();
            errorMsg = ex.getMessage();
            success = false;
        }


        if (success) {
            response.sendRedirect("tenant");

        }else {
            RequestDispatcher dispatcher;
            request.setAttribute("errorMsg", errorMsg);
            dispatcher = request.getRequestDispatcher("admin/add_form.jsp");
            dispatcher.forward(request, response);
        }


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try{
            List<Tenant> tenants = tenantBean.list();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tenants);
            response.getWriter().println(json);
        } catch(Exception e) {
            //e.printStackTrace();
            response.getWriter().println(e.getMessage());

        }

    }
}
