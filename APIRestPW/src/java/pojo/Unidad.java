package pojo;

public class Unidad {

    private int idUnidad;
    private String marca;
    private String modelo;
    private int anio;
    private String vin;
    private int idTipoUnidad;
    private String numIdenInter;
    
    private String estatus;
    private String motivoBaja;
    
    private Integer idConductor;

    public Unidad() {
    }

    public Unidad(int idUnidad, String marca, String modelo, int anio, String vin, int idTipoUnidad, String numIdenInter, String estatus, String motivoBaja, Integer idConductor) {
        this.idUnidad = idUnidad;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.vin = vin;
        this.idTipoUnidad = idTipoUnidad;
        this.numIdenInter = numIdenInter;
        this.estatus = estatus;
        this.motivoBaja = motivoBaja;
        this.idConductor = idConductor;
    }
    
    public int getIdUnidad() {
        return idUnidad;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public String getVin() {
        return vin;
    }

    public int getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public String getNumIdenInter() {
        return numIdenInter;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setIdTipoUnidad(int idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public void setNumIdenInter(String numIdenInter) {
        this.numIdenInter = numIdenInter;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }
    
    public Integer getIdConductor() {
        return idConductor;
    }
    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }
}
