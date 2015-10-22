/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author rikardo308
 */
@Entity
public class MovimientoitemDTOVista implements Serializable{
    @Id
    private Integer iditem;
    @Id
    private Integer idmovimiento;
    private String nombre_tItem;
    private String descripcion_tItem;
    private String nombre_marca;
    private String nombre_familia;

    public MovimientoitemDTOVista() {
    }
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
     * @return the nombre_tItem
     */
    public String getNombre_tItem() {
        return nombre_tItem;
    }

    /**
     * @param nombre_tItem the nombre_tItem to set
     */
    public void setNombre_tItem(String nombre_tItem) {
        this.nombre_tItem = nombre_tItem;
    }

    /**
     * @return the descripcion_tItem
     */
    public String getDescripcion_tItem() {
        return descripcion_tItem;
    }

    /**
     * @param descripcion_tItem the descripcion_tItem to set
     */
    public void setDescripcion_tItem(String descripcion_tItem) {
        this.descripcion_tItem = descripcion_tItem;
    }

    /**
     * @return the nombre_marca
     */
    public String getNombre_marca() {
        return nombre_marca;
    }

    /**
     * @param nombre_marca the nombre_marca to set
     */
    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    /**
     * @return the nombre_familia
     */
    public String getNombre_familia() {
        return nombre_familia;
    }

    /**
     * @param nombre_familia the nombre_familia to set
     */
    public void setNombre_familia(String nombre_familia) {
        this.nombre_familia = nombre_familia;
    }

    /**
     * @return the idmovimiento
     */
    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    /**
     * @param idmovimiento the idmovimiento to set
     */
    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }
    
    
    
}
