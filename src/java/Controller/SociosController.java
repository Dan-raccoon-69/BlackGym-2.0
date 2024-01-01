package Controller;

import Modelo.Planes;
import Modelo.Socio;
import dao.PlanesDao;
import dao.SociosDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
public class SociosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String mensaje = "";
        PlanesDao planesDao = new PlanesDao();
        List<Planes> listaPlanes = planesDao.obtenerTodosLosPlanes();
        RequestDispatcher dispatcher;
        switch (action) {
            case "inicio":
                rd = request.getRequestDispatcher("/homePage.jsp");
                rd.forward(request, response);
                break;
            case "verSocios":
                this.verTodosLosSocios(request, response);
                break;
            case "agregarSocios":
                planesDao = new PlanesDao();
                listaPlanes = planesDao.obtenerTodosLosPlanes();
                LocalDate fechaActual = LocalDate.now();
                // Colocar la lista en el alcance de solicitud
                
                request.setAttribute("listaPlanes", listaPlanes);
                request.setAttribute("fechaActual", fechaActual);
                // Redirigir a la página de modificación
                dispatcher = request.getRequestDispatcher("/agregarSocios.jsp");
                dispatcher.forward(request, response);
                break;
            case "modificar":
                // Obtener el número de socio a modificar desde los parámetros de la solicitud
                int numFol = Integer.parseInt(request.getParameter("fol"));

                // Obtener el plan de la base de datos usando el PlanDao
                SociosDao sociosDao = new SociosDao();
                Socio socio = sociosDao.obtenerSocioPorFolio(numFol);

                // Agregar el plan al request para que la vista pueda acceder a él
                request.setAttribute("socio", socio);

                planesDao = new PlanesDao();
                listaPlanes = planesDao.obtenerTodosLosPlanes();

                // Colocar la lista en el alcance de solicitud
                request.setAttribute("listaPlanes", listaPlanes);

                // Redirigir a la página de modificación
                dispatcher = request.getRequestDispatcher("/modificarSocio.jsp");
                dispatcher.forward(request, response);
                break;
            case "eliminar":
                if (session.getAttribute("u1") == null) {
                    mensaje = "Acceso Denegado.";
                    System.out.println(mensaje);
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                } else {
                    this.eliminarSocio(request, response);
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
            
            String nombreParametro = request.getParameter("Nom");
            String edadParametro = request.getParameter("Eda");
            String TelParametro = request.getParameter("Tel");
            String CorElecParametro = request.getParameter("CorElec");
            int numPlanParametro = Integer.parseInt(request.getParameter("NumPlan"));
            
            // Obtener los parámetros como String desde la solicitud
            String fechaString = request.getParameter("fecha");
            String fechaOutString = request.getParameter("fechaOut");

            // Convertir los String a objetos Date
            Date fecha = null;
            Date fechaOut = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                if (fechaString != null && !fechaString.isEmpty()) {
                    fecha = dateFormat.parse(fechaString);
                }

                if (fechaOutString != null && !fechaOutString.isEmpty()) {
                    fechaOut = dateFormat.parse(fechaOutString);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // Manejar la excepción de análisis aquí
            }
            
            Socio socioNuevo = new Socio(0);
            socioNuevo.setNom(nombreParametro);
            socioNuevo.setEda(edadParametro);
            socioNuevo.setTel(TelParametro);
            socioNuevo.setCorElec(CorElecParametro);
            socioNuevo.setNumPlan(numPlanParametro);
            socioNuevo.setFip(fechaOut);
            socioNuevo.setInp(fecha);

            SociosDao socioDao = new SociosDao();
            boolean resultado = socioDao.insertar(socioNuevo);

            String mensaje = "";
            if (resultado) {
                mensaje = "El usuario fue insertado correctamente.";
                System.out.println(mensaje);
            } else {
                mensaje = "Ocurrió un error, el usuario no fue agregado.";
                System.out.println(mensaje);
            }
            verTodosLosSocios(request, response);

        } else if ("modificar".equals(action)) {

            int Fol = Integer.parseInt(request.getParameter("fol"));
            String nombreParametro = request.getParameter("Nom");
            String edadParametro = request.getParameter("Eda");
            String TelParametro = request.getParameter("Tel");
            String CorElecParametro = request.getParameter("CorElec");
            int numPlanParametro = Integer.parseInt(request.getParameter("NumPlan"));
            //int NumParametro = Integer.parseInt(request.getParameter("Num"));
            String CalParametro = request.getParameter("Cal");
            String ColParametro = request.getParameter("Col");
            String CpParametro = request.getParameter("Cp");
            String EntParametro = request.getParameter("Ent");
            String EstParametro = request.getParameter("Est");
            
            // Obtener los parámetros como String desde la solicitud
            String fechaString = request.getParameter("fecha");
            String fechaOutString = request.getParameter("fechaOut");

            // Convertir los String a objetos Date
            Date fecha = null;
            Date fechaOut = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                if (fechaString != null && !fechaString.isEmpty()) {
                    fecha = dateFormat.parse(fechaString);
                }

                if (fechaOutString != null && !fechaOutString.isEmpty()) {
                    fechaOut = dateFormat.parse(fechaOutString);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // Manejar la excepción de análisis aquí
            }

            Socio socioModificado = new Socio(Fol);
            socioModificado.setNom(nombreParametro);
            socioModificado.setEda(edadParametro);
            socioModificado.setTel(TelParametro);
            socioModificado.setCorElec(CorElecParametro);
            socioModificado.setNumPlan(numPlanParametro);
            socioModificado.setCol(ColParametro);
            socioModificado.setCp(CpParametro);
            socioModificado.setCal(CalParametro);
            socioModificado.setEnt(EntParametro);
            socioModificado.setEst(EstParametro);
            socioModificado.setFip(fechaOut);
            socioModificado.setInp(fecha);

            SociosDao socioDao = new SociosDao();
            boolean resultado = socioDao.actualizarSocio(socioModificado);

            String mensaje = "";
            if (resultado) {
                mensaje = "El socio fue modificado correctamente.";
                System.out.println(mensaje);
            } else {
                mensaje = "Ocurrió un error, el socio no fue modificado.";
                System.out.println(mensaje);
            }
            verTodosLosSocios(request, response);
        }
    }

    private void eliminarSocio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // se recibe el id del plan que vamos a eliminar
        int idSocio = Integer.parseInt(request.getParameter("fol"));
        SociosDao soc = new SociosDao();
        int respuesta = soc.borrarSocio(idSocio);
        String mensaje = "";
        if (respuesta == 1) {
            mensaje = "El Socio fue eliminado correctamente.";
            System.out.println(mensaje);
        } else {
            mensaje = "El Socio no fue eliminado correctamente.";
            System.out.println(mensaje);
        }
        verTodosLosSocios(request, response);
    }

    protected void verTodosLosSocios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Socio> todas = new LinkedList<>();
        SociosDao socio = new SociosDao();
        todas = socio.obtenerTodosLosSocios();
        System.out.println("SOCIOS DESDE CONTROLLER");
        for (Socio toda : todas) {
            System.out.println(toda.toString());
        }
        RequestDispatcher rd;
        // compartimos la variable ultimas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        // enviamos respuesta, se renderiza a la vista "index.jsp"
        rd = request.getRequestDispatcher("/VerSocios.jsp");
        rd.forward(request, response);
    }
}
