<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Detalle de Producto</title>
	<!-- Tus hojas de estilo existentes -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/producto-detalle.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/planes.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/admin.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/productos-grid.css"/>
	<!-- Nueva hoja de estilos para el modal -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/cart-modal.css"/>
    </head>
    <body>
	<div class="container">
	    <!-- Encabezado -->
	    <div class="masthead">
		<h3 class="text-muted">BlackGym Productos</h3>
		<nav>
		    <ul class="nav nav-justified">
			<li><a href="PlanesController?action=inicio">Inicio</a></li>
			<li><a href="PlanesController?action=verPlanes">Planes</a></li>
			<li><a href="ProductosController?action=verProductos">Ver Productos</a></li>
			<!-- ↓ Nuevo ítem: Carrito ↓ -->
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

	    <!-- Panel de detalle -->
	    <div class="panel panel-default detail-panel">
		<div class="panel-heading">
		    <h3 class="panel-title">Detalle de Producto</h3>
		</div>
		<div class="panel-body detail-body">
		    <div class="detail-layout">
			<!-- Imagen grande -->
			<div class="detail-image">
			    <img src="${pageContext.request.contextPath}/img/${producto.image_url}" alt="${producto.nomProd}" />
			</div>
			<!-- Info central -->
			<div class="detail-info">
			    <h1 class="detail-title">${producto.nomProd}</h1>
			    <p class="price">$${producto.cosProdu} MXN</p>
			    <p><span class="label">Descripción:</span> ${producto.desProd}</p>
			    <p><span class="label">Categoría:</span> ${producto.categoria}</p>
			    <p><span class="label">Material:</span> ${producto.material}</p>
			    <p><span class="label">Modelo:</span> ${producto.modelo}</p>
			    <p><span class="label">Peso:</span> ${producto.peso} KG</p>
			    <p><span class="label">Existencias:</span> ${producto.exi} unidades</p>
			</div>
			<!-- Caja de compra -->
			<div class="purchase-box">
			    <div class="price">$${producto.cosProdu} MXN</div>
			    <div class="location">Ciudad de México</div>
			    <!-- Botón que llama al servlet vía fetch y muestra modal -->
			    <button type="button"
				    class="btn add-cart"
				    data-numprod="${producto.numProd}"
				    data-nomprod="${producto.nomProd}"
				    data-imgurl="${pageContext.request.contextPath}/img/${producto.image_url}"
				    data-cosprodu="${producto.cosProdu}">
				Agregar al carrito
			    </button>
			    <button class="btn buy-now">Comprar ahora</button>
			    <div class="shipping-info">
				<h4>Envío desde</h4><p>BlackGym</p>
				<h4>Vendido por</h4><p>BlackGym México</p>
				<h4>Devoluciones</h4><p>Devolución sin costo durante 14 días</p>
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

	<!-- Modal “Agregaste a tu carrito” -->
	<div id="cartModal" class="modal">
	    <div class="modal-content">
		<div class="modal-header">
		    <h4>Agregaste a tu carrito</h4>
		    <span id="modalClose" class="modal-close">&times;</span>
		</div>
		<div class="modal-body">
		    <img id="modalImg" src="" alt="Imagen producto"/>
		    <div class="modal-details">
			<p id="modalName"></p>
			<p id="modalPrice"></p>
			<p id="modalQuantity">Unidades: 1</p>
		    </div>
		</div>
		<div class="modal-footer">
		    <a href="ProductosController?action=verProductos" class="btn" id="closeBtn">Continuar comprando</a>
		    <a href="CarritoServlet?accion=ver" class="btn view-cart">Ver carrito</a>
		</div>
	    </div>
	</div>

	<!-- Al final del body, justo antes de </body>: -->
	<script>
	    document.querySelectorAll('.add-cart').forEach(btn => {
		btn.addEventListener('click', function (e) {
		    e.preventDefault();
		    const num = this.dataset.numprod;
		    const nom = this.dataset.nomprod;
		    const img = this.dataset.imgurl;    // esto ya trae el contexto + "/img/archivo.jpg"
		    const prec = this.dataset.cosprodu;

		    const url = 'CarritoServlet?accion=agregar'
			    + '&NumProd=' + num
			    + '&NomProd=' + encodeURIComponent(nom)
			    + '&CosProdu=' + prec
			    + '&image_Url=' + encodeURIComponent(img)
			    + '&ajax=true';

		    fetch(url)
			    .then(res => res.json())
			    .then(data => {
				document.getElementById('modalImg').src = img;
				document.getElementById('modalName').textContent = nom;
				document.getElementById('modalPrice').textContent = '$' + prec + ' MXN';
				document.getElementById('modalQuantity').textContent = 'Unidades: ' + data.quantity;
				document.getElementById('cartModal').classList.add('show');
			    })
			    .catch(console.error);
		});
	    });
	</script>

    </body>
</html>
