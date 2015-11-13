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
public class VeMedioPagoDTO {
    private int idVenta;
    private int idMedioPago;
    private Double monto;
    
    //AGREGADOS
    private String nombreMedioPago;

    /**
     * @return the idVenta
     */
    public int getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

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
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * @return the nombreMedioPago
     */
    public String getNombreMedioPago() {
        return nombreMedioPago;
    }

    /**
     * @param nombreMedioPago the nombreMedioPago to set
     */
    public void setNombreMedioPago(String nombreMedioPago) {
        this.nombreMedioPago = nombreMedioPago;
    }
    
    
}
