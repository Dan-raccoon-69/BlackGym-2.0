/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Planes;
import Modelo.Producto;
import Modelo.Socio;
import Modelo.Ventas;
import Modelo.VentasPlanes;
import dao.ProductoDao;
import dao.VentasDao;
import dao.VentasPlanesDao;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.ServletOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dao.PlanesDao;
import dao.SociosDao;

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
            case "descargarTicket":
                String numVenta = request.getParameter("numVenta");

                VentasPlanesDao ventasPlanesDao = new VentasPlanesDao();
                // Obtener la venta por el número de venta (numVenta) desde tu lógica de negocio
                VentasPlanes venta = ventasPlanesDao.obtenerVentaPorNumero(numVenta);
                int FolSocio = Integer.parseInt(request.getParameter("Fol"));
                // Configurar la respuesta para la descarga
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=Ticket_Venta_" + numVenta + ".pdf");

                // Escribir el contenido del ticket en el flujo de salida de la respuesta
                try (ServletOutputStream outputStream = response.getOutputStream()) {
                    generatePdf(outputStream, venta, FolSocio);
                }
                break;
            case "enviarPorCorreo":

                break;
            case "descargarTicketProductos":
                String FolV = request.getParameter("numVenta");

                VentasDao ventasDao = new VentasDao();
                // Obtener la venta por el número de venta (numVenta) desde tu lógica de negocio
                Ventas venta2 = ventasDao.obtenerVentaProductosPorNumero(FolV);
                // Configurar la respuesta para la descarga
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=Ticket_Venta_Productos" + FolV + ".pdf");
                int folV = Integer.parseInt(request.getParameter("numVenta"));
                // Escribir el contenido del ticket en el flujo de salida de la respuesta
                try (ServletOutputStream outputStream = response.getOutputStream()) {
                    generatePdfProductos(outputStream, venta2, folV);
                }
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
            //String CorElecParametro = request.getParameter("CorElecParametro");
            //request.setAttribute("CorElecParametro", CorElecParametro);
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

    private void generatePdf(OutputStream outputStream, VentasPlanes venta, int FolSocio) throws IOException {
        Document document = new Document(PageSize.A6, 20, 20, 20, 20); // Tamaño de página A6

        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            // Añadir el logo de la empresa
            String absolutePath = getServletContext().getRealPath("/WEB-INF/logo2.png");
            Image logo = Image.getInstance(absolutePath);
            // Ajustar el tamaño de la imagen
            logo.scaleToFit(80, 80); // Ajusta los valores según sea necesario

            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);

            // Dirección
            Font addressFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.GRAY);
            Paragraph address = new Paragraph("Carr. Federal Pachuca - Mexico 68, Fuentes de San Cristobal, 55040 Ecatepec de Morelos, Méx.", addressFont);
            address.setAlignment(Element.ALIGN_CENTER);
            document.add(address);

            // Añadir un título al documento
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
            Paragraph title = new Paragraph("Ticket de Venta", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Agregar contenido al PDF
            document.add(new Paragraph("Venta - Número: " + venta.getNumVenta()));

            // Datos de la venta
            document.add(new Paragraph("Fecha Venta: " + venta.getFecV() + "   Hora: " + venta.getHor()));
            document.add(new Paragraph("Folio del Socio: " + venta.getFol()));
            PlanesDao planesDao = new PlanesDao();
            Planes plan = planesDao.obtenerPlanPorNumero(venta.getNum_Plan());
            String nomPlan = plan.getNom();
            document.add(new Paragraph("Número de Plan: " + venta.getNum_Plan() + " - " + nomPlan));
            document.add(new Paragraph("Precio: $" + venta.getCosP()));
            document.add(new Paragraph("Forma de Pago: " + venta.getForP()));

            // Datos del socio
            SociosDao socioDao = new SociosDao();
            Socio socio = socioDao.obtenerSocioPorFolio(FolSocio);
            document.add(new Paragraph("Socio: " + socio.getNom()));
            document.add(new Paragraph("Inicio Plan: " + socio.getInp()));
            document.add(new Paragraph("Fin de Plan: " + socio.getFip()));

            Font addressFont2 = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.GRAY);
            Paragraph address2 = new Paragraph("\n\n ¡GRACIAS POR SU COMPRA!", addressFont2);
            address2.setAlignment(Element.ALIGN_CENTER);
            document.add(address2);

            // Puedes añadir más elementos al documento según tus necesidades
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
            outputStream.close();
        }
    }

    private void generatePdfProductos(OutputStream outputStream, Ventas venta, int FolV) throws IOException {
        Document document = new Document(PageSize.A6, 15, 15, 15, 15); // Tamaño de página A6

        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            // Añadir el logo de la empresa
            String absolutePath = getServletContext().getRealPath("/WEB-INF/logo2.png");
            Image logo = Image.getInstance(absolutePath);
            // Ajustar el tamaño de la imagen
            logo.scaleToFit(80, 80); // Ajusta los valores según sea necesario

            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);

            // Dirección
            Font addressFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.GRAY);
            Paragraph address = new Paragraph("Carr. Federal Pachuca - Mexico 68, Fuentes de San Cristobal, 55040 Ecatepec de Morelos, Méx.", addressFont);
            address.setAlignment(Element.ALIGN_CENTER);
            document.add(address);

            // Añadir un título al documento
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
            Paragraph title = new Paragraph("Ticket de Venta", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Agregar contenido al PDF
            document.add(new Paragraph("Venta - Número: " + venta.getFolV()));
            document.add(new Paragraph("Fecha Venta: " + venta.getFecV() + "   Hora: " + venta.getHor()));
            String descripV = venta.getDesV();
            // Eliminar espacios y saltos de línea al principio y al final del String
            String trimmedInput = descripV.trim();
            trimmedInput = trimmedInput.trim();
            // Eliminar todas las comas de la cadena
            trimmedInput = trimmedInput.replaceAll(",", "");
            // Reemplazar múltiples saltos de línea consecutivos con uno solo
            String result = trimmedInput.replaceAll("(\n\\s*)+", "\n");
            document.add(new Paragraph("\nProductos:" + "\n" + result));
            document.add(new Paragraph("\nPrecio: $" + venta.getCosV()));
            document.add(new Paragraph("Forma de Pago: " + venta.getForP()));

            Font addressFont2 = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.GRAY);
            Paragraph address2 = new Paragraph("\n\n ¡GRACIAS POR SU COMPRA!", addressFont2);
            address2.setAlignment(Element.ALIGN_CENTER);
            document.add(address2);

            // Puedes añadir más elementos al documento según tus necesidades
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
            outputStream.close();
        }
    }

}
