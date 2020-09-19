<!DOCTYPE html>
<html>
<title>Print form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
    <form method="post" action="" id="myfrm">
        <fieldset>
            <legend>Personal information:</legend>
            First name:<br>
            <input type="text" name="firstname">
            <br> Last name:<br>
            <input type="text" name="lastname">
            <br><br>
            <input type="submit" value="Submit">
        </fieldset>
    </form><closeform></closeform>
    <p align="center"><input type="button" onclick="myPrint('myfrm')" value="print"></p>
    <script>
        function myPrint(myfrm) {
            var printdata = document.getElementById(myfrm);
            newwin = window.open("");
            newwin.document.write(printdata.outerHTML);
            newwin.print();
            newwin.close();
        }
    </script>
</body>
</html>