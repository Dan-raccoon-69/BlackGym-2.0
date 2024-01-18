<%-- 
    Document   : modificarPlanSocio
    Created on : 2 ene. 2024, 11:21:29
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Modificar Plan</title>
        <link rel="stylesheet" href="Styles/admin.css"/>
        <link rel="stylesheet" href="Styles/modificarSocios.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" /> 

        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script>
            $(document).ready(function () {
                // Manejar el cambio en el select de planes
                $('select[name="NumPlan"]').change(function () {
                    actualizarFechaTermino();
                });

                // Manejar el cambio en el input de fecha inicial
                $('input[name="fecha"]').change(function () {
                    actualizarFechaTermino();
                });

                // Función para actualizar la fecha de término
                function actualizarFechaTermino() {
                    var fechaInicial = $('input[name="fecha"]').val();
                    var numPlanSeleccionado = $('select[name="NumPlan"]').val();
                    console.log("fechaInicial ", fechaInicial);
                    console.log(numPlanSeleccionado);
                    // Lógica para calcular la fecha de término basada en el tipo de plan
                    let fechaTermino;

                    switch (numPlanSeleccionado) {
                        case '1':
                            fechaTermino = new Date(fechaInicial);
                            fechaTermino.setFullYear(fechaTermino.getFullYear() + 1);
                            break;

                        case '2':
                        case '5':
                            fechaTermino = new Date(fechaInicial);
                            fechaTermino.setMonth(fechaTermino.getMonth() + 1);
                            break;

                        case '3':
                            fechaTermino = new Date(fechaInicial);
                            fechaTermino.setMonth(fechaTermino.getMonth() + 3);
                            break;

                        default:
                            // Si el tipo de plan no se reconoce, no hacemos nada
                            console.log("No se encontro");
                            return;
                    }

                    console.log("FechaTermino: ", fechaTermino);
                    // Formatea la fecha de término como "dd/mm/yyyy"
                    var fechaTerminoFormateada = fechaTermino.getDate().toString().padStart(2, '0') + '/' + (fechaTermino.getMonth() + 1).toString().padStart(2, '0') + '/' + fechaTermino.getFullYear();
                    // Actualiza el valor del input de fecha de término (oculto) en formato estándar "yyyy-mm-dd"
                    var fechaTerminoEstandar = fechaTermino.toISOString().split('T')[0];
                    $('#fechaOut').val(fechaTerminoEstandar);
                }

                // Llama a la función al inicio para manejar la fecha inicial por defecto
                actualizarFechaTermino();
            });

        </script>

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
                <form class="my-custom-form" method="post" action="SociosController">
                    <input type="hidden" class="my-form-input" name="fol" value="${socio.fol}">

                    <label for="Nom" class="my-form-label">Nombre:</label>
                    <input type="text" autocomplete="off" name="Nom" value="${socio.nom}" class="my-form-input" required readonly>

                    <label for="Eda" class="my-form-label">Edad:</label>
                    <input type="text" autocomplete="off" name="Eda" class="my-form-input" value="${socio.eda}" required readonly>

                    <label for="Tel" class="my-form-label">Telefono:</label>
                    <input type="text" autocomplete="off" name="Tel" value="${socio.tel}" class="my-form-input" required readonly>

                    <label for="CorElec" class="my-form-label">Email:</label>
                    <input type="text" autocomplete="off" name="CorElec" class="my-form-input" value="${socio.corElec}" required readonly>

                    <label for="NumPlan" class="my-form-label">Seleccionar Plan:</label>
                    <select name="NumPlan" class="my-form-input" required>
                        <c:forEach var="plan" items="${listaPlanes}">
                            <c:choose>
                                <c:when test="${socio.numPlan eq plan.numPlan}">
                                    <option value="${plan.numPlan}" selected>${plan.nom}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${plan.numPlan}">${plan.nom}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>

                    <label for="fecha" class="my-form-label" >Fecha de inicio:</label>
                    <input type="date" class="my-form-input" value="${socio.inp}" id="fecha" name="fecha" required>

                    <label for="fechaOut" class="my-form-label">Fecha de Termino:</label>
                    <input type="date" class="my-form-input" id="fechaOut" name="fechaOut" readonly>


                    <button type="submit" class="my-form-button" name="action" value="modificarPlan">Modificar Plan</button>
                </form>
            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2023 BlackGym, Daniel De La Cruz.</p>
        </footer>

    </body>
</html>