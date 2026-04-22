package pojo;

public class Sucursal {
    private int idSucursal;
    private String codigo;
    private String nombre;
    private int idEstatus;
    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    private String ciudad;
    private String estado;

    public Sucursal() {
    }

    public Sucursal(int idSucursal, String codigo, String nombre, int idEstatus, String calle, String numero, String colonia, String codigoPostal, String ciudad, String estado) {
        this.idSucursal = idSucursal;
        this.codigo = codigo;
        this.nombre = nombre;
        this.idEstatus = idEstatus;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdEstatus() {
        return idEstatus;
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

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
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

    
}
