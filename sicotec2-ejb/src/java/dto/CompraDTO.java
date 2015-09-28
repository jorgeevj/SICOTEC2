/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Empresa;
import java.util.Date;

/**
 *
 * @author jc7
 */
public class CompraDTO {
    
     private Integer idcompra;   
     private Date fecha;
     private Double total;
     private String iddocumento;
     private String nSerie;
     private String correlativo;
     private String idalmacen;
     private Empresa idempresa;

    /**
     * @return the idcompra
     */
    public Integer getIdcompra() {
        return idcompra;
    }

    /**
     * @param idcompra the idcompra to set
     */
    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
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
     * @return the nSerie
     */
    public String getnSerie() {
        return nSerie;
    }

    /**
     * @param nSerie the nSerie to set
     */
    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
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
    public String getIdalmacen() {
        return idalmacen;
    }

    /**
     * @param idalmacen the idalmacen to set
     */
    public void setIdalmacen(String idalmacen) {
        this.idalmacen = idalmacen;
    }

    /**
     * @return the idempresa
     */
    public Empresa getIdempresa() {
        return idempresa;
    }

    /**
     * @param idempresa the idempresa to set
     */
    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }
     
     
     
     
     

    
}
