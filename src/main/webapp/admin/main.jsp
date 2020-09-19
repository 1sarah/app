<!DOCTYPE html>

<html lang="en">
<script>
        $(function() {
            // Check if a user session exists. Set the links in the navbar accordingly.
            fetch('http://localhost:8080/my-app/login')
            .then(
                function(response) {
                    if (response.status !== 200) {
                        console.log('Looks like there was a problem. Status Code: ' + response.status);
                        return;
                    }
                    response.json().then(data => {
                        if(data.name != null) {
                            let name = data.name.toUpperCase();
                            let role = data.role.toUpperCase();
                            console.log('A user is logged in');
                            $('#auth-links').append(`<a href="#" id="user-link"> <i class="fa fa-user" aria-hidden="true"></i> ${name} (${role})</a>`);
                        }
                        else {
                            console.log('No user is logged in');
                        }
                    });
                }
            )
            .catch(err => console.log('Fetch Error :-S', err));
        });
    </script>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />

    <title>RealEstate | Dashboard</title>

    <jsp:include page="./main/styles.jsp" />

    <style>
      .wrapper {
        text-align: center;
      }
    </style>
  </head>

  <body class="hold-transition sidebar-mini">
    <div class="wrapper">
      <!-- Navbar -->
      <jsp:include page="./main/top_navs.jsp" />
      <!-- /.navbar -->

      <!-- Main Sidebar Container -->
      <jsp:include page="./main/side_navs1.jsp" />
      <!-- /.Main Sidebar Container -->

      <main class="main">
        <div class="main-header">
          <div class="main-header__heading">
          <%
          String username=(String)session.getAttribute("user");
          out.print("Hello " + username);
          %>
          </div>
          <div class="main-header__updates">
            <!--                <div class="box">-->
            <!--                  <a class="button" href="#popup1">ADD</a>-->
            <!--                </div>-->
</div>
</div>
        <div class="main-overview">
          <div class="overviewcard">
            <table id="dashboard">
              <thead>
                                  <tr>
                                      <th>Name</th>
                                      <th>Phone_No</th>
                                      <th>Gender</th>

                                  </tr>
                                  </thead>
                                  <tbody>
                                  <tr>

                                  </tr>
              </tbody>
            </table>
          </div>
        </div>
      </main>
      <!-- /.content-wrapper -->

      <!-- Control Sidebar -->
      <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
      </aside>
      <!-- /.control-sidebar -->

      <!-- Main Footer -->
      <jsp:include page="./main/footer.jsp" />
    </div>
    <!-- ./wrapper -->

    <!-- REQUIRED SCRIPTS -->
    <jsp:include page="./main/scripts.jsp" />
    <script>
      // Fetch all users
      fetch("http://localhost:8080/my-app/landlord_details", {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      })
        .then((response) => response.json())
        .then((data) => {
        //console.log(data)
          data.forEach((element) => {
            const table = document.querySelector("#dashboard");
            const name = element.person.name;
            const phoneNo = element.contact.phoneNo;
            const gender = element.person.gender;

            //console.log(element.person.name);
            const row = `
      <tr>
        <td>${name}</td>
        <td>${phoneNo}</td>
        <td>${gender}</td>
      </tr>
      `;
            table.insertAdjacentHTML("beforeend", row);
          });
        })
        .catch((error) => {
          console.log("There is an error");
        });





//Deleting an item from the database
                let deleteBtn = document.getElementById("del");
                  deleteBtn.addEventListener("click", function(e) {
                              e.preventDefault();
                              let xhr = new XMLHttpRequest();

                              xhr.open("delete", "/tenants_list?"+`id=${deleteBtn.dataset.id}`, false);
                              xhr.onload  = function() {
                                  if (xhr.status == 200) {
                                      alert(xhr.responseText);
                                  } else {
                                      alert("Error occurred " + xhr.status);
                                  }
                              }
                              xhr.send();

                      })

                      </script>
                      </body>
</html>
