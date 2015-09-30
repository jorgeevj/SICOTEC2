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
public class TipomovimientoDTO {
    private Integer idtipoMovimiento;
    private String iddocumento;
    private String nombre;
    private String serie;
    private String correlativo;

    /**
     * @return the idtipoMovimiento
     */
    public Integer getIdtipoMovimiento() {
        return idtipoMovimiento;
    }

    /**
     * @param idtipoMovimiento the idtipoMovimiento to set
     */
    public void setIdtipoMovimiento(Integer idtipoMovimiento) {
        this.idtipoMovimiento = idtipoMovimiento;
    }

    /**
     * @return the iddocumento
     */
    public String getIddocumento() {
        return iddocumento;
    }

    /**
     * @param iddocumento the iddocumento to set
     */
    public void setIddocumento(String iddocumento) {
        this.iddocumento = iddocumento;
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
     * @return the correlativo
     */
    public String getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
    
    
}
