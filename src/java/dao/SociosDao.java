/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Socio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SociosDao {

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

    public List<Socio> obtenerTodosLosSocios() {
        try {
            Connection conn = getConnection();
            String sql = "select * from socios order by fol asc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Socio> list = new LinkedList<>();
            Socio socio;
            while (rs.next()) {
                socio = new Socio(rs.getInt("fol"));
                socio.setNom(rs.getString("Nom"));
                socio.setEda(rs.getString("Eda"));
                socio.setTel(rs.getString("Tel"));
                socio.setCorElec(rs.getString("CorElec"));
                socio.setCal(rs.getString("Cal"));
                socio.setNum(rs.getInt("Num"));
                socio.setCol(rs.getString("Col"));
                socio.setCp(rs.getString("Cp"));
                socio.setEnt(rs.getString("Ent"));
                socio.setEst(rs.getString("Est"));
                socio.setNumPlan(rs.getInt("NumPlan"));
                socio.setInp(rs.getDate("Inp"));
                socio.setFip(rs.getDate("FiP"));

                list.add(socio);
            }
            System.out.println("SOCIOS DAO");
            for (Socio socio1 : list) {
                System.out.println(socio1.toString() + " ");
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }

    public Socio obtenerSocioPorFolio(int numFol) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM socios WHERE fol = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numFol);
            rs = ps.executeQuery();

            if (rs.next()) {
                Socio socio = new Socio(0);
                socio.setFol(rs.getInt("fol"));
                socio.setNom(rs.getString("Nom"));
                socio.setEda(rs.getString("Eda"));
                socio.setTel(rs.getString("Tel"));
                socio.setCorElec(rs.getString("CorElec"));
                socio.setCal(rs.getString("Cal"));
                socio.setNum(rs.getInt("Num"));
                socio.setCol(rs.getString("Col"));
                socio.setCp(rs.getString("Cp"));
                socio.setEnt(rs.getString("Ent"));
                socio.setEst(rs.getString("Est"));
                socio.setNumPlan(rs.getInt("NumPlan"));
                socio.setInp(rs.getDate("Inp"));
                socio.setFip(rs.getDate("FiP"));
                return socio;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // Devuelve null si no se encuentra ningún socio con el folio dado
    }

    public int borrarSocio(int fol) {
        String sql = "delete from socios where fol=?";
        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fol);
            // ExecuteUpdate se utiliza para insertar, eliminar o modificar
            int r = ps.executeUpdate();
            return r;
        } catch (SQLException ex) {
            System.out.println("Error SocioDao.borrarSocio" + ex.getMessage());
            return 0;
        }
    }

    public boolean actualizarSocio(Socio socio) {
        String sql = "UPDATE socios SET Nom=?, Eda=?, Tel=?, CorElec=?, Cal=?, Num=?, Col=?, Cp=?, Ent=?, Est=?, NumPlan=?, Inp=?, FiP=? WHERE fol = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, socio.getNom());
            ps.setString(2, socio.getEda());
            ps.setString(3, socio.getTel());
            ps.setString(4, socio.getCorElec());
            ps.setString(5, socio.getCal());
            ps.setInt(6, socio.getNum());
            ps.setString(7, socio.getCol());
            ps.setString(8, socio.getCp());
            ps.setString(9, socio.getEnt());
            ps.setString(10, socio.getEst());
            ps.setInt(11, socio.getNumPlan());

            // Obtén las fechas de tu objeto socioModificado
            Date fechaInp = socio.getInp();
            Date fechaFip = socio.getFip();

            // Convierte las fechas a java.sql.Date
            java.sql.Date sqlFechaInp = new java.sql.Date(fechaInp.getTime());
            java.sql.Date sqlFechaFip = new java.sql.Date(fechaFip.getTime());

            // Luego, establece las fechas en el PreparedStatement
            ps.setDate(12, sqlFechaInp);
            ps.setDate(13, sqlFechaFip);

            //ps.setDate(13, (java.sql.Date) socio.getFip());
            ps.setInt(14, socio.getFol());

            int filasActualizadas = ps.executeUpdate();

            return filasActualizadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean insertar(Socio socio) {
        String sql = "insert into socios (Nom, Eda, Tel, CorElec, NumPlan, Inp, FiP) values (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, socio.getNom());
            ps.setString(2, socio.getEda());
            ps.setString(3, socio.getTel());
            ps.setString(4, socio.getCorElec());
            ps.setInt(5, socio.getNumPlan());

            // Obtén las fechas de tu objeto socioModificado
            Date fechaInp = socio.getInp();
            Date fechaFip = socio.getFip();

            // Convierte las fechas a java.sql.Date
            java.sql.Date sqlFechaInp = new java.sql.Date(fechaInp.getTime());
            java.sql.Date sqlFechaFip = new java.sql.Date(fechaFip.getTime());

            // Luego, establece las fechas en el PreparedStatement
            ps.setDate(6, sqlFechaInp);
            ps.setDate(7, sqlFechaFip);

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Socio> buscarSocio(String socioAbuscar) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM socios WHERE Nom LIKE ? ORDER BY fol ASC";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + socioAbuscar + "%");
            rs = ps.executeQuery();
            List<Socio> socioEncontrado = new LinkedList<>();
            Socio socio;
            while (rs.next()) {
                socio = new Socio(rs.getInt("fol"));
                socio.setNom(rs.getString("Nom"));
                socio.setEda(rs.getString("Eda"));
                socio.setTel(rs.getString("Tel"));
                socio.setCorElec(rs.getString("CorElec"));
                socio.setCal(rs.getString("Cal"));
                socio.setNum(rs.getInt("Num"));
                socio.setCol(rs.getString("Col"));
                socio.setCp(rs.getString("Cp"));
                socio.setEnt(rs.getString("Ent"));
                socio.setEst(rs.getString("Est"));
                socio.setNumPlan(rs.getInt("NumPlan"));
                socio.setInp(rs.getDate("Inp"));
                socio.setFip(rs.getDate("FiP"));
                socioEncontrado.add(socio);
            }
            return socioEncontrado;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public int obtenerFolPorNombre(String nombreSocio) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT fol FROM socios WHERE Nom = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombreSocio);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("fol");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1; // Devuelve -1 si no se encuentra ningún socio con el nombre dado
    }

}
