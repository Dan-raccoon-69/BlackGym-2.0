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
    private String imageUrl;
    private String image_url;
    private String Categoria;
    private String Material;
    private String Modelo;
    private String peso;

    public String getPeso() {
	return peso;
    }

    public void setPeso(String peso) {
	this.peso = peso;
    }

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
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getImage_url() { return image_url; }
    
    public void setImage_url(String i) { this.image_url = i; }

    public String getCategoria() {
	return Categoria;
    }

    public void setCategoria(String Categoria) {
	this.Categoria = Categoria;
    }

    public String getMaterial() {
	return Material;
    }

    public void setMaterial(String Material) {
	this.Material = Material;
    }

    public String getModelo() {
	return Modelo;
    }

    public void setModelo(String Modelo) {
	this.Modelo = Modelo;
    }

    
    
    @Override
    public String toString() {
        return "Producto{" + "NumProd=" + NumProd + ", NomProd=" + NomProd + ", DesProd=" + DesProd + ", Exi=" + Exi + ", CosProdu=" + CosProdu + ", ImageURL=" + imageUrl + ", Image2url=" + image_url +  ", categoria=" + Categoria + ", modelo=" + Modelo + +'}';
    }
    
}
