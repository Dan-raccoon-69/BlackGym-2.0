<%-- 
    Document   : carrito
    Created on : 26 abr. 2025, 17:33:25
    Author     : gusta
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<meta charset="UTF-8">
	<title>Mi Carrito</title>
	<link rel="icon" href="favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/cart.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/planes.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/admin.css"/>
    </head>
    <body>
	<div class="cart-container">
	    <h2>Tu Carrito</h2>
	    <c:if test="${empty sessionScope.productosCarrito}">
		<p>Tu carrito está vacío.</p>
	    </c:if>
	    <c:if test="${not empty sessionScope.productosCarrito}">
		<table class="cart-table">
		    <thead>
			<tr>
			    <th>Imagen</th>
			    <th>Nombre</th>
			    <th>Precio unitario</th>
			    <th>Cantidad</th>
			    <th>Subtotal</th>
			    <th>Acción</th>
			</tr>
		    </thead>
		    <tbody>
			<c:forEach items="${sessionScope.productosCarrito}" var="prod">
			    <tr>
				<td>
				    <img
					src="${prod.image_url}"
					alt="${prod.nomProd}"
					height="140px"
					/>
				</td>
				<td>${prod.nomProd}</td>
				<td>$${prod.cosProdu} MXN</td>
				<td>1</td>
				<td>$${prod.cosProdu} MXN</td>
				<td>
				    <a class="remove"
				       href="CarritoServlet?accion=quitar&NumProd=${prod.numProd}&NomProd=${prod.nomProd}">
					Quitar
				    </a>
				</td>
			    </tr>
			</c:forEach>

		    </tbody>
		    <tfoot>
			<tr>
			    <td colspan="3"></td>
			    <td><strong>Total productos:</strong></td>
			    <td colspan="2">${sessionScope.cantidadProductos}</td>
			</tr>
			<tr>
			    <td colspan="3"></td>
			    <td><strong>Total a pagar:</strong></td>
			    <td colspan="2">$${sessionScope.precio} MXN</td>
			</tr>
		    </tfoot>
		</table>
		<div class="cart-actions">
		    <a href="ProductosController?action=verProductos" class="btn">Seguir comprando</a>
		    <a href="VentasController?action=insertar" class="btn buy-now">Finalizar compra</a>
		</div>
	    </c:if>
	</div>
    </body>
</html>
