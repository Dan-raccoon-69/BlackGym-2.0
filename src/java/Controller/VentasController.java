/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Producto;
import Modelo.Ventas;
import Modelo.VentasPlanes;
import dao.ProductoDao;
import dao.VentasDao;
import dao.VentasPlanesDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VentasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String action2 = request.getParameter("action");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String mensaje = "";
        switch (action) {
            case "agregarVentas":
                /*
                session = request.getSession(true);
                List<String> carrito = (List<String>) session.getAttribute("carrito");
                List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");
                carrito.clear();
                productosCarrito.clear();
                */
                this.verTodosLosProductos(request, response);
                LocalDate fechaActual = LocalDate.now();
                // Colocar la lista en el alcance de solicitud  
                /*
                int cantidadProductos = 0;
                String carrito = "";
                double precio = 0;
                
                
                request.setAttribute("cantidadProductos", cantidadProductos);
                request.setAttribute("carrito", carrito);
                request.setAttribute("precio", precio);
                 */
                request.setAttribute("fechaActual", fechaActual);
                // Redirigir a la página de agregar
                rd = request.getRequestDispatcher("/ventas.jsp");
                rd.forward(request, response);
                break;
            // verReporteProductos
            case "verReporte":
                verTodasLasVentas(request, response);
                // Redirigir a la página de agregar
                rd = request.getRequestDispatcher("/reportes.jsp");
                rd.forward(request, response);
                break;
            case "verReportePlanes":
                verTodasLasVentasDePlanes(request, response);
                // Redirigir a la página de agregar
                rd = request.getRequestDispatcher("/reportesPlanes.jsp");
                rd.forward(request, response);
                break;
            case "fechas":
                verTodasLasVentas(request, response);
                // Redirigir a la página de agregar
                rd = request.getRequestDispatcher("/reportes.jsp");
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

            String cantidadProductosParametro = request.getParameter("CanP");
            String descripcionVentaParametro = request.getParameter("DesV");
            String costoVentaParametro = request.getParameter("CosV");
            String fechaVentaParametro = request.getParameter("FecV");
            //String horaVentaParametro = request.getParameter("Hor");
            String formaPagoParametro = request.getParameter("ForP");

            // Realizar la conversión de tipos según sea necesario
            int cantidadProductos = Integer.parseInt(cantidadProductosParametro);
            double costoVenta = Double.parseDouble(costoVentaParametro);
            LocalTime horaActual = LocalTime.now();

            // Crear un objeto Ventas y establecer los valores
            Ventas venta = new Ventas(0);
            venta.setCanP(cantidadProductos);
            venta.setDesV(descripcionVentaParametro);
            venta.setCosV(costoVenta);
            venta.setFecV(java.sql.Date.valueOf(fechaVentaParametro));
            venta.setHor(horaActual);
            venta.setForP(formaPagoParametro);

            VentasDao ventaDao = new VentasDao();
            boolean resultado = ventaDao.insertar(venta);

            String mensaje = "";
            if (resultado) {
                mensaje = "La venta fue agregada correctamente.";
                System.out.println(mensaje);
                HttpSession session;
                session = request.getSession(true);
                List<String> carrito = (List<String>) session.getAttribute("carrito");
                List<Producto> productosCarrito = (List<Producto>) session.getAttribute("productosCarrito");
                carrito.clear();
                productosCarrito.clear();
                
            } else {
                mensaje = "Ocurrió un error, la venta no fue agregada.";
                System.out.println(mensaje);
            }
            verTodasLasVentas(request, response);
        } else if ("Ver".equals(action)) {
            RequestDispatcher rd;
            String fechaIni = request.getParameter("fechaIni");
            String fechaFin = request.getParameter("fechaFin");

            LocalDate fechaI = LocalDate.parse(fechaIni);
            LocalDate fechaF = LocalDate.parse(fechaFin);

            List<Ventas> ventasTotales = new LinkedList<>();
            List<Ventas> ventasFiltradas = new LinkedList<>();
            VentasDao venta = new VentasDao();
            ventasTotales = venta.obtenerTodasLasVentas();
            Double costosEfe = 0.0;
            Double costosTarDeb = 0.0;
            Double costosTarCre = 0.0;
            Double costosTot = 0.0;
            for (Ventas ventas : ventasTotales) {
                // Asegúrate de que toda.getFecV() devuelve un java.sql.Date
                java.sql.Date fechaVenta = (java.sql.Date) ventas.getFecV();
                // Convertir la fecha de la venta a LocalDate
                LocalDate fechaVentaLocalDate = fechaVenta.toLocalDate();

                if (fechaVentaLocalDate.isAfter(fechaI) && fechaVentaLocalDate.isBefore(fechaF)) {
                    ventasFiltradas.add(ventas);
                    if (ventas.getForP().equals("Efectivo")) {
                        costosEfe += ventas.getCosV();
                    } else if (ventas.getForP().equals("Tarjeta Debito")) {
                        costosTarDeb += ventas.getCosV();
                    } else if (ventas.getForP().equals("Tarjeta Credito")) {
                        costosTarCre += ventas.getCosV();
                    }
                }
            }

            costosTot = costosEfe + costosTarDeb + costosTarCre;

            // compartimos la variable ultimas, para poder acceder la vista con Expression Language
            request.setAttribute("todas", ventasFiltradas);
            request.setAttribute("costosEfe", costosEfe);
            request.setAttribute("costosTarDeb", costosTarDeb);
            request.setAttribute("costosTarCre", costosTarCre);
            request.setAttribute("costosTot", costosTot);
            request.setAttribute("fechaI", fechaI);
            request.setAttribute("fechaF", fechaF);
            // enviamos respuesta, se renderiza a la vista "index.jsp"
            rd = request.getRequestDispatcher("/reportes.jsp");
            rd.forward(request, response);
        } else if ("insertarVentaPlan".equals(action)) {

            int folioSocio = Integer.parseInt(request.getParameter("fol"));
            String nomSocio = request.getParameter("nomSo");
            int numPlan = Integer.parseInt(request.getParameter("numPlan"));
            double CosPlan = Double.valueOf(request.getParameter("CosPlan"));
            String FecV = request.getParameter("FecV");
            String ForP = request.getParameter("ForP");
            LocalTime horaActual = LocalTime.now();

            // Crear un objeto Ventas y establecer los valores
            VentasPlanes venta = new VentasPlanes(0);
            venta.setFol(folioSocio);
            venta.setNum_Plan(numPlan);
            venta.setCosP(CosPlan);
            venta.setFecV(java.sql.Date.valueOf(FecV));
            venta.setHor(horaActual);
            venta.setForP(ForP);

            VentasPlanesDao ventaDao = new VentasPlanesDao();
            boolean resultado = ventaDao.insertar(venta);

            String mensaje = "";
            if (resultado) {
                mensaje = "La venta de plan fue agregada correctamente.";
                System.out.println(mensaje);
            } else {
                mensaje = "Ocurrió un error, la venta de plan no fue agregada.";
                System.out.println(mensaje);
            }
            verTodasLasVentasDePlanes(request, response);
        } else if ("VerVentasPlanes".equals(action)) {
            RequestDispatcher rd;
            String fechaIni = request.getParameter("fechaIni");
            String fechaFin = request.getParameter("fechaFin");

            LocalDate fechaI = LocalDate.parse(fechaIni);
            LocalDate fechaF = LocalDate.parse(fechaFin);

            List<VentasPlanes> ventasTotales = new LinkedList<>();
            List<VentasPlanes> ventasFiltradas = new LinkedList<>();
            VentasPlanesDao venta = new VentasPlanesDao();
            ventasTotales = venta.obtenerTodasLasVentasDePlanes();
            Double costosEfe = 0.0;
            Double costosTarDeb = 0.0;
            Double costosTarCre = 0.0;
            Double costosTot = 0.0;
            for (VentasPlanes ventas : ventasTotales) {
                // Asegúrate de que toda.getFecV() devuelve un java.sql.Date
                java.sql.Date fechaVenta = (java.sql.Date) ventas.getFecV();
                // Convertir la fecha de la venta a LocalDate
                LocalDate fechaVentaLocalDate = fechaVenta.toLocalDate();

                if (fechaVentaLocalDate.isAfter(fechaI) && fechaVentaLocalDate.isBefore(fechaF)) {
                    ventasFiltradas.add(ventas);
                    if (ventas.getForP().equals("Efectivo")) {
                        costosEfe += ventas.getCosP();
                    } else if (ventas.getForP().equals("Tarjeta Debito")) {
                        costosTarDeb += ventas.getCosP();
                    } else if (ventas.getForP().equals("Tarjeta Credito")) {
                        costosTarCre += ventas.getCosP();
                    }
                }
            }

            costosTot = costosEfe + costosTarDeb + costosTarCre;

            // compartimos la variable ultimas, para poder acceder la vista con Expression Language
            request.setAttribute("todas", ventasFiltradas);
            request.setAttribute("costosEfe", costosEfe);
            request.setAttribute("costosTarDeb", costosTarDeb);
            request.setAttribute("costosTarCre", costosTarCre);
            request.setAttribute("costosTot", costosTot);
            request.setAttribute("fechaI", fechaI);
            request.setAttribute("fechaF", fechaF);
            // enviamos respuesta, se renderiza a la vista "index.jsp"
            rd = request.getRequestDispatcher("/reportesPlanes.jsp");
            rd.forward(request, response);
        }

    }

    protected void verTodasLasVentas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ventas> todas = new LinkedList<>();
        VentasDao venta = new VentasDao();
        todas = venta.obtenerTodasLasVentas();
        Double costosEfe = 0.0;
        Double costosTarDeb = 0.0;
        Double costosTarCre = 0.0;
        Double costosTot = 0.0;
        for (Ventas toda : todas) {
            if (toda.getForP().equals("Efectivo")) {
                costosEfe += toda.getCosV();
            } else if (toda.getForP().equals("Tarjeta Debito")) {
                costosTarDeb += toda.getCosV();
            } else if (toda.getForP().equals("Tarjeta Credito")) {
                costosTarCre += toda.getCosV();
            }
        }
        costosTot = costosEfe + costosTarDeb + costosTarCre;
        RequestDispatcher rd;
        // compartimos la variable ultimas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        request.setAttribute("costosEfe", costosEfe);
        request.setAttribute("costosTarDeb", costosTarDeb);
        request.setAttribute("costosTarCre", costosTarCre);
        request.setAttribute("costosTot", costosTot);
        // enviamos respuesta, se renderiza a la vista "index.jsp"
        rd = request.getRequestDispatcher("/reportes.jsp");
        rd.forward(request, response);
    }

    protected void verTodasLasVentasDePlanes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<VentasPlanes> todas = new LinkedList<>();
        VentasPlanesDao venta = new VentasPlanesDao();
        todas = venta.obtenerTodasLasVentasDePlanes();
        Double costosEfe = 0.0;
        Double costosTarDeb = 0.0;
        Double costosTarCre = 0.0;
        Double costosTot = 0.0;
        for (VentasPlanes toda : todas) {
            if (toda.getForP().equals("Efectivo")) {
                costosEfe += toda.getCosP();
            } else if (toda.getForP().equals("Tarjeta Debito")) {
                costosTarDeb += toda.getCosP();
            } else if (toda.getForP().equals("Tarjeta Credito")) {
                costosTarCre += toda.getCosP();
            }
        }
        costosTot = costosEfe + costosTarDeb + costosTarCre;
        RequestDispatcher rd;
        // compartimos la variable ultimas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        request.setAttribute("costosEfe", costosEfe);
        request.setAttribute("costosTarDeb", costosTarDeb);
        request.setAttribute("costosTarCre", costosTarCre);
        request.setAttribute("costosTot", costosTot);
        // enviamos respuesta, se renderiza a la vista "index.jsp"
        rd = request.getRequestDispatcher("/reportesPlanes.jsp");
        rd.forward(request, response);
    }

    protected void verTodosLosProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> todas = new LinkedList<>();
        ProductoDao producto = new ProductoDao();
        todas = producto.obtenerTodosLosProductos();
        RequestDispatcher rd;
        // compartimos la variable ultimas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
    }

}
