/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel
 */
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del producto seleccionado
        String NumProd = request.getParameter("NumProd");
        String NomProd = request.getParameter("NomProd");
        Double CosProdu = Double.valueOf(request.getParameter("CosProdu"));
        String accion = request.getParameter("accion");
        int cantidadProductos = 0;

        Producto p1 = new Producto(0);
        p1.setNumProd(Integer.parseInt(NumProd));
        p1.setNomProd((NomProd));
        p1.setCosProdu(CosProdu);
        // Obtener o crear la lista de productos en el carrito desde la sesión
        HttpSession session = request.getSession(true);
        List<String> carrito = (List<String>) session.getAttribute("carrito");
        List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
            productosCarrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
            session.setAttribute("productosCarrito", productosCarrito);
        }

        if ("quitar".equals(accion)) {
            // Quitar el producto correspondiente del carrito y de productosCarrito
            carrito.remove(NomProd);
            Iterator<Producto> iterator = productosCarrito.iterator();
            while (iterator.hasNext()) {
                Producto producto = iterator.next();
                if (producto.getNomProd().equals(NomProd)) {
                    iterator.remove();
                    break; // Se puede quitar este break si hay múltiples productos con el mismo nombre
                }
            }
        } else if ("agregar".equals(accion)){
            // Agregar el producto al carrito
            productosCarrito.add(p1);
            carrito.add(p1.getNomProd());
        } else if("limpiar".equals(accion)){
            cantidadProductos = 0;
            double precio = 0.0;
            session.setAttribute("precio", precio);
            session.setAttribute("cantidadProductos", cantidadProductos);
        }

        double precio = 0.0;
        for (Producto producto : productosCarrito) {
            cantidadProductos++;
            precio += producto.getCosProdu();
        }
        session.setAttribute("precio", precio);
        session.setAttribute("cantidadProductos", cantidadProductos);
        // Redireccionar de nuevo a la vista de Ventas
        response.sendRedirect("VentasController?action=agregarVentas");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
