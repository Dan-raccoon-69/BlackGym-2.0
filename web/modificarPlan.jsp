<%-- 
    Document   : modificarPlan
    Created on : 8 dic. 2023, 11:28:17
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="favicon.ico">
        <title>Modificar Plan</title>
        <link rel="stylesheet" href="Styles/admin.css"/>
        <link rel="stylesheet" href="Styles/modificarPlan.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" /> 
    </head>
    <body>
        <div class="masthead">
            <h3 class="text-muted">BlackGym</h3>
            <nav>
                <ul class="nav nav-justified">
                    <li><a href="PlanesController?action=inicio">Inicio</a></li>            
                    <li><a href="SociosController?action=verSocios">Socios</a></li>                        
                    <li><a href="PlanesController?action=verPlanes">Planes</a></li>            
                </ul>
            </nav>
        </div>

        <div class="main-content">
            <div class="custom-form-container">
                <form class="my-custom-form" method="post" action="PlanesController">
                    <input type="hidden" class="my-form-input" name="NumPlan" value="${plan.numPlan}">
                    <label for="Nom" class="my-form-label">Nombre:</label>
                    <input type="text" autocomplete="off" id="nombre" name="Nom" value="${plan.nom}" class="my-form-input" required>

                    <label for="P" class="my-form-label">Precio:</label>
                    <input type="text" autocomplete="off" id="precio" name="P" class="my-form-input" value="${plan.p}" required>

                    <button type="submit" class="my-form-button" name="action" value="modificar">Modificar Plan</button>
                </form>
            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2023 BlackGym, Daniel De La Cruz.</p>
        </footer>

    </body>
</html>
