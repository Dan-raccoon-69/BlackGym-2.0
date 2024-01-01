package Modelo;

/**
 *
 * @author Daniel
 */
public class Producto {
    private int NumProd;
    private String NomProd;
    private String DesProd;
    private int Exi;
    private double CosProdu;

    public Producto(int NumProd) {
        this.NumProd = NumProd;
    }
    
    public int getNumProd() {
        return NumProd;
    }

    public void setNumProd(int NumProd) {
        this.NumProd = NumProd;
    }

    public String getNomProd() {
        return NomProd;
    }

    public void setNomProd(String NomProd) {
        this.NomProd = NomProd;
    }

    public String getDesProd() {
        return DesProd;
    }

    public void setDesProd(String DesProd) {
        this.DesProd = DesProd;
    }

    public int getExi() {
        return Exi;
    }

    public void setExi(int Exi) {
        this.Exi = Exi;
    }

    public double getCosProdu() {
        return CosProdu;
    }

    public void setCosProdu(double CosProdu) {
        this.CosProdu = CosProdu;
    }
    
    @Override
    public String toString() {
        return "Producto{" + "NumProd=" + NumProd + ", NomProd=" + NomProd + ", DesProd=" + DesProd + ", Exi=" + Exi + ", CosProdu=" + CosProdu + '}';
    }
    
}
