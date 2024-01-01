// AutocompleteController.java
package Controller;

import com.google.gson.Gson;
import dao.SociosDao;
import Modelo.Socio;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AutocompleteController", urlPatterns = {"/AutocompleteController"})
public class AutocompleteController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén la cadena de búsqueda desde la solicitud
        String query = request.getParameter("query");

        // Realiza la búsqueda en la base de datos
        SociosDao dao = new SociosDao();
        List<Socio> sugerencias = dao.buscarSocio(query);

        // Convierte las sugerencias a una cadena JSON y envíala como respuesta
        String jsonResponse = convertirSugerenciasAJson(sugerencias);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    // Modifica el método convertirSugerenciasAJson en AutocompleteController.java
    private String convertirSugerenciasAJson(List<Socio> sugerencias) {
        // Usa Gson para convertir directamente la lista de objetos Socio a JSON
        Gson gson = new Gson();
        return gson.toJson(sugerencias);
    }
}
