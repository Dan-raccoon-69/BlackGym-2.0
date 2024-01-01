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
public class Ventas {
    private int FolV;
    private int CanP;
    private String DesV;
    private Double CosV;
    private Date FecV;
    private LocalTime hor;
    private String ForP;

    public Ventas(int FolV) {
        this.FolV = FolV;
    }
    
    public int getFolV() {
        return FolV;
    }

    public void setFolV(int FolV) {
        this.FolV = FolV;
    }

    public int getCanP() {
        return CanP;
    }

    public void setCanP(int CanP) {
        this.CanP = CanP;
    }

    public String getDesV() {
        return DesV;
    }

    public void setDesV(String DesV) {
        this.DesV = DesV;
    }

    public Double getCosV() {
        return CosV;
    }

    public void setCosV(Double CosV) {
        this.CosV = CosV;
    }

    public Date getFecV() {
        return FecV;
    }

    public void setFecV(Date FecV) {
        this.FecV = FecV;
    }

    public LocalTime getHor() {
        return hor;
    }

    public void setHor(LocalTime hor) {
        this.hor = hor;
    }

    public String getForP() {
        return ForP;
    }

    public void setForP(String ForP) {
        this.ForP = ForP;
    }

    @Override
    public String toString() {
        return "Ventas{" + "FolV=" + FolV + ", CanP=" + CanP + ", DesV=" + DesV + ", CosV=" + CosV + ", FecV=" + FecV + ", hor=" + hor + ", ForP=" + ForP + '}';
    }
    
}
