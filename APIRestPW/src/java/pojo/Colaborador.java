package pojo;

public class Colaborador {
    private int idColaborador;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String curp;
    private String correo;
    private String noPersonal;
    private String password;
    private int idRol;
    private byte[] foto;
    private String fotoBase64;
    private String numeroLicencia;
    private int idSucursal;

    public Colaborador() {
    }

    public Colaborador(int idColaborador, String nombre, String apellidoPaterno, String apellidoMaterno, String curp, String correo, String noPersonal, String password, int idRol, byte[] foto, String fotoBase64, String numeroLicencia, int idSucursal) {
        this.idColaborador = idColaborador;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.correo = correo;
        this.noPersonal = noPersonal;
        this.password = password;
        this.idRol = idRol;
        this.foto = foto;
        this.fotoBase64 = fotoBase64;
        this.numeroLicencia = numeroLicencia;
        this.idSucursal = idSucursal;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCurp() {
        return curp;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNoPersonal() {
        return noPersonal;
    }

    public String getPassword() {
        return password;
    }

    public int getIdRol() {
        return idRol;
    }

    public byte[] getFoto() {
        return foto;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNoPersonal(String noPersonal) {
        this.noPersonal = noPersonal;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    
}
