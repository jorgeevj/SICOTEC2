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
public class MedioPagoDTO {
    private int idMedioPago;
    private String nombre;

    /**
     * @return the idMedioPago
     */
    public int getIdMedioPago() {
        return idMedioPago;
    }

    /**
     * @param idMedioPago the idMedioPago to set
     */
    public void setIdMedioPago(int idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
