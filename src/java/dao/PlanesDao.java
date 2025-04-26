/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Planes;
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
public class PlanesDao {

    public static final String url = "jdbc:mysql://localhost:3306/gym";
    public static final String usuario = "root";
    
    /*public static final String contraseña = "616263646566676869";*/
    public static final String contraseña = "";

    PreparedStatement ps;
    ResultSet rs;

    public boolean insertar(Planes plan) {
        String sql = "insert into Planes (Nom, P) values (?, ?)";

        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, plan.getNom());
            ps.setInt(2, plan.getP());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Planes obtenerPlanPorNumero(int numPlan) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM Planes WHERE NumPlan = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numPlan);
            rs = ps.executeQuery();

            if (rs.next()) {
                Planes plan = new Planes(0);
                plan.setNumPlan(rs.getInt("NumPlan"));
                plan.setNom(rs.getString("Nom"));
                plan.setP(rs.getInt("P"));
                return plan;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // Devuelve null si no se encuentra ningún plan con el número dado
    }
    
    public int borrarPlan(int idPlan){
        String sql = "delete from Planes where NumPlan=?";
        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,idPlan);
            // ExecuteUpdate se utiliza para insertar, eliminar o modificar
            int r = ps.executeUpdate();
            return r;
        } catch (SQLException ex) {
            System.out.println("Error PlanesDao.eliminar" + ex.getMessage());
            return 0;
        }
    }

    public boolean actualizarPlan(Planes plan) {
        String sql = "UPDATE Planes SET Nom=?, P=? WHERE NumPlan=?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, plan.getNom());
            ps.setInt(2, plan.getP());
            ps.setInt(3, plan.getNumPlan());

            int filasActualizadas = ps.executeUpdate();

            return filasActualizadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

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

    public List<Planes> obtenerTodosLosPlanes() {
        try {
            Connection conn = getConnection();
            String sql = "select * from Planes order by NumPlan asc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Planes> list = new LinkedList<>();
            Planes plan;
            while (rs.next()) {
                plan = new Planes(rs.getInt("NumPlan"));
                plan.setNom(rs.getString("Nom"));
                plan.setP(rs.getInt("P"));
                list.add(plan);
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<Planes> buscarPlan(String planAbuscar) {
        try {
            Connection conn = getConnection();
            String sql = "select * from Planes where Nom like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + planAbuscar + "%");
            rs = ps.executeQuery();
            List<Planes> planEncontrado = new LinkedList<>();
            Planes plan;
            while (rs.next()) {
                plan = new Planes(rs.getInt("NumPlan"));
                plan.setNom(rs.getString("Nom"));
                plan.setP(rs.getInt("P"));
                planEncontrado.add(plan);
            }
            return planEncontrado;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
