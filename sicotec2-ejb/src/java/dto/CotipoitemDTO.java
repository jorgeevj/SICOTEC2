/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dao.AltipoitemFacade;
import entidades.Almacen;
import entidades.Altipoitem;
import entidades.AltipoitemPK;
import entidades.Cotizacion;
import entidades.Tipoitem;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Jorge
 */
public class CotipoitemDTO {
    private AltipoitemFacade altipoitemFacade = lookupAltipoitemFacadeBean();
    
    private Double precio;
    
    private Integer cantidad;
    
    private Double descuento;
    
    private Cotizacion cotizacion;
   
    private Tipoitem tipoitem; 
    
    //EXTRAS
    private int cantidad2;
    private int stock;
    private int almacen;

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad2() {
        return cantidad2;
    }

    public void setCantidad2(int cantidad2) {
        this.cantidad2 = cantidad2;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Tipoitem getTipoitem() {
        return tipoitem;
    }

    public void setTipoitem(Tipoitem tipoitem) {
        this.tipoitem = tipoitem;
    }

    public int getStock() {
       if(cotizacion.getIdalmacen()!=null){
        Altipoitem a=new Altipoitem(new AltipoitemPK(cotizacion.getIdalmacen(), tipoitem.getIdtipoItem()));
        a.setAlmacen(new Almacen(cotizacion.getIdalmacen()));
        a.setTipoitem(tipoitem);
        a=altipoitemFacade.buscaByIDs(a).isEmpty() ? null:altipoitemFacade.buscaByIDs(a).get(0);
        if(a!=null){
        stock=a.getCantidad();
        }
       }
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    private AltipoitemFacade lookupAltipoitemFacadeBean() {
        try {
            Context c = new InitialContext();
            return (AltipoitemFacade) c.lookup("java:global/sicotec2/sicotec2-ejb/AltipoitemFacade!dao.AltipoitemFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public AltipoitemFacade getAltipoitemFacade() {
        return altipoitemFacade;
    }

    public void setAltipoitemFacade(AltipoitemFacade altipoitemFacade) {
        this.altipoitemFacade = altipoitemFacade;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }
    
}
