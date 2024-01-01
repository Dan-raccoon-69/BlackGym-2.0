<%-- 
    Document   : index
    Created on : 25 nov. 2023, 16:48:29
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="Styles/login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" />
</head>

<body>
    <div class="login-container">
        <div class="login-box">
            <img src="img/LogoBlackGym-removebg-preview.png" class="logo" alt="Logo" width="100" height="100" />
            <h2 class="form-title">Iniciar Sesión</h2>
            <!-- action="/tu_ruta_de_procesamiento" method="post"-->
            <form id="loginForm" class="login-form" action="LoginController" method="post">
                <label for="usuario" class="form-label">Usuario:</label>
                <input type="text" id="usuario" name="usuario" class="form-input" required />

                <label for="contrasena" class="form-label">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" class="form-input" required />
                <p class="text-danger"> ${mensaje} </p>
                <button type="submit" class="form-button">Iniciar Sesión</button>
            </form>
        </div>
    </div>
</body>

</html>