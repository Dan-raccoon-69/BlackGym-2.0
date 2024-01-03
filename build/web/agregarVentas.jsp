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
                    <input type="number" autocomplete="off" name="fol" class="my-form-input" value="${Fol}" readonly required>

                    <label for="nomSo" class="my-form-label">Nombre Cliente:</label>
                    <input type="text" autocomplete="off" name="nomSo" class="my-form-input" value="${nombreParametro}" readonly required>

                    <label for="numPlan" class="my-form-label">Num Plan:</label>
                    <input type="number" autocomplete="off" name="numPlan" class="my-form-input" value="${numPlanParametro}" readonly required>

                    <label for="CosPlan" class="my-form-label">Precio Plan:</label>
                    <input type="number" autocomplete="off" name="CosPlan" class="my-form-input" value="${costoPlan}" required readonly>

                    <label for="FecV" class="my-form-label">Fecha Venta: </label>
                    <input type="date" class="my-form-input" value="${fechaActual}" name="FecV" readonly required>

                    <label for="ForP" class="my-form-label">Forma de Pago</label>
                    <!-- 
                    <input type="text" class="my-form-input" name="ForP" autocomplete="off">
                    -->
                    <select class="my-form-input" name="ForP">
                        <option value="Efectivo">Efectivo</option>
                        <option value="Tarjeta Debito">Tarjeta de Debito</option>
                        <option value="Tarjeta Credito">Tarjeta de Credito</option>
                    </select>

                    <button type="submit" class="my-form-button" name="action" value="insertarVentaPlan">Agregar Producto</button>
                </form>


            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2023 BlackGym, The Godfathers.</p>
        </footer>

    </body>
</html>