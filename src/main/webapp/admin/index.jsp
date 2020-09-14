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
      <jsp:include page="./main/side_navs1.jsp" />
      <!-- /.Main Sidebar Container -->

      <main class="main">
        <div class="main-header">
          <div class="main-header__heading">Hello Username</div>
          <div class="main-header__updates">
            <!--                <div class="box">-->
            <!--                  <a class="button" href="#popup1">ADD</a>-->
            <!--                </div>-->
            <button><a href="#ex1" rel="modal:open">ADD-TENANT</a></button>
          </div>
        </div>
        <div id="ex1" class="modal">
          <h1 style="text-align: center">Add Tenant</h1>
          <div class="content" >
            <form action="<%=request.getContextPath()%>/tenants_add" method="POST">
              <input placeholder="Name" name="name" type="text" required />
              <input placeholder="House_No" name="houseno" type="text" required />
              <input placeholder="Rent_Paid" name="rent" type="prize" required />
              <input placeholder="Date" type="size" name="date" required />
              <div class="wrapper">
                <button class="button">SUBMIT</button>
              </div>
            </form>
          </div>
        </div>
        <div class="main-overview">
          <div class="overviewcard">
            <table id="dashboard">
              <thead>
                <tr>
                  <th>Tenant_Id</th>
                  <th>Name</th>
                  <th>House_No</th>
                  <th>Rent_Paid</th>
                  <th>Date</th>
                  <th>Edit</th>
                  <th>Delete</th>
                </tr>
              </thead>

              <tbody class="rent-table">
                <tr>
                  <td data-column="Tenant_Id">1</td>
                  <td data-column="Name">1</td>
                  <td data-column="House_No">Ziwani</td>
                  <td data-column="Rent_Paid">67890</td>
                  <td data-column="Date">4500</td>
                  <td><a href="">Edit</a></td>
                  <td><a href="" id="delete">Delete</a></td>
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

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script>
      // Fetch all users
      fetch("http://localhost:8080/my-app/tenants_list", {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      })
        .then((response) => response.json())
        .then((data) => {
          //console.log(data)
          data.forEach((element) => {
            const table = document.querySelector("#dashboard");
            const tenantId = element.id;
            const name = element.person.name;
            const houseNo = element.houseNo;
            const rentPaid = element.rentPaid;
            const date = element.timeRecorded;
            console.log(tenantId, name, houseNo, rentPaid, date);
            //console.log(element.person.name);
            const row = `
            <tr>
              <td>${tenantId}</td>
              <td>${name}</td>
              <td>${houseNo}</td>
              <td>${rentPaid}</td>
              <td>${date}</td>
              <td>
              <a href>Edit</a>
              </td>
              <td>
              <button class="delbtn" onclick="delItems(this)" data-id="${tenantId}">Delete</button>
              </td>
            </tr>
            `;
            table.insertAdjacentHTML("beforeend", row);
          });
        })
        .catch((error) => {
          console.log("There is an error");
        });

      //Deleting an item from the database
       function delItems(e) {
        const deleteBtn = document.querySelector(".delbtn");
        console.log(e);
        let xhr = new XMLHttpRequest();

        xhr.open(
          "delete",
          "/my-app/tenants_list?" + `id=${deleteBtn.dataset.id}`,
          false
        );
        xhr.onload = function () {
          if (xhr.status == 200) {
            alert(xhr.responseText);
          } else {
            alert("Error occurred " + xhr.status);
          }
        };
        xhr.send();
      };
    </script>
  </body>
</html>
