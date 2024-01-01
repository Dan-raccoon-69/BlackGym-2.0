<%-- 
    Document   : agregarSocios
    Created on : 10 dic. 2023, 15:36:41
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Agregar Socio</title>
        <link rel="stylesheet" href="Styles/admin.css"/>
        <link rel="stylesheet" href="Styles/modificarSocios.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" /> 

        <!-- Agrega esto en la sección head de tu HTML -->
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <!-- Agrega estos enlaces en el encabezado de tu HTML -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.17/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.17/dist/sweetalert2.all.min.js"></script>
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

        <script>
            function validarFormulario() {
                var edad = document.forms["miFormulario"]["Eda"].value;
                var telefono = document.forms["miFormulario"]["Tel"].value;
                var email = document.forms["miFormulario"]["CorElec"].value;

                // Validación de Edad
                if (isNaN(edad) || edad < 0) {
                    mostrarAlerta("Por favor, ingrese una edad válida.");
                    return false;
                }

                // Validación de Teléfono (puedes personalizar según tus requisitos)
                var telefonoRegex = /^[0-9]{10}$/; // Ejemplo: 1234567890
                if (!telefono.match(telefonoRegex)) {
                    mostrarAlerta("Por favor, ingrese un número de teléfono válido.");
                    return false;
                }

                // Validación de Email (puedes utilizar una expresión regular más compleja)
                var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!email.match(emailRegex)) {
                    mostrarAlerta("Por favor, ingrese una dirección de correo electrónico válida.");
                    return false;
                }

                // Si todas las validaciones son exitosas, el formulario se envía
                return true;
            }

            function mostrarAlerta(mensaje) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: mensaje,
                    confirmButtonColor: '#3085d6',
                    background: '#1a1a1a',
                    customClass: {
                        confirmButton: 'my-swal-button',
                    },
                });
            }
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
                <form class="my-custom-form" method="post" action="SociosController" id="miFormulario" onsubmit="return validarFormulario()">

                    <label for="Nom" class="my-form-label">Nombre:</label>
                    <input type="text" autocomplete="off" name="Nom" class="my-form-input" required>

                    <label for="Eda" class="my-form-label">Edad:</label>
                    <input type="number" autocomplete="off" name="Eda" class="my-form-input" required>

                    <label for="Tel" class="my-form-label">Telefono:</label>
                    <input type="text" autocomplete="off" name="Tel" class="my-form-input" required>

                    <label for="CorElec" class="my-form-label">Email:</label>
                    <input type="text" autocomplete="off" name="CorElec" class="my-form-input" required>

                    <label for="NumPlan" class="my-form-label">Seleccionar Plan:</label>
                    <select name="NumPlan" class="my-form-input" required>
                        <c:forEach var="plan" items="${listaPlanes}">
                            <option value="${plan.numPlan}">${plan.nom}</option>
                        </c:forEach>
                    </select>

                    <label for="fecha" class="my-form-label" >Fecha de Inicio:</label>
                    <input type="date" class="my-form-input" value="${fechaActual}" name="fecha" readonly required>

                    <label for="fechaOut" class="my-form-label">Fecha de Termino:</label>
                    <input type="date" class="my-form-input" id="fechaOut" name="fechaOut" readonly >


                    <button type="submit" class="my-form-button" name="action" value="insertar">Agregar Socio</button>
                </form>


            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2023 BlackGym, The Godfathers.</p>
        </footer>

    </body>
</html>