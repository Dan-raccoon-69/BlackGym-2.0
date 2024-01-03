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

    public boolean insertar(Producto producto) {
        String sql = "insert into productos (NomProd, DesProd, Exi, CosProdu) values (?, ?, ?, ?)";

        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, producto.getNomProd());
            ps.setString(2, producto.getDesProd());
            ps.setInt(3, producto.getExi());
            ps.setDouble(4, producto.getCosProdu());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET NomProd=?, DesProd=?, Exi=?, CosProdu=? WHERE NumProd = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNomProd());
            ps.setString(2, producto.getDesProd());
            ps.setInt(3, producto.getExi());
            ps.setDouble(4, producto.getCosProdu());
            ps.setDouble(5, producto.getNumProd());
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Producto obtenerProductoPorFolio(int NumProd) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM productos WHERE NumProd = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, NumProd);
            rs = ps.executeQuery();

            if (rs.next()) {
                Producto producto = new Producto(0);
                producto.setNumProd(rs.getInt("NumProd"));
                producto.setNomProd(rs.getString("NomProd"));
                producto.setDesProd(rs.getString("DesProd"));
                producto.setExi(rs.getInt("Exi"));
                producto.setCosProdu(rs.getDouble("CosProdu"));
                return producto;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // Devuelve null si no se encuentra ningún socio con el folio dado
    }

    public int obtenerExistenciasEnStock(int numProd) {
        int existenciasEnStock = 0;

        try (Connection conn = getConnection()) {
            String sql = "SELECT Exi FROM productos WHERE NumProd = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, numProd);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        existenciasEnStock = resultSet.getInt("Exi");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
        }

        return existenciasEnStock;
    }

    public void actualizarExistenciasEnStock(int numProd, int nuevasExistencias) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE productos SET Exi = ? WHERE NumProd = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, nuevasExistencias);
                statement.setInt(2, numProd);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
        }
    }

}
