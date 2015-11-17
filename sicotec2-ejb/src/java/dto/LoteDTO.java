/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import bo.UnidadBO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author rikardo308
 */
public class LoteDTO {
    private UnidadBO unidadBO = lookupUnidadBOBean();
    private Integer idLote;
    private int cantidad;
    private Integer cantidadIngresar;
    private Integer cantidadConvertida;
    private double precioUni;
    private String idtipoitem;
    private RequerimientoDTO requerimiento;
    private Integer idAlmacen;
    
    /*AGREGAR*/
    private String numParte;
    private String nombreTipoItem;
    private String descripcionTipoItem;
    private String idItem;
    private int idUMedida;
    private String descUMedida;
    private int cantUnidad;
    private UnidadDTO unidadDTO;
    
    public LoteDTO() {
     requerimiento=new RequerimientoDTO();
     unidadDTO=new UnidadDTO();
    }

    /**
     * @return the idLote
     */
    public Integer getIdLote() {
        return idLote;
    }

    /**
     * @param idLote the idLote to set
     */
    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioUni
     */
    public double getPrecioUni() {
        return precioUni;
    }

    /**
     * @param precioUni the precioUni to set
     */
    public void setPrecioUni(double precioUni) {
        this.precioUni = precioUni;
    }

    /**
     * @return the idtipoitem
     */
    public String getIdtipoitem() {
        return idtipoitem;
    }

    /**
     * @param idtipoitem the idtipoitem to set
     */
    public void setIdtipoitem(String idtipoitem) {
        this.idtipoitem = idtipoitem;
    }

    /**
     * @return the numParte
     */
    public String getNumParte() {
        return numParte;
    }

    /**
     * @param numParte the numParte to set
     */
    public void setNumParte(String numParte) {
        this.numParte = numParte;
    }

    /**
     * @return the nombreTipoItem
     */
    public String getNombreTipoItem() {
        return nombreTipoItem;
    }

    /**
     * @param nombreTipoItem the nombreTipoItem to set
     */
    public void setNombreTipoItem(String nombreTipoItem) {
        this.nombreTipoItem = nombreTipoItem;
    }

    /**
     * @return the descripcionTipoItem
     */
    public String getDescripcionTipoItem() {
        return descripcionTipoItem;
    }

    /**
     * @param descripcionTipoItem the descripcionTipoItem to set
     */
    public void setDescripcionTipoItem(String descripcionTipoItem) {
        this.descripcionTipoItem = descripcionTipoItem;
    }

    /**
     * @return the cantidadIngresar
     */
    public Integer getCantidadIngresar() {
        return cantidadIngresar;
    }

    /**
     * @param cantidadIngresar the cantidadIngresar to set
     */
    public void setCantidadIngresar(Integer cantidadIngresar) {
        this.cantidadIngresar = cantidadIngresar;
    }

    /**
     * @return the idItem
     */
    public String getIdItem() {
        return idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    /**
     * @return the idUMedida
     */
    public int getIdUMedida() {
        return idUMedida;
    }

    /**
     * @param idUMedida the idUMedida to set
     */
    public void setIdUMedida(int idUMedida) {
        this.idUMedida = idUMedida;
    }

    /**
     * @return the descUMedida
     */
    public String getDescUMedida() {
       if(idUMedida!=0 ){
        descUMedida=getUnidadDTO().getNombre();
       }else{
       descUMedida="Seleccione";
       }
        return descUMedida;
    }

    /**
     * @param descUMedida the descUMedida to set
     */
    public void setDescUMedida(String descUMedida) {
        this.descUMedida = descUMedida;
    }

    /**
     * @return the cantidadConvertida
     */
    public Integer getCantidadConvertida() {
        return cantidadConvertida;
    }

    /**
     * @param cantidadConvertida the cantidadConvertida to set
     */
    public void setCantidadConvertida(Integer cantidadConvertida) {
        this.cantidadConvertida = cantidadConvertida;
    }

    public RequerimientoDTO getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(RequerimientoDTO requerimiento) {
        this.requerimiento = requerimiento;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    private UnidadBO lookupUnidadBOBean() {
        try {
            Context c = new InitialContext();
            return (UnidadBO) c.lookup("java:global/sicotec2/sicotec2-ejb/UnidadBO!bo.UnidadBO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public UnidadBO getUnidadBO() {
        return unidadBO;
    }

    public void setUnidadBO(UnidadBO unidadBO) {
        this.unidadBO = unidadBO;
    }

    public int getCantUnidad() {
        if(getUnidadDTO().getUnidades()!=null){
        cantUnidad=getUnidadDTO().getUnidades()*cantidad;
        }
        return cantUnidad;
    }

    public void setCantUnidad(int cantUnidad) {
        this.cantUnidad = cantUnidad;
    }

    public UnidadDTO getUnidadDTO() {
        if(unidadDTO.getIdunidades()!=null){
        unidadDTO=unidadBO.findByIdUnidad(unidadDTO.getIdunidades());
        }else{
        unidadDTO.setNombre("Seleccione");
        }
        
        return unidadDTO;
    }

    public void setUnidadDTO(UnidadDTO unidadDTO) {
        this.unidadDTO = unidadDTO;
    }

    
}
