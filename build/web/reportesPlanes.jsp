<%-- 
    Document   : reportesPlanes
    Created on : 1 ene. 2024, 15:14:01
    Author     : Daniel
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="favicon.ico">
        <title>Reporte Planes</title>
        <link rel="stylesheet" href="Styles/planes.css"/>
        <link rel="stylesheet" href="Styles/admin.css"/>
        <link rel="stylesheet" href="Styles/style.css"/>
        <link rel="stylesheet" href="Styles/botones.css"/>
        <link rel="stylesheet" href="Styles/modificarSocios.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" />
    </head>

    <body>
        <div class="container">
            <div class="masthead">
                <h3 class="text-muted">BlackGym Reportes</h3>
                <nav>
                    <ul class="nav nav-justified">
                        <li><a href="PlanesController?action=inicio">Inicio</a></li>            
                        <li><a href="SociosController?action=verSocios">Socios</a></li>                        
                        <li><a href="VentasController?action=verReporte">R. Productos</a></li>            
                    </ul>
                </nav>
            </div>


            <aside>
                <div class="panel panel-default contenedorAside">
                    <div class="panel-heading">
                        <h3 class="panel-title"><b>Totales</b></h3>
                    </div>
                    <div class="contenedorDatos">
                        <div class="Efectivo">
                            <p>Efectivo: </p>
                            <p><b> $ ${costosEfe} </b></p>
                        </div>

                        <div class="Tarjeta">
                            <p>Tarjeta Debito: </p>
                            <p><b>  $ ${costosTarDeb} </b></p>
                        </div>
                        
                        <div class="Tarjeta">
                            <p>Tarjeta Credito: </p>
                            <p><b>  $ ${costosTarCre} </b></p>
                        </div>

                        <div class="Total">
                            <p>Total: </p>
                            <p><b>  $ ${costosTot} </b></p>
                        </div>
                    </div>
                </div>
            </aside>

            <div class="contenedor con-sidebar"> 
                <div class="tabla">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><b>Reporte</b></h3>
                        </div>

                        <div class="panel-body">

                            <form class="filtros" method="post" action="VentasController">
                                Inicio: <input class="my-form-input" id="Date_search" name="fechaIni" type="date" value="${fechaI}" required placeholder="Search by Date">        
                                Final: <input class="my-form-input" id="Date_search" name="fechaFin" type="date" value="${fechaF}" required placeholder="Search by Date"/>   
                                <!--  
                                <div><a href="VentasController?action=fechas" style="border-radius: 10px"><i class="icono"></i> Ver </a></div>
                                -->
                                <button type="submit" class="my-form-button" name="action" value="VerVentasPlanes">Ver</button>
                            </form>

                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th class="left">Num VPl</th>
                                        <th>Folio Socio</th>
                                        <th>Num. Plan</th>
                                        <th>Precio</th>
                                        <th>Fecha</th>
                                        <th>Hora</th>
                                        <th>F. Pago</th>
                                        <th>Acción</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${todas}" var="venta" varStatus="status">
                                        <tr> 
                                            <td>${venta.getNumVenta()}</td>
                                            <td>${venta.getFol()}</td>
                                            <td>${venta.getNum_Plan()}</td>
                                            <td>${venta.getCosP()}</td>
                                            <td>${venta.getFecV()}</td> 
                                            <td>${venta.getHor()}</td>
                                            <td>${venta.getForP()}</td>
                                            <td>
                                                <a class="imprimir"  href="VentasController?action=descargarTicket&numVenta=${venta.getNumVenta()}&Fol=${venta.getFol()}">Descargar Ticket</a>
                                                <a class="enviar"  href="VentasController?action=enviarPorCorreo&numVenta=${venta.getNumVenta()}&Fol=${venta.getFol()}">Enviar por Correo</a>
                                            </td>
                                            </tr>
                                    </c:forEach>
                                </tbody> 
                            </table>
                        </div>
                    </div>
                </div>

            </div>

            <footer class="footer">
                <p>&copy; 2023 BlackGym, The Godfathers.</p>
            </footer>
        </div>
    </body>
</html>
