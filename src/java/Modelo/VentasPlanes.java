/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class VentasPlanes {
    private int NumVenta;
    private int fol;
    private int Num_Plan;
    private Double CosP;
    private Date FecV;
    private LocalTime Hor;
    private String ForP;

    public VentasPlanes(int NumVenta) {
        this.NumVenta = NumVenta;
    } 
    
    public int getNumVenta() {
        return NumVenta;
    }

    public void setNumVenta(int NumVenta) {
        this.NumVenta = NumVenta;
    }

    public int getFol() {
        return fol;
    }

    public void setFol(int fol) {
        this.fol = fol;
    }

    public int getNum_Plan() {
        return Num_Plan;
    }

    public void setNum_Plan(int Num_Plan) {
        this.Num_Plan = Num_Plan;
    }

    public Double getCosP() {
        return CosP;
    }

    public void setCosP(Double CosP) {
        this.CosP = CosP;
    }

    public Date getFecV() {
        return FecV;
    }

    public void setFecV(Date FecV) {
        this.FecV = FecV;
    }

    public LocalTime getHor() {
        return Hor;
    }

    public void setHor(LocalTime Hor) {
        this.Hor = Hor;
    }

    public String getForP() {
        return ForP;
    }

    public void setForP(String ForP) {
        this.ForP = ForP;
    }

    @Override
    public String toString() {
        return "VentasPlanes{" + "NumVenta=" + NumVenta + ", fol=" + fol + ", Num_Plan=" + Num_Plan + ", CosP=" + CosP + ", FecV=" + FecV + ", Hor=" + Hor + ", ForP=" + ForP + '}';
    }
    
}
