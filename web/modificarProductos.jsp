<%-- 
    Document   : modificarProductos
    Created on : 1 ene. 2024, 12:04:25
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Modificar Producto</title>
        <link rel="stylesheet" href="Styles/admin.css"/>
        <link rel="stylesheet" href="Styles/modificarSocios.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" /> 
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.17/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.17/dist/sweetalert2.all.min.js"></script>
       
        <script>
            function validarFormulario() {
                var Exi = document.forms["miFormulario"]["Exi"].value;
                var CosProdu = document.forms["miFormulario"]["CosProdu"].value;
                
                // Validación de existencias
                if (isNaN(Exi) || Exi <= 0) {
                    mostrarAlerta("Por favor, corrige el valor de las existencias.");
                    return false;
                }
                
                // Validación de precio producto
                if (isNaN(CosProdu) || CosProdu <= 0) {
                    mostrarAlerta("Por favor, corrige el precio que ingresaste.");
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
                <form class="my-custom-form" method="post" action="ProductosController" id="miFormulario" onsubmit="return validarFormulario()">
                    <input type="hidden" class="my-form-input" name="NumProd" value="${producto.numProd}">

                    <label for="NomProd" class="my-form-label">Nombre:</label>
                    <input type="text" autocomplete="off" name="NomProd" value="${producto.nomProd}" class="my-form-input" required>

                    <label for="DesProd" class="my-form-label">Descripción:</label>
                    <textarea name="DesProd" style="resize: none" rows="10" cols="15"" required class="my-form-input">
${producto.desProd}
                    </textarea>

                    <label for="Exi" class="my-form-label">Existencias:</label>
                    <input type="text" autocomplete="off" name="Exi" value="${producto.exi}" class="my-form-input" required>

                    <label for="CosProdu" class="my-form-label">Precio:</label>
                    <input type="text" autocomplete="off" name="CosProdu" class="my-form-input" value="${producto.cosProdu}" required>
                    
                    <button type="submit" class="my-form-button" name="action" value="modificar">Modificar Socio</button>
                </form>
            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2023 BlackGym, Daniel De La Cruz.</p>
        </footer>

    </body>
</html>