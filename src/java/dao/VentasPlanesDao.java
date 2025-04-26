/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.VentasPlanes;
import java.sql.Connection;
import java.sql.Date;
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
public class VentasPlanesDao {

    public static final String url = "jdbc:mysql://localhost:3306/gym";
    public static final String usuario = "root";
    /*public static final String contraseña = "616263646566676869";*/
    public static final String contraseña = "";

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

    public boolean insertar(VentasPlanes venta) {
        String sql = "insert into VentasPlanes (fol, Num_Plan, CosP, FecV, Hor, ForP) values (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, venta.getFol());
            ps.setInt(2, venta.getNum_Plan());
            ps.setDouble(3, venta.getCosP());
            ps.setDate(4, new Date(venta.getFecV().getTime())); // Convirtiendo LocalDate a Date
            ps.setString(5, venta.getHor().toString()); // Convirtiendo LocalTime a String
            ps.setString(6, venta.getForP());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<VentasPlanes> obtenerTodasLasVentasDePlanes() {
        try {
            Connection conn = getConnection();
            String sql = "select * from VentasPlanes order by NumVenta asc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<VentasPlanes> list = new LinkedList<>();
            VentasPlanes venta;
            while (rs.next()) {
                venta = new VentasPlanes(rs.getInt("NumVenta"));
                venta.setFol(Integer.parseInt(rs.getString("fol")));
                venta.setNum_Plan(Integer.parseInt(rs.getString("Num_Plan")));
                venta.setCosP(Double.valueOf(rs.getString("CosP")));
                venta.setFecV(rs.getDate("FecV"));
                venta.setHor(rs.getTime("Hor").toLocalTime());
                venta.setForP(rs.getString("ForP"));
                list.add(venta);
            }
            System.out.println("Ventas Planes DAO");
            for (VentasPlanes ventas : list) {
                System.out.println(ventas.toString() + " ");
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }

    public VentasPlanes obtenerVentaPorNumero(String numVenta) {
        try {
            Connection conn = getConnection();
            String sql = "select * from VentasPlanes where NumVenta = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, numVenta);
            rs = ps.executeQuery();

            if (rs.next()) {
                VentasPlanes venta = new VentasPlanes(rs.getInt("NumVenta"));
                venta.setFol(Integer.parseInt(rs.getString("fol")));
                venta.setNum_Plan(Integer.parseInt(rs.getString("Num_Plan")));
                venta.setCosP(Double.valueOf(rs.getString("CosP")));
                venta.setFecV(rs.getDate("FecV"));
                venta.setHor(rs.getTime("Hor").toLocalTime());
                venta.setForP(rs.getString("ForP"));

                return venta;
            } else {
                return null; // No se encontró la venta con el número de venta especificado
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
