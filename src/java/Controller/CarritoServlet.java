package Controller;

import Modelo.Producto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// Obtener/crear carrito en sesión
	HttpSession session = request.getSession(true);
	List<String> carrito = (List<String>) session.getAttribute("carrito");
	List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");
	if (carrito == null) {
	    carrito = new ArrayList<>();
	    productosCarrito = new ArrayList<>();
	    session.setAttribute("carrito", carrito);
	    session.setAttribute("productosCarrito", productosCarrito);
	}

	String accion = request.getParameter("accion");
	String ajax = request.getParameter("ajax");
	int cantidadProductos = 0;
	double precioTotal = 0.0;

	if ("agregar".equals(accion)) {
	    // Parámetros producto
	    int numProd = Integer.parseInt(request.getParameter("NumProd"));
	    String nomProd = request.getParameter("NomProd");
	    double cosProd = Double.parseDouble(request.getParameter("CosProdu"));
	    String image_Url = request.getParameter("image_Url");  // coincide con JS
	    Producto p = new Producto(0);
	    p.setNumProd(numProd);
	    p.setNomProd(nomProd);
	    p.setCosProdu(cosProd);
	    p.setImage_url(image_Url);                             // guardas la URL completa
	    productosCarrito.add(p);
	    carrito.add(nomProd);

	    // Recalcular totales
	    int countThis = 0;
	    for (Producto prod : productosCarrito) {
		cantidadProductos++;
		precioTotal += prod.getCosProdu();
		if (prod.getNumProd() == numProd) {
		    countThis++;
		}
	    }
	    session.setAttribute("cantidadProductos", cantidadProductos);
	    session.setAttribute("precio", precioTotal);

	    // Si es petición AJAX devolvemos sólo JSON con la cantidad de este producto
	    if ("true".equals(ajax)) {
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write("{\"quantity\":" + countThis + "}");
		return;
	    }
	    // Si no es AJAX, redirigimos a la vista del carrito
	    response.sendRedirect("CarritoServlet?accion=ver");
	    return;
	}

	if ("quitar".equals(accion)) {
	    int numProd = Integer.parseInt(request.getParameter("NumProd"));
	    String nomProd = request.getParameter("NomProd");

	    // Eliminar de productosCarrito y carrito
	    Iterator<Producto> it = productosCarrito.iterator();
	    while (it.hasNext()) {
		Producto prod = it.next();
		if (prod.getNumProd() == numProd) {
		    it.remove();
		    break;
		}
	    }
	    carrito.remove(nomProd);

	    // Recalcular totales
	    for (Producto prod : productosCarrito) {
		cantidadProductos++;
		precioTotal += prod.getCosProdu();
	    }
	    session.setAttribute("cantidadProductos", cantidadProductos);
	    session.setAttribute("precio", precioTotal);

	    response.sendRedirect("CarritoServlet?accion=ver");
	    return;
	}

	if ("limpiar".equals(accion)) {
	    productosCarrito.clear();
	    carrito.clear();
	    session.setAttribute("cantidadProductos", 0);
	    session.setAttribute("precio", 0.0);
	    response.sendRedirect("CarritoServlet?accion=ver");
	    return;
	}

	if ("ver".equals(accion)) {
	    // Simplemente despachamos a carrito.jsp para mostrarlo
	    RequestDispatcher rd = request.getRequestDispatcher("/carrito.jsp");
	    rd.forward(request, response);
	    return;
	}

	// Acción por defecto
	response.sendRedirect("ProductosController?action=verProductos");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }
}
