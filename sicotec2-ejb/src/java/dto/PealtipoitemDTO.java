/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Altipoitem;
import entidades.Compra;
import entidades.Pedido;
import entidades.Requerimientos;

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
    private Integer idalmacen;
    private Requerimientos requerimientos;
    
    private String nombreItems;

    //extras
     private Integer cantidadItem;
     private Double precioItem;
     private String nombreEstado;
     private Double total;
     
     
     
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

    public String getNombreItems() {
        return nombreItems;
    }

    public void setNombreItems(String nombreItems) {
        this.nombreItems = nombreItems;
    }

    public Integer getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
    }

    public Integer getCantidadItem() {
        return cantidadItem;
    }

    public void setCantidadItem(Integer cantidadItem) {
        this.cantidadItem = cantidadItem;
    }

    public Double getPrecioItem() {
        return precioItem;
    }

    public void setPrecioItem(Double precioItem) {
        this.precioItem = precioItem;
    }

    public Requerimientos getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(Requerimientos requerimientos) {
        this.requerimientos = requerimientos;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
