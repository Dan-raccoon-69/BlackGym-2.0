package Controller;

import Modelo.Socio;
import dao.SociosDao;
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
public class BuscarSociosController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String socioAbuscar = request.getParameter("query");

        SociosDao soc = new SociosDao();
        List<Socio> todas = new LinkedList<>();
        todas = soc.buscarSocio(socioAbuscar);

        RequestDispatcher rd;
        // compartimos la variable msg, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        // enviamos respuesta, se renderiza a la vista "vacantes.jsp"
        rd = request.getRequestDispatcher("/VerSocios.jsp");
        rd.forward(request, response);
    }
    
    

}
