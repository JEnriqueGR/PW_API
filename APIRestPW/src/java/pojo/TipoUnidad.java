/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author cocoq
 */
public class TipoUnidad {
    private int idTipoUnidad;
    private String tipo;

    public TipoUnidad() {
    }

    public TipoUnidad(int idTipoUnidad, String tipo) {
        this.idTipoUnidad = idTipoUnidad;
        this.tipo = tipo;
    }

    public int getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setIdTipoUnidad(int idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
