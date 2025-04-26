/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Ventas;
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
public class VentasDao {
    public static final String url = "jdbc:mysql://localhost:3306/gym";
    public static final String usuario = "root";
    /*public static final String contraseña = "616263646566676869";*/
    public static final String contraseña = "";

    PreparedStatement ps;
    ResultSet rs;
    
    public boolean insertar(Ventas venta) {
        String sql = "insert into ventas (CanP, DesV, CosV, FecV, Hor, ForP) values (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, venta.getCanP());
            ps.setString(2, venta.getDesV());
            ps.setDouble(3, venta.getCosV());
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
    
    public List<Ventas> obtenerTodasLasVentas() {
        try {
            Connection conn = getConnection();
            String sql = "select * from Ventas order by FolV asc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Ventas> list = new LinkedList<>();
            Ventas venta;
            while (rs.next()) {
                venta = new Ventas(rs.getInt("FolV"));
                venta.setCanP(Integer.parseInt(rs.getString("CanP")));
                venta.setDesV(rs.getString("DesV"));
                venta.setCosV(Double.valueOf(rs.getString("CosV")));
                venta.setFecV(rs.getDate("FecV"));
                venta.setHor(rs.getTime("Hor").toLocalTime());
                venta.setForP(rs.getString("ForP"));
                list.add(venta);
            }
            System.out.println("Ventas DAO");
            for (Ventas socio1 : list) {
                System.out.println(socio1.toString() + " ");
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Ventas obtenerVentaProductosPorNumero(String FolV) {
        try {
            Connection conn = getConnection();
            String sql = "select * from Ventas where FolV = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, FolV);
            rs = ps.executeQuery();

            if (rs.next()) {
                Ventas venta = new Ventas(rs.getInt("FolV"));
                venta = new Ventas(rs.getInt("FolV"));
                venta.setCanP(Integer.parseInt(rs.getString("CanP")));
                venta.setDesV(rs.getString("DesV"));
                venta.setCosV(Double.valueOf(rs.getString("CosV")));
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
