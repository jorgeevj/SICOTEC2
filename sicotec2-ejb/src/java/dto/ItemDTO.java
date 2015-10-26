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
    private Integer iditem;
    private String estado;
    private String operatividad;
    
    private Integer idLote;
    private String idTipoItem;
    private Integer cantidad;

    /**
     * @return the iditem
     */
    public Integer getIditem() {
        return iditem;
    }

    /**
     * @param iditem the iditem to set
     */
    public void setIditem(Integer iditem) {
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
    
    
}
