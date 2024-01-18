<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="favicon.ico">
        <title>Planes</title>
        <link rel="stylesheet" href="Styles/planes.css"/>
        <link rel="stylesheet" href="Styles/admin.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" /> 
    </head>

    <body>
        <div class="container">
            <div class="masthead">
                <h3 class="text-muted">BlackGym</h3>
                <nav>
                    <ul class="nav nav-justified">
                        <li><a href="PlanesController?action=inicio">Inicio</a></li>            
                        <li><a href="SociosController?action=verSocios">Socios</a></li>                        
                        <li><a href="#" id="crearPlanLink">Crear Plan</a></li>            
                    </ul>
                </nav>
            </div>
            <form method ="post" action="BuscarController" class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" autocomplete="off" name="query" required placeholder="Buscar Plan..." class="form-control">
                </div>        
                <button type="submit" class="btn btn-success">Buscar</button>
            </form>
            <br>

            <div id="crearPlanForm" class="invisible" style="display: none">
                <div class="form-container">
                    <form class="custom-form" method ="post" action="PlanesController">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" autocomplete="off" id="nombre" name="nombre" class="form-input" required>

                        <label for="precio" class="form-label">Precio:</label>
                        <input type="text" autocomplete="off" id="precio" name="precio" class="form-input" required>

                        <button type="submit" class="form-button" name="action" value="insertar">Guardar Plan</button>
                    </form>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><b>Lista de Planes</b></h3>
                </div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th class="left">Num</th>
                                <th>Nombre</th>                
                                <th>Precio</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${todas}" var="plan" varStatus="status">
                                <tr> 
                                    <td class="left">${plan.getNumPlan()}</td>
                                    <td>${plan.getNom()}</td>
                                    <td>${plan.getP()}</td>
                                    <td class="text-right">
                                        <a class="btn btn-default" href="PlanesController?action=modificar&NumPlan=${plan.getNumPlan()}" role="button">Modificar</a>  
                                        <!--  
                                        <a class="btn btn-default" href="PlanesController?action=eliminar&NumPlan=${plan.getNumPlan()}" role="button">Eliminar</a>
                                        -->
                                    </td> 
                                </tr>
                            </c:forEach>>
                        </tbody> 
                    </table>
                </div>
            </div>

            <footer class="footer">
                <p>&copy; 2023 BlackGym, Daniel De La Cruz.</p>
            </footer>
        </div>
        <script src="Js/crearPlanes.js"></script>
    </body>
</html>