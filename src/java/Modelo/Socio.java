/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class Socio {
    private int fol;
    private String Nom;
    private String Eda;
    private String Tel;
    private String CorElec;
    private String Cal;
    private int Num;
    private String Col;
    private String Cp;
    private String Ent;
    private String Est;
    private int NumPlan;
    private Date Inp;
    private Date Fip;

    public Socio(int fol) {
        this.fol = fol;
    }

    public int getFol() {
        return fol;
    }

    public void setFol(int fol) {
        this.fol = fol;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getEda() {
        return Eda;
    }

    public void setEda(String Eda) {
        this.Eda = Eda;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getCorElec() {
        return CorElec;
    }

    public void setCorElec(String CorElec) {
        this.CorElec = CorElec;
    }

    public String getCal() {
        return Cal;
    }

    public void setCal(String Cal) {
        this.Cal = Cal;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int Num) {
        this.Num = Num;
    }

    public String getCol() {
        return Col;
    }

    public void setCol(String Col) {
        this.Col = Col;
    }

    public String getCp() {
        return Cp;
    }

    public void setCp(String Cp) {
        this.Cp = Cp;
    }

    public String getEnt() {
        return Ent;
    }

    public void setEnt(String Ent) {
        this.Ent = Ent;
    }

    public String getEst() {
        return Est;
    }

    public void setEst(String Est) {
        this.Est = Est;
    }

    public int getNumPlan() {
        return NumPlan;
    }

    public void setNumPlan(int NumPlan) {
        this.NumPlan = NumPlan;
    }

    public Date getInp() {
        return Inp;
    }

    public void setInp(Date Inp) {
        this.Inp = Inp;
    }

    public Date getFip() {
        return Fip;
    }

    public void setFip(Date Fip) {
        this.Fip = Fip;
    }

    @Override
    public String toString() {
        return "Socio{" + "fol=" + fol + ", Nom=" + Nom + ", Eda=" + Eda + ", Tel=" + Tel + ", CorElec=" + CorElec + ", Cal=" + Cal + ", Num=" + Num + ", Col=" + Col + ", Cp=" + Cp + ", Ent=" + Ent + ", Est=" + Est + ", NumPlan=" + NumPlan + ", Inp=" + Inp + ", Fip=" + Fip + '}';
    }

    
}
