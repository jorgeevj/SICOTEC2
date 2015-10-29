/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import bo.CotizacionBO;
import entidades.Cotipoitem;
import entidades.Empresa;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Jorge
 */
public class CotizacionDTO {
    CotizacionBO cotizacionBO = lookupCotizacionBOBean();
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
        empresaDTO=new EmpresaDTO();
        idempresa=new Empresa();
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
        long tiempo,actual;
        if(fechaEnvio!=null){
         tiempo=fechaEnvio.getTime()+(24*60*60*1000l*duracion);
         actual=new Date().getTime();
        if((fechaEnvio.getTime()+(24*60*60*1000l*duracion))<=new Date().getTime()){
        estado=4;
        cotizacionBO.guardarEditar(this);
        }
        }
        if(estado==1){
        nombEstado="CREADA";
        }
        if(estado==2){
        nombEstado="APROBADA";
        }
        if(estado==3){
        nombEstado="ENVIADA";
        }
        if(estado==4){
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

    private CotizacionBO lookupCotizacionBOBean() {
        try {
            Context c = new InitialContext();
            return (CotizacionBO) c.lookup("java:global/sicotec2/sicotec2-ejb/CotizacionBO!bo.CotizacionBO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
   
}
