<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Detalle de Producto</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/producto-detalle.css"/>
	<!-- CSS globales (usa contextPath para rutas absolutas) -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/planes.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/admin.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/productos-grid.css"/>
	<!-- CSS detalle -->

    </head>
    <body>
	<div class="container">
	    <div class="masthead">
		<h3 class="text-muted">BlackGym Productos</h3>
		<nav>
		    <ul class="nav nav-justified">
			<li><a href="PlanesController?action=inicio">Inicio</a></li>
			<li><a href="PlanesController?action=verPlanes">Planes</a></li>
			<li><a href="ProductosController?action=verProductos">Ver Productos</a></li>
		    </ul>
		</nav>
	    </div>

	    <div class="panel panel-default detail-panel">
		<div class="panel-heading">
		    <h3 class="panel-title">Detalle de Producto</h3>
		</div>
		<div class="panel-body detail-body">
		    <!-- Grid container -->
		    <div class="detail-layout">
			<!-- Imagen grande -->
			<div class="detail-image">
			    <img src="${pageContext.request.contextPath}/img/${producto.image_url}" alt="${producto.nomProd}" />
			</div>

			<!-- Información central -->
			<div class="detail-info">
			    <h1 class="detail-title">${producto.nomProd}</h1>
			    <p style="margin-bottom: 20px">$${producto.cosProdu} MXN</p>
			    <p style="margin-bottom: 20px"><span class="label">Descripcion:</span> ${producto.desProd}</p>
			    <p style="margin-bottom: 20px"><span class="label">Categoria:</span> ${producto.categoria}</p>
			    <p style="margin-bottom: 20px"><span class="label">Material:</span> ${producto.material}</p>
			    <p style="margin-bottom: 20px"><span class="label">Modelo:</span> ${producto.modelo}</p>
			    <p style="margin-bottom: 20px"><span class="label">Peso:</span> ${producto.peso} KG</p>
			    <p style="margin-bottom: 20px"><span class="label">Existencias:</span> ${producto.exi} Unidades Disponibles</p>

			</div>

			<!-- Sección de compra -->
			<div class="purchase-box">
			    <div class="price">$${producto.cosProdu} MXN</div>
			    <div class="location">Ciudad de México</div>
			    <!-- <button class="btn add-cart">Agregar al carrito</button> -->
			    <a href="CarritoServlet?accion=agregar&NumProd=${producto.getNumProd()}&NomProd=${producto.getNomProd()}&CosProdu=${producto.getCosProdu()}" 
			       class="btn add-cart" style="text-decoration: none; text-align: center"> Agregar al carrito</a>
			    <button class="btn buy-now">Comprar ahora</button>

			    <div class="shipping-info">
				<h4>Envío desde</h4><p>BlackGym</p>
				<h4>Vendido por</h4><p>BlackGym México</p>
				<h4>Devoluciones</h4><p>Devolución sin costo durante 14 días a partir de que recibes el producto</p>
				<h4>Pago</h4><p>Transacción segura</p>
			    </div>
			</div>
		    </div>

		    <a href="ProductosController?action=verProductos" class="back-button">&larr; Volver a la lista</a>
		</div>
	    </div>

	    <footer class="footer">
		<p>&copy; 2023 BlackGym, Daniel De La Cruz.</p>
	    </footer>
	</div>
    </body>
</html>