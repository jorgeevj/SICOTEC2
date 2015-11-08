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
public class ItemDTO {
    private String iditem;
    private String estado;
    private String operatividad;
    
    private Integer idLote;
    private String idTipoItem;
    private String descTipoItem;
    private String numParte;
    private String descColor;
    private String descMarca;
    private String descFamilia;
    private Integer cantidad;

    /**
     * @return the iditem
     */
    public String getIditem() {
        return iditem;
    }

    /**
     * @param iditem the iditem to set
     */
    public void setIditem(String iditem) {
        this.iditem = iditem;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the operatividad
     */
    public String getOperatividad() {
        return operatividad;
    }

    /**
     * @param operatividad the operatividad to set
     */
    public void setOperatividad(String operatividad) {
        this.operatividad = operatividad;
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
     * @return the idTipoItem
     */
    public String getIdTipoItem() {
        return idTipoItem;
    }

    /**
     * @param idTipoItem the idTipoItem to set
     */
    public void setIdTipoItem(String idTipoItem) {
        this.idTipoItem = idTipoItem;
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
     * @return the descTipoItem
     */
    public String getDescTipoItem() {
        return descTipoItem;
    }

    /**
     * @param descTipoItem the descTipoItem to set
     */
    public void setDescTipoItem(String descTipoItem) {
        this.descTipoItem = descTipoItem;
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
     * @return the descColor
     */
    public String getDescColor() {
        return descColor;
    }

    /**
     * @param descColor the descColor to set
     */
    public void setDescColor(String descColor) {
        this.descColor = descColor;
    }

    /**
     * @return the descMarca
     */
    public String getDescMarca() {
        return descMarca;
    }

    /**
     * @param descMarca the descMarca to set
     */
    public void setDescMarca(String descMarca) {
        this.descMarca = descMarca;
    }

    /**
     * @return the descFamilia
     */
    public String getDescFamilia() {
        return descFamilia;
    }

    /**
     * @param descFamilia the descFamilia to set
     */
    public void setDescFamilia(String descFamilia) {
        this.descFamilia = descFamilia;
    }
    
    
}
