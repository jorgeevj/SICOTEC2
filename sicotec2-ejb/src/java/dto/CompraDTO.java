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
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author jc7
 */

public class CompraDTO {

     private Integer idcompra;   
     private Date fecha;
     private Double total;
     private String serie;
     private String correlativo;
//     private int idalmacen;
     private Empresa idempresa;
     
     
     /*AGREGAR*/
     private String nombreEmpresa;
     private Integer estado;
     
     private String nombreAlmacen;
     
     private int idAlmacen;
     private int idEmpresa;
     
     private String nombEstado;
     
     
     private Date fechaInicio;
    private Date fechaFin;
    
    

    public CompraDTO() {
    idempresa=new Empresa();
    }    
     
    public Integer getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCorrelativo() {
        return correlativo;
    }

   
    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
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
     * @return the idEmpresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the idAlmacen
     */
   

    public Integer getEstado() {
        
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    

    

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombEstado() {
        
        if(estado==1){
        nombEstado="CREADA";
        }
        if(estado==2){
        nombEstado="ENVIADA";
        }
        if(estado==3){
        nombEstado="RECIBIDA";
        }
        if(estado==4){
        nombEstado="RECHAZADA";
        }
        
        return nombEstado;
    }

    public void setNombEstado(String nombEstado) {
        this.nombEstado = nombEstado;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

 
   
}
