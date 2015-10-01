/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Altipoitem;
import entidades.Compra;
import entidades.Pedido;

/**
 *
 * @author Cesar
 */
public class PealtipoitemDTO {
    private Integer cantidad;
    private Double costoUni;
    private Integer estado;
    private Altipoitem altipoitem;
    private Compra idcompra;
    private Pedido pedido;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCostoUni() {
        return costoUni;
    }

    public void setCostoUni(Double costoUni) {
        this.costoUni = costoUni;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Altipoitem getAltipoitem() {
        return altipoitem;
    }

    public void setAltipoitem(Altipoitem altipoitem) {
        this.altipoitem = altipoitem;
    }

    public Compra getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Compra idcompra) {
        this.idcompra = idcompra;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
