/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Requerimientos;

/**
 *
 * @author rikardo308
 */
public class LoteDTO {
    private Integer idLote;
    private Integer cantidad;
    private Integer cantidadIngresar;
    private Integer cantidadConvertida;
    private Double precioUni;
    private String idtipoitem;
    private RequerimientoDTO requerimiento;
    private Integer idAlmacen;
    
    /*AGREGAR*/
    private String numParte;
    private String nombreTipoItem;
    private String descripcionTipoItem;
    private String idItem;
    private int idUMedida;
    private String descUMedida;

    public LoteDTO() {
     requerimiento=new RequerimientoDTO();
    }

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

    /**
     * @return the idItem
     */
    public String getIdItem() {
        return idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    /**
     * @return the idUMedida
     */
    public int getIdUMedida() {
        return idUMedida;
    }

    /**
     * @param idUMedida the idUMedida to set
     */
    public void setIdUMedida(int idUMedida) {
        this.idUMedida = idUMedida;
    }

    /**
     * @return the descUMedida
     */
    public String getDescUMedida() {
        return descUMedida;
    }

    /**
     * @param descUMedida the descUMedida to set
     */
    public void setDescUMedida(String descUMedida) {
        this.descUMedida = descUMedida;
    }

    /**
     * @return the cantidadConvertida
     */
    public Integer getCantidadConvertida() {
        return cantidadConvertida;
    }

    /**
     * @param cantidadConvertida the cantidadConvertida to set
     */
    public void setCantidadConvertida(Integer cantidadConvertida) {
        this.cantidadConvertida = cantidadConvertida;
    }

    public RequerimientoDTO getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(RequerimientoDTO requerimiento) {
        this.requerimiento = requerimiento;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    
}
