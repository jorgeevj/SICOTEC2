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
    
    
}
