/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Producto;
import dao.ProductoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
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
public class ProductosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        RequestDispatcher rd;

        switch (action) {
            case "verProductos":
                this.verTodosLosProductos(request, response);
                break;
            case "agregarProductos":
                // Redirigir a la página de agregar
                rd = request.getRequestDispatcher("/agregarProductos.jsp");
                rd.forward(request, response);
                break;
            case "modificar":
                // Obtener el número de socio a modificar desde los parámetros de la solicitud
                int NumProd = Integer.parseInt(request.getParameter("NumProd"));

                // Obtener el plan de la base de datos usando el PlanDao
                ProductoDao productosDao = new ProductoDao();
                Producto producto = productosDao.obtenerProductoPorFolio(NumProd);

                // Agregar el plan al request para que la vista pueda acceder a él
                request.setAttribute("producto", producto);

                // Redirigir a la página de modificación
                rd = request.getRequestDispatcher("/modificarProductos.jsp");
                rd.forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insertar".equals(action)) {

            String nombreParametro = request.getParameter("Nom");
            int existenciasParametro = Integer.parseInt(request.getParameter("Exi"));
            String descripcionParametro = request.getParameter("DesProd");
            Double precioParametro = Double.valueOf(request.getParameter("CosProdu"));

            Producto productoNuevo = new Producto(0);
            productoNuevo.setNomProd(nombreParametro);
            productoNuevo.setExi(existenciasParametro);
            productoNuevo.setDesProd(descripcionParametro);
            productoNuevo.setCosProdu(precioParametro);

            ProductoDao productoDao = new ProductoDao();
            boolean resultado = productoDao.insertar(productoNuevo);

            String mensaje = "";
            if (resultado) {
                mensaje = "El producto fue insertado correctamente.";
                System.out.println(mensaje);
            } else {
                mensaje = "Ocurrió un error, el producto no fue agregado.";
                System.out.println(mensaje);
            }
            verTodosLosProductos(request, response);

        } else if ("modificar".equals(action)) {
            
            int NumProd = Integer.parseInt(request.getParameter("NumProd"));
            String nombreParametro = request.getParameter("NomProd");
            String descripcionParametro = request.getParameter("DesProd");
            int existenciasParametro = Integer.parseInt(request.getParameter("Exi"));
            Double precioParametro = Double.valueOf(request.getParameter("CosProdu"));
            
            Producto productoModificado = new Producto(NumProd);
            productoModificado.setNomProd(nombreParametro);
            productoModificado.setDesProd(descripcionParametro);
            productoModificado.setExi(existenciasParametro);
            productoModificado.setCosProdu(precioParametro);

            ProductoDao productoDao = new ProductoDao();
            boolean resultado = productoDao.actualizarProducto(productoModificado);

            String mensaje = "";
            if (resultado) {
                mensaje = "El Producto fue modificado correctamente.";
                System.out.println(mensaje);
            } else {
                mensaje = "Ocurrió un error, el Producto no fue modificado.";
                System.out.println(mensaje);
            }
            verTodosLosProductos(request, response);
        }
    }

    protected void verTodosLosProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> todas = new LinkedList<>();
        ProductoDao producto = new ProductoDao();
        todas = producto.obtenerTodosLosProductos();
        RequestDispatcher rd;
        // compartimos la variable ultimas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        // enviamos respuesta, se renderiza a la vista "index.jsp"
        rd = request.getRequestDispatcher("/Productos.jsp");
        rd.forward(request, response);
    }
}
