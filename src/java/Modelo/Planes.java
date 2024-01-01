/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Daniel
 */
public class Planes {
    private int NumPlan;
    private String Nom;
    private int p;

    public Planes(int id) {
        this.NumPlan = id;
    }
    
    public int getNumPlan() {
        return NumPlan;
    }

    public void setNumPlan(int NumPlan) {
        this.NumPlan = NumPlan;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "Planes{" + "NumPlan=" + NumPlan + ", Nom=" + Nom + ", p=" + p + '}';
    }
   
}
