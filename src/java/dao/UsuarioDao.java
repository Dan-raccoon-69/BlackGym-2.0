package dao;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    public static final String url = "jdbc:mysql://localhost:3306/gym";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";

    PreparedStatement ps;
    ResultSet rs;

    // Método para obtener la conexión
    private Connection getConnection() {
        try {
            // Cargar el controlador JDBC (Driver)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            return conexion;
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar la excepción (imprimir o lanzar una nueva)
            e.printStackTrace();
            return null;
        }
    }

    public Usuario login(String user, String pass) {
        try {
            Connection conn = getConnection();

            if (conn == null) {
                // La conexión no se pudo establecer, manejar según sea necesario
                return null;
            }

            String sql = "select * from login where Clav=? and Cont =? limit 1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            Usuario usuario = new Usuario(0);

            while (rs.next()) {
                usuario.setId(rs.getInt("idR"));
                usuario.setClav(rs.getString("Clav"));
                usuario.setCont(rs.getString("Cont"));
                usuario.setNom(rs.getString("Nom"));
                usuario.setApePa(rs.getString("ApePa"));
                usuario.setCorr(rs.getString("Corr"));
            }

            // Cerrar la conexión y recursos después de su uso
            rs.close();
            ps.close();
            conn.close();

            return usuario;
        } catch (SQLException ex) {
            // Manejar la excepción (imprimir o lanzar una nueva)
            ex.printStackTrace();
            return null;
        }
    }
}