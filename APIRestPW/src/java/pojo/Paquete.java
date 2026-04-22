package pojo;

public class Paquete {
    private int idPaquete;
    private int idEnvio;
    private String descripcion;
    private double peso;
    private double alto;
    private double ancho;
    private double profundidad;

    public Paquete() {
    }

    public Paquete(int idPaquete, int idEnvio, String descripcion, double peso, double alto, double ancho, double profundidad) {
        this.idPaquete = idPaquete;
        this.idEnvio = idEnvio;
        this.descripcion = descripcion;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public double getAlto() {
        return alto;
    }

    public double getAncho() {
        return ancho;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }
    
    
}
