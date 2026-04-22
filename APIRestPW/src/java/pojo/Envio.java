package pojo;

public class Envio {
    private int idEnvio;
    private String numeroGuia;
    private int idCliente;

    private String destiNomb;
    private String destiApellidoPaterno;
    private String destiApellidoMaterno;

    private int idSucursalOrigen;

    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    private String ciudad;
    private String estado;

    private double costoTotal;

    private int idEstatus;
    private Integer idConductor;
    
    private String motivoEstatus;

    public Envio() {
    }

    public Envio(int idEnvio, String numeroGuia, int idCliente, String destiNomb, String destiApellidoPaterno, String destiApellidoMaterno, int idSucursalOrigen, String calle, String numero, String colonia, String codigoPostal, String ciudad, String estado, double costoTotal, int idEstatus, Integer idConductor) {
        this.idEnvio = idEnvio;
        this.numeroGuia = numeroGuia;
        this.idCliente = idCliente;
        this.destiNomb = destiNomb;
        this.destiApellidoPaterno = destiApellidoPaterno;
        this.destiApellidoMaterno = destiApellidoMaterno;
        this.idSucursalOrigen = idSucursalOrigen;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.estado = estado;
        this.costoTotal = costoTotal;
        this.idEstatus = idEstatus;
        this.idConductor = idConductor;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getDestiNomb() {
        return destiNomb;
    }

    public String getDestiApellidoPaterno() {
        return destiApellidoPaterno;
    }

    public String getDestiApellidoMaterno() {
        return destiApellidoMaterno;
    }

    public int getIdSucursalOrigen() {
        return idSucursalOrigen;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setDestiNomb(String destiNomb) {
        this.destiNomb = destiNomb;
    }

    public void setDestiApellidoPaterno(String destiApellidoPaterno) {
        this.destiApellidoPaterno = destiApellidoPaterno;
    }

    public void setDestiApellidoMaterno(String destiApellidoMaterno) {
        this.destiApellidoMaterno = destiApellidoMaterno;
    }

    public void setIdSucursalOrigen(int idSucursalOrigen) {
        this.idSucursalOrigen = idSucursalOrigen;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public String getMotivoEstatus() {
        return motivoEstatus;
    }

    public void setMotivoEstatus(String motivoEstatus) {
        this.motivoEstatus = motivoEstatus;
    }
    
}
