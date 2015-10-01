/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Almacen;
import entidades.Documento;
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
     private String serie;
     private String correlativo;
     private String idalmacen;
     private Empresa idempresa;
     private String nombreAlmacen;
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

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(String iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(String idalmacen) {
        this.idalmacen = idalmacen;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

   

   
     
     
     
     
     

    
}
