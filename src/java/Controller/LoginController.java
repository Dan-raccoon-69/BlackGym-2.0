package Controller;

import Modelo.Usuario;
import dao.UsuarioDao;
import java.io.IOException;
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
public class LoginController extends HttpServlet {

    private Usuario obtenerUsuario(HttpServletRequest request) {
        String user = request.getParameter("usuario");
        String password = request.getParameter("contrasena");
        UsuarioDao usuarioDao = new UsuarioDao();
        return usuarioDao.login(user, password);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("u1") != null) {
            String action = request.getParameter("action");

            if ("irAdministracion".equals(action)) {
                RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
                rd.forward(request, response);
            }
            else if("irPlanes".equals(action)){
                RequestDispatcher rd = request.getRequestDispatcher("/planes.jsp");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario u1 = obtenerUsuario(request);
        RequestDispatcher rd;

        if (u1.getId() > 0) {
            session.setAttribute("u1", u1);
            System.out.println(u1.toString());
            rd = request.getRequestDispatcher("/admin.jsp");
            rd.forward(request, response);
        } else {
            String mensaje = "Usuario y/o Contraseña incorrectos";
            request.setAttribute("mensaje", mensaje);
            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }
}

