/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author rikardo308
 */
public class loteDTO {
    private Integer idLote;
    private Integer cantidad;
    private Integer cantidadIngresar;
    private Double precioUni;
    private String idtipoitem;
    
    /*AGREGAR*/
    private String numParte;
    private String nombreTipoItem;
    private String descripcionTipoItem;

    /**
     * @return the idLote
     */
    public Integer getIdLote() {
        return idLote;
    }

    /**
     * @param idLote the idLote to set
     */
    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioUni
     */
    public Double getPrecioUni() {
        return precioUni;
    }

    /**
     * @param precioUni the precioUni to set
     */
    public void setPrecioUni(Double precioUni) {
        this.precioUni = precioUni;
    }

    /**
     * @return the idtipoitem
     */
    public String getIdtipoitem() {
        return idtipoitem;
    }

    /**
     * @param idtipoitem the idtipoitem to set
     */
    public void setIdtipoitem(String idtipoitem) {
        this.idtipoitem = idtipoitem;
    }

    /**
     * @return the numParte
     */
    public String getNumParte() {
        return numParte;
    }

    /**
     * @param numParte the numParte to set
     */
    public void setNumParte(String numParte) {
        this.numParte = numParte;
    }

    /**
     * @return the nombreTipoItem
     */
    public String getNombreTipoItem() {
        return nombreTipoItem;
    }

    /**
     * @param nombreTipoItem the nombreTipoItem to set
     */
    public void setNombreTipoItem(String nombreTipoItem) {
        this.nombreTipoItem = nombreTipoItem;
    }

    /**
     * @return the descripcionTipoItem
     */
    public String getDescripcionTipoItem() {
        return descripcionTipoItem;
    }

    /**
     * @param descripcionTipoItem the descripcionTipoItem to set
     */
    public void setDescripcionTipoItem(String descripcionTipoItem) {
        this.descripcionTipoItem = descripcionTipoItem;
    }

    /**
     * @return the cantidadIngresar
     */
    public Integer getCantidadIngresar() {
        return cantidadIngresar;
    }

    /**
     * @param cantidadIngresar the cantidadIngresar to set
     */
    public void setCantidadIngresar(Integer cantidadIngresar) {
        this.cantidadIngresar = cantidadIngresar;
    }

    
}
