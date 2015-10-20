/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Almacen;
import entidades.Tipoitem;

/**
 *
 * @author Cesar
 */
public class AltipoitemDTO {
    private Integer cantidad;
    private Integer estado;
    private Integer reservado;
    private Integer comprados;
    private Almacen almacen;
    private Tipoitem tipoitem;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getReservado() {
        return reservado;
    }

    public void setReservado(Integer reservado) {
        this.reservado = reservado;
    }

    public Integer getComprados() {
        return comprados;
    }

    public void setComprados(Integer comprados) {
        this.comprados = comprados;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public Tipoitem getTipoitem() {
        return tipoitem;
    }

    public void setTipoitem(Tipoitem tipoitem) {
        this.tipoitem = tipoitem;
    }
    
    
}
