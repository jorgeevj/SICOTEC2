/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Faviana
 */
public class CaracteristicaDTO {
    private Integer idCaracteristica;
    private String nombreCaracteristica;

    /**
     * @return the idCaracteristica
     */
    public Integer getIdCaracteristica() {
        return idCaracteristica;
    }

    /**
     * @param idCaracteristica the idCaracteristica to set
     */
    public void setIdCaracteristica(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    /**
     * @return the nombreCaracteristica
     */
    public String getNombreCaracteristica() {
        return nombreCaracteristica;
    }

    /**
     * @param nombreCaracteristica the nombreCaracteristica to set
     */
    public void setNombreCaracteristica(String nombreCaracteristica) {
        this.nombreCaracteristica = nombreCaracteristica;
    }
    
    
}
