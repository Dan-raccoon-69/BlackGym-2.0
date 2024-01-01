/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class ProductoDao {
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

    public List<Producto> obtenerTodosLosProductos() {
        try {
            Connection conn = getConnection();
            String sql = "select * from Productos order by NumProd asc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Producto> list = new LinkedList<>();
            Producto producto;
            while (rs.next()) {
                producto = new Producto(rs.getInt("NumProd"));
                producto.setNomProd(rs.getString("NomProd"));
                producto.setDesProd(rs.getString("DesProd"));
                producto.setExi(rs.getInt("Exi"));
                producto.setCosProdu(rs.getDouble("CosProdu"));
                list.add(producto);
            }
            System.out.println("SOCIOS DAO");
            for (Producto prod : list) {
                System.out.println(prod.toString() + " ");
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }
}
