<%-- 
    Document   : agregarVentas
    Created on : 1 ene. 2024, 15:51:45
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Agregar Venta</title>
        <link rel="stylesheet" href="Styles/admin.css"/>
        <link rel="stylesheet" href="Styles/modificarSocios.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" /> 

    </head>
    <body>
        <div class="masthead">
            <h3 class="text-muted">BlackGym Venta</h3>
            <!--  
            <nav>
                <ul class="nav nav-justified">
                    <li><a href="PlanesController?action=inicio">Inicio</a></li>            
                    <li><a href="SociosController?action=verSocios">Socios</a></li>                        
                    <li><a href="PlanesController?action=verPlanes">Planes</a></li>            
                </ul>
            </nav>
            -->
        </div>

        <div class="main-content">
            <div class="custom-form-container">
                <form class="my-custom-form" method="post" action="VentasController">

                    <label for="fol" class="my-form-label">Folio Cliente:</label>
                    <input type="number" autocomplete="off" name="fol" class="my-form-input" value="" readonly required>
                    
                    <label for="DesProd" class="my-form-label">Descripción:</label>
                    <textarea id="id" name="DesProd" style="resize: none" rows="10" cols="15" required class="my-form-input"></textarea>
                    
                    <label for="Exi" class="my-form-label">Existencias:</label>
                    <input type="number" autocomplete="off" name="Exi" class="my-form-input" required>

                    <label for="CosProdu" class="my-form-label">Precio:</label>
                    <input type="number" autocomplete="off" name="CosProdu" class="my-form-input" required>
                    
                    <button type="submit" class="my-form-button" name="action" value="insertarVenta">Agregar Producto</button>
                </form>


            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2023 BlackGym, The Godfathers.</p>
        </footer>

    </body>
</html>