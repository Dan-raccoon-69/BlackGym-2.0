<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="favicon.ico">
	<title>Productos</title>
	<!-- Tus estilos originales -->
	<link rel="stylesheet" href="Styles/planes.css"/>
	<link rel="stylesheet" href="Styles/admin.css"/>
	<!-- Nuevo stylesheet para grid de productos -->
	<link rel="stylesheet" href="Styles/productos-grid.css"/>

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
			<li class="cart-button">
			    <a href="CarritoServlet?accion=ver">
				<i class="fa fa-shopping-cart" aria-hidden="true"></i>
				<span>Carrito</span>
				<span class="count">
				    <c:out value="${sessionScope.cantidadProductos != null ? sessionScope.cantidadProductos : 0}"/>
				</span>
			    </a>
			</li>
		    </ul>
		</nav>
	    </div>

	    <form method="post" action="ProductosController" class="navbar-form navbar-right">
		<div class="form-group">
		    <input type="text" autocomplete="off" name="query" required 
			   placeholder="Buscar Producto..." class="form-control">
		</div>
		<button type="submit" class="btn btn-success">Buscar</button>
	    </form>

	    <div class="panel panel-default">
		<div class="panel-heading">
		    <h3 class="panel-title">Lista de Productos</h3>
		</div>
		<div class="panel-body">
		    <div class="product-grid">
			<c:forEach items="${todas}" var="productos">
			    <!-- Tarjeta clicable con animación al click -->
			    <a href="ProductosController?action=verDetalle&NumProd=${productos.numProd}" 
			       style="text-decoration:none; color:inherit;" 
			       class="product-card">
				<img src="${pageContext.request.contextPath}/img/${productos.image_url}" 
				     alt="${productos.nomProd}" 
				     height="310px" />
				<div class="product-info">
				    <div class="product-name">${productos.nomProd}</div>
				    <div class="product-desc">${productos.desProd}</div>
				    <div class="product-footer">
					<div class="product-price">$${productos.cosProdu}</div>
				    </div>
				</div>
			    </a>
			</c:forEach>
		    </div>
		</div>
	    </div>

	    <footer class="footer">
		<p>&copy; 2023 BlackGym, Daniel De La Cruz.</p>
	    </footer>
	</div>

    </body>
</html>