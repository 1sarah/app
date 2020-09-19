<!-- Navbar -->
<div class="grid-container">
    <header class="header"><div class="header__search">Welcome...</div>
        <div class="header__avatar">
        <%
                  String username=(String)session.getAttribute("user");
                  out.print("Hello " + username);
                  %>
        </div></header>
  <!-- /.navbar -->