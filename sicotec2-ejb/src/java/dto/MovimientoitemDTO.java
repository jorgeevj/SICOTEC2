/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Item;
import entidades.Movimiento;

/**
 *
 * @author rikardo308
 */
public class MovimientoitemDTO {
    private Integer estado;
    private ItemDTO item;
    private MovimientoDTO movimiento;

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * @return the item
     */
    public ItemDTO getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(ItemDTO item) {
        this.item = item;
    }

    /**
     * @return the movimiento
     */
    public MovimientoDTO getMovimiento() {
        return movimiento;
    }

    /**
     * @param movimiento the movimiento to set
     */
    public void setMovimiento(MovimientoDTO movimiento) {
        this.movimiento = movimiento;
    }
    
    
}
