/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author rikardo308
 */
public class VentaDTO {
    private Integer idventa;
    private Date fecha;
    private Double descuento;
    private Double total;
    private String estado;
    private Integer iddocumento;
    private String serie;
    private String correlativo;
    private Integer idalmacen;
    
    //AGREGADOS
    private Integer idEmpresa;
    private String nombreEmpresa;
    private String nombreImpuesto;
    private String porcentajeImpuesto;

    /**
     * @return the idventa
     */
    public Integer getIdventa() {
        return idventa;
    }

    /**
     * @param idventa the idventa to set
     */
    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the descuento
     */
    public Double getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
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
     * @return the iddocumento
     */
    public Integer getIddocumento() {
        return iddocumento;
    }

    /**
     * @param iddocumento the iddocumento to set
     */
    public void setIddocumento(Integer iddocumento) {
        this.iddocumento = iddocumento;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
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

    /**
     * @return the idalmacen
     */
    public Integer getIdalmacen() {
        return idalmacen;
    }

    /**
     * @param idalmacen the idalmacen to set
     */
    public void setIdalmacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
    }

    /**
     * @return the idEmpresa
     */
    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the nombreImpuesto
     */
    public String getNombreImpuesto() {
        return nombreImpuesto;
    }

    /**
     * @param nombreImpuesto the nombreImpuesto to set
     */
    public void setNombreImpuesto(String nombreImpuesto) {
        this.nombreImpuesto = nombreImpuesto;
    }

    /**
     * @return the porcentajeImpuesto
     */
    public String getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    /**
     * @param porcentajeImpuesto the porcentajeImpuesto to set
     */
    public void setPorcentajeImpuesto(String porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    
    
}
