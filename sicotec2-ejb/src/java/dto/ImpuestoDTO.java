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
public class ImpuestoDTO {
    private int idImpuesto;
    private String nombre;
    private Double porcentaje;

    /**
     * @return the idImpuesto
     */
    public int getIdImpuesto() {
        return idImpuesto;
    }

    /**
     * @param idImpuesto the idImpuesto to set
     */
    public void setIdImpuesto(int idImpuesto) {
        this.idImpuesto = idImpuesto;
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

    /**
     * @return the porcentaje
     */
    public Double getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    
    
}
