<%@ page import="java.net.URLEncoder" %><!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Styles/homePage.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" />
        <title>BlackGYM</title>
        <!-- Aseg�rate de que Chart.js se est� cargando correctamente -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <!-- Agrega la referencia a jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <link rel="stylesheet" href="../Styles/homePage.css"/>
        <script>
            // Funci�n para convertir el formato de fecha a milisegundos
            function convertirFechaAMilisegundos(fechaString) {
                var meses = {
                    "ene.": 0, "feb.": 1, "mar.": 2, "abr.": 3, "may.": 4, "jun.": 5,
                    "jul.": 6, "ago.": 7, "sep.": 8, "oct.": 9, "nov.": 10, "dic.": 11
                };

                var partesFecha = fechaString.split(" ");
                var mes = meses[partesFecha[0].toLowerCase()];
                var dia = parseInt(partesFecha[1].replace(",", ""));
                var anio = parseInt(partesFecha[2]);

                return new Date(anio, mes, dia).getTime();
            }

            $(document).ready(function () {
                // Captura el evento de escribir en el campo de b�squeda
                $("#searchInput").on("input", function () {
                    // Obt�n el valor del campo de b�squeda
                    var query = $(this).val();

                    // Realiza una solicitud AJAX al servidor para obtener sugerencias
                    $.ajax({
                        type: "POST",
                        url: "AutocompleteController",
                        data: {query: query},
                        success: function (data) {
                            // Verifica si data es un objeto y no una cadena
                            var suggestions = typeof data === 'string' ? JSON.parse(data) : data;

                            // Actualiza el contenido de la lista de sugerencias
                            var suggestionList = $("#suggestionList");
                            suggestionList.empty(); // Limpia las sugerencias anteriores

                            // Agrega las nuevas sugerencias como enlaces
                            suggestions.forEach(function (suggestion) {
                                suggestionList.append("<a href='#' data-nom='" + suggestion.Nom + "' data-inp='" + suggestion.Inp + "' data-fip='" + suggestion.Fip + "' data-fol='" + suggestion.fol + "'>" + suggestion.Nom + "</a>");
                            });
                        }
                    });
                });

                // Maneja el clic en un enlace de sugerencia
                $("#suggestionList").on("click", "a", function (event) {
                    event.preventDefault();

                    // Obt�n los datos del enlace clicado
                    var selectedSocio = {
                        fol: $(this).data("fol"),
                        Nom: $(this).data("nom"),
                        Inp: $(this).data("inp"),
                        Fip: $(this).data("fip")
                    };

                    // Redirecciona a la p�gina deseada con los par�metros del socio
                    window.location.href = "homePage.jsp?nombre=" + encodeURIComponent(selectedSocio.Nom) + "&inp=" + selectedSocio.Inp + "&fip=" + selectedSocio.Fip + "&fol=" + selectedSocio.fol;
                });

                // Obtiene el elemento canvas despu�s de la redirecci�n
                var canvas = document.getElementById('progressChart');

                if (canvas) {
                    // Obtiene el contexto 2D del canvas
                    var ctx = canvas.getContext('2d');

                    if (!ctx) {
                        console.error("No se pudo obtener el contexto 2D del elemento canvas.");
                    } else {
                        // Configuraci�n del gr�fico
                        var startDate = convertirFechaAMilisegundos("${param.inp}");
                        var endDate = convertirFechaAMilisegundos("${param.fip}");
                        var currentDate = new Date().getTime();
                        var progress = ((currentDate - startDate) / (endDate - startDate)) * 100;

                        console.log('Fecha de inicio:', new Date(startDate));
                        console.log('Fecha de finalizaci�n:', new Date(endDate));
                        console.log('Fecha actual:', new Date(currentDate));
                        console.log('Progreso:', progress);

                        // Actualiza el porcentaje en la interfaz gr�fica
                        $("#progressPercentage").text(progress.toFixed(3) + "%");

                        // Configuraci�n del gr�fico
                        var config = {
                            type: 'doughnut',
                            data: {
                                labels: ['Progreso', 'Restante'],
                                datasets: [{
                                        data: [progress, 100 - progress],
                                        backgroundColor: ['#14213d', '#e7b057'],
                                        //backgroundColor: ['#1a1110', '#353839'],
                                    }]
                            },
                            options: {
                                responsive: true,
                                legend: {
                                    display: false
                                },
                            }
                        };

                        // Crea el gr�fico
                        new Chart(ctx, config);
                    }
                } else {
                    console.error("No se encontr� el elemento canvas con ID 'progressChart'.");
                }
            });
        </script>

    </head>
    <body>

        <!-- Barra de navegaci�n moderna -->
        <nav class="navbar">
            <div class="navbar-left">
                <img src="img/LogoBlackGym-removebg-preview.png" alt="Logo de BlackGym" class="logo">
            </div>
            <div class="contenedorBuscardor">
                <form method="post" class="navbar-form navbar-right" id="searchForm">
                    <div class="form-group">
                        <input type="text" autocomplete="off" name="query" required placeholder="Buscar Socio..." class="form-control" id="searchInput">
                    </div>
                </form>
                <div id="suggestionsContainer">
                    <ul id="suggestionList"></ul>
                </div>
            </div>

            <!-- Agregamos el bot�n de hamburguesa para dispositivos m�viles -->
            <div class="burger-menu" onclick="toggleSidebar()">
                <div class="bar"></div>
                <div class="bar"></div>
                <div class="bar"></div>
            </div>
        </nav>

        <!-- Contenido principal -->
        <div class="main-container">
            <aside class="sidebar">
                <div class="menu-item"><a href="PlanesController?action=verPlanes"><i class="icono"></i> Planes</a></div>
                <div class="menu-item submenu">
                    <a href="SociosController?action=verSocios"><i class="icono"></i> Socios</a>
                    <div class="submenu-content">
                        <a href="SociosController?action=agregarSocios"><i class="icono"></i> Agregar</a>
                        <a href="SociosController?action=verSocios"><i class="icono"></i> Ver Socios</a>
                    </div>
                </div>
                <div class="menu-item submenu">
                    <a href="VentasController?action=agregarVentas"><i class="icono"></i> Ventas</a>
                    <div class="submenu-content">
                        <a href="VentasController?action=agregarVentas"><i class="icono"></i> Productos</a>
                        <a href="VentasController?action=agregarVentas"><i class="icono"></i> Planes</a>
                    </div>
                </div>
                <div class="menu-item"><a href="VentasController?action=verReporte"><i class="icono"></i> Reportes</a></div>
                <div class="menu-item"><a href="LoginController?action=irAdministracion"><i class="icono"></i> Administrador</a></div>
                <div class="menu-item"><a href="ProductosController?action=verProductos"><i class="icono"></i> Productos</a></div>
            </aside>

            <!-- Contenedor adicional -->
            <div class="additional-container">
                <div class="rectangle">
                    <p style="font-family: 'Times New Roman', Times, serif; font-weight: bolder;">BIENVENIDO</p>
                </div>
                <div class="cuadrados_container">

                    <div class="square">
                        <div class="square-info">
                            <div class="panel-heading">
                                <h2 class="panel-title"> ${param.nombre} </h3>
                            </div>
                            <div class="panel-heading">
                                <figure>
                                    <img src="img/user_icon_149851.png" alt="Foto del Usuario">
                                </figure>
                            </div>
                            <div class="panel-heading">
                                <h3><b>Folio: ${param.fol} </b> </h3>
                                <h3><b>Inicio: ${param.inp} </b> </h3>
                                <h3><b>Vence: ${param.fip} </b> </h3>  
                                <a class="enlace" href="SociosController?action=modificar&fol=${param.fol}" role="button">Modificar Plan</a> 
                            </div>
                        </div> 
                    </div>

                    <div class="square">
                        <div class="aquare-info">
                            <div class="panel-heading">
                                <div class="panel-heading">
                                    <h2><b>Porcentaje del avance del plan: <p style="display: inline" id="progressPercentage">0%</p>  </b> </h2>
                                    <canvas id="progressChart"></canvas>
                                </div>
                            </div>
                            <div class="panel-heading">
                            </div>
                        </div> 
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>