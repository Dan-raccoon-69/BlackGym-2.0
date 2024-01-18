/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Planes;
import dao.PlanesDao;
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
public class PlanesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String mensaje = "";

        switch (action) {
            case "inicio":
                rd = request.getRequestDispatcher("homePage.jsp");
                rd.forward(request, response);
                break;
            case "verPlanes":
                this.verTodosLosPlanes(request, response);
                break;
            case "modificar":
                // Obtener el número de plan a modificar desde los parámetros de la solicitud
                int numPlan = Integer.parseInt(request.getParameter("NumPlan"));

                // Obtener el plan de la base de datos usando el PlanDao
                PlanesDao planesDao = new PlanesDao();
                Planes plan = planesDao.obtenerPlanPorNumero(numPlan);

                // Agregar el plan al request para que la vista pueda acceder a él
                request.setAttribute("plan", plan);

                // Redirigir a la página de modificación
                RequestDispatcher dispatcher = request.getRequestDispatcher("/modificarPlan.jsp");
                dispatcher.forward(request, response);
                break;
            case "eliminar":
                if (session.getAttribute("u1") == null) {
                    mensaje = "Acceso Denegado.";
                    request.setAttribute("mensaje", mensaje);
                    rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                } else {
                    this.eliminarPlan(request, response);
                }
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private void eliminarPlan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPlan = Integer.parseInt(request.getParameter("NumPlan"));
        PlanesDao pla = new PlanesDao();
        int respuesta = pla.borrarPlan(idPlan);
        String mensaje = "";
        if (respuesta == 1) {
            mensaje = "El Plan fue eliminado correctamente.";
            System.out.println(mensaje);
        } else {
            mensaje = "El Plan no fue eliminado correctamente.";
            System.out.println(mensaje);
        }
        verTodosLosPlanes(request, response);
    }

    protected void verTodosLosPlanes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Planes> todas = new LinkedList<>();
        PlanesDao plan = new PlanesDao();
        todas = plan.obtenerTodosLosPlanes();
        RequestDispatcher rd;
        // compartimos la variable todas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        rd = request.getRequestDispatcher("/planes.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insertar".equals(action)) {
            String nombreParametro = request.getParameter("nombre");
            String precioParametro = request.getParameter("precio");
            int precio = Integer.parseInt(precioParametro);

            Planes planNuevo = new Planes(0);
            planNuevo.setNom(nombreParametro);
            planNuevo.setP(precio);

            PlanesDao planDao = new PlanesDao();
            boolean resultado = planDao.insertar(planNuevo);

            String mensaje = "";
            if (resultado) {
                mensaje = "El plan fue insertado correctamente.";
                System.out.println(mensaje);
            } else {
                mensaje = "Ocurrió un error, el plan no fue insertado.";
                System.out.println(mensaje);
            }
            verTodosLosPlanes(request, response);

        } else if ("modificar".equals(action)) {
            int numPlan = Integer.parseInt(request.getParameter("NumPlan"));
            String nombreParametro = request.getParameter("Nom");
            int precio = Integer.parseInt(request.getParameter("P"));

            Planes planModificado = new Planes(numPlan);
            planModificado.setNom(nombreParametro);
            planModificado.setP(precio);

            PlanesDao planDao = new PlanesDao();
            boolean resultado = planDao.actualizarPlan(planModificado);

            String mensaje = "";
            if (resultado) {
                mensaje = "El plan fue modificado correctamente.";
                System.out.println(mensaje);
            } else {
                mensaje = "Ocurrió un error, el plan no fue modificado.";
                System.out.println(mensaje);
            }
            verTodosLosPlanes(request, response);
        }
    }

}
