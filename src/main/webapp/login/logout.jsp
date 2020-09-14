<% 
session.invalidate(); //destroy session
response.sendRedirect("login.jsp"); //logout successfully and redirect to "login.jsp" page
%>


