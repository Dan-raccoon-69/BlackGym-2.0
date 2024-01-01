<%-- 
    Document   : admin
    Created on : 1 dic. 2023, 14:37:15
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="Styles/admin.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" />
    </head>
    <body>
        <div class="container">

            <div class="masthead">
                <h2 class="text-muted">BlackGym Administración</h2>
                <nav>
                    <ul class="nav nav-justified">
                        <li><a href="AdminController?action=inicio">Inicio</a></li>            
                        <li><a href="SociosController?action=verSocios">Socios</a></li>                           
                        <li><a href="AdminController?action=logout">Salir</a></li>            
                    </ul>
                </nav>
            </div>
            <br>

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="panel-title">Bienvenido </h2>
                </div>
                <div class="panel-body">
                    <h2 class="panel-title"><b>Nombre: </b> <span style="color: #88b4db; font-style: italic;">${u1.getNom()}</span><br><br></h2>
                    <h2 class="panel-title"><b>Apellido Paterno: </b> <span style="color: #88b4db; font-style: italic;">${u1.getApePa()}</span> <br><br></h2>
                    <h2 class="panel-title"><b>Email: </b> <span style="color: #88b4db; text-decoration: underline; font-style: italic;">${u1.getCorr()}</span> <br><br></h2>
                    <h2 class="panel-title"><b>Nombre de Usuario: </b> <span style="color: #88b4db; font-style: italic;">${u1.getClav()}</span> <br><br></h2>

                </div>
            </div>


            <!-- Site footer -->
            <footer class="footer">
                <p>&copy; 2023 BlackGym, The Godfathers.</p>
            </footer>

        </div> <!-- /container -->
    </body>
</html>