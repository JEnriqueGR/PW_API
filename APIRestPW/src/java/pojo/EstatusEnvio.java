/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author cocoq
 */
public class EstatusEnvio {
    private int idEstatus;
    private String estatus;

    public EstatusEnvio() {
    }

    public EstatusEnvio(int idEstatus, String estatus) {
        this.idEstatus = idEstatus;
        this.estatus = estatus;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
}
