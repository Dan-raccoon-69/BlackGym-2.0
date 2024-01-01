/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Daniel
 */
public class Usuario {
    private int id;
    private String Clav;
    private String Cont;
    private String Nom;
    private String ApePa;
    private String Corr;

    public Usuario(int id) {
        this.id = id;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClav() {
        return Clav;
    }

    public void setClav(String Clav) {
        this.Clav = Clav;
    }

    public String getCont() {
        return Cont;
    }

    public void setCont(String Cont) {
        this.Cont = Cont;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getApePa() {
        return ApePa;
    }

    public void setApePa(String ApePa) {
        this.ApePa = ApePa;
    }

    public String getCorr() {
        return Corr;
    }

    public void setCorr(String Corr) {
        this.Corr = Corr;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", Clav=" + Clav + ", Cont=" + Cont + ", Nom=" + Nom + ", ApePa=" + ApePa + ", Corr=" + Corr + '}';
    }

    
}
