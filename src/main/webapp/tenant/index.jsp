<!DOCTYPE html>
<html lang="en">
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
      <jsp:include page="./main/side_navs.jsp" />
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
           <button onclick="myPrint('myfrm')" value="print"><a href="#ex" rel="modal:close">PRINT</a></button>
            <div id="ex" class="modal">
                <form method="post" action="" id="myfrm">
                    <fieldset>
                    						<div class="logo">
                    							<a href="#"><img src="images/logo.jpg" alt=""></a>
                    						</div>
                        <legend>Personal information:</legend>
                        Name:<br> <p>Dave</p>
                        <br>
                                                             <p data-column="Tenant_Id">1</p><br>
                                                             <td data-column="Name">1</td>
                                                             <td data-column="House_No">Ziwani</td>
                                                             <td data-column="Rent_Paid">67890</td>
                                                             <td data-column="Date">4500</td>
                        <input type="text" name="lastname">
                        <br><br>
                    </fieldset>
                </form><closeform></closeform>
                </div>
                <script>
                    function myPrint(myfrm) {
                        var printdata = document.getElementById(myfrm);
                        newwin = window.open("");
                        newwin.document.write(printdata.outerHTML);
                        newwin.print();
                        newwin.close();
                    }
                </script>
</div>
</div>
        <div class="main-overview">
          <div class="overviewcard">
            <table id="dashboard">
              <thead>
                                  <tr>
                                      <th>Name</th>
                                      <th>House_No</th>
                                      <th>Rent_Paid</th>
                                      <th>Date</th>
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
      fetch("http://localhost:8080/my-app/tenants_list",{
        method: "GET",
        headers: { "Content-Type": "application/json" },
      })
        .then((response) => response.json())
        .then((data) => {
        console.log(data)
          data.forEach((element) => {
            const table = document.querySelector("#dashboard");
            const name = element.person.name;
            const houseNo = element.houseNo;
            const rentPaid = element.rentPaid;
            const date = element.timeRecorded;
            //console.log(name,houseNo,rentPaid,date);
            //console.log(element.person.name);
            const row = `
      <tr>
        <td>${name}</td>
        <td>${houseNo}</td>
        <td>${rentPaid}</td>
        <td>${date}</td>
      </tr>
      `;
            table.insertAdjacentHTML("beforeend", row);
          });
        })
        .catch((error) => {
          console.log(error.message);
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
