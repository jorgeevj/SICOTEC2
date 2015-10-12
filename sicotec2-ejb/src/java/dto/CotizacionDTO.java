/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Cotipoitem;
import entidades.Empresa;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class CotizacionDTO {
   private int idcotizacion;
   
    private int estado;
   
    private Date fechaEnvio;
   
    private int duracion;
   
    private int entrega;
    
    private String serie;
   
    private String correlativo;
   
    private int idalm;
    private Integer idalmacen;
   
    private List<Cotipoitem> cotipoitemList;
   
    private Empresa idempresa;
    
    private EmpresaDTO empresaDTO;
    
    private String nombAlmacen;
    
    private String nombEntrega;
    private String nombEstado;
    
    public CotizacionDTO() {
    }

    

    public int getIdcotizacion() {
        return idcotizacion;
    }

    public void setIdcotizacion(int idcotizacion) {
        this.idcotizacion = idcotizacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getEntrega() {
        
        
        return entrega;
    }

    public void setEntrega(int entrega) {
        this.entrega = entrega;
    }

   
    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public int getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
    }

    public List<Cotipoitem> getCotipoitemList() {
        return cotipoitemList;
    }

    public void setCotipoitemList(List<Cotipoitem> cotipoitemList) {
        this.cotipoitemList = cotipoitemList;
    }

    public Empresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresa idempresa) {
        this.idempresa = idempresa;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNombAlmacen() {
        return nombAlmacen;
    }

    public void setNombAlmacen(String nombAlmacen) {
        this.nombAlmacen = nombAlmacen;
    }

    public String getNombEntrega() {
        if(entrega==0){
        nombEntrega="Almacen";
        }else{
        nombEntrega="Domicilio";
        }
        return nombEntrega;
    }

    public void setNombEntrega(String nombEntrega) {
        this.nombEntrega = nombEntrega;
    }

    public String getNombEstado() {
        if(estado==0){
        nombEstado="CREADA";
        }
        if(estado==1){
        nombEstado="ACEPTADA";
        }
        if(estado==2){
        nombEstado="ENVIADA";
        }
        if(estado==3){
        nombEstado="CADUCADA";
        }
        
        return nombEstado;
    }

    public void setNombEstado(String nombEstado) {
        this.nombEstado = nombEstado;
    }

    public EmpresaDTO getEmpresaDTO() {
        return empresaDTO;
    }

    public void setEmpresaDTO(EmpresaDTO empresaDTO) {
        this.empresaDTO = empresaDTO;
    }

    public int getIdalm() {
        return idalm;
    }

    public void setIdalm(int idalm) {
        this.idalm = idalm;
    }
   
}
