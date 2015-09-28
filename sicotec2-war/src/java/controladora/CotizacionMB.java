/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import bo.AlmacenBO;
import bo.CotizacionBO;
import dto.CotizacionDTO;
import entidades.Almacen;
import entidades.Cotizacion;
import entidades.Empresa;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class CotizacionMB {
    @EJB
    private AlmacenBO almacenBO;

    @EJB
    private CotizacionBO cotizacionBO;

    private List<Cotizacion> listaCotizacion;
    private CotizacionDTO campos;
    private Empresa emp;
    private List<Almacen> listaAlmacenes;
    private String selectAlmacen;
    /**
     * Creates a new instance of CotizacionMB
     */
    public CotizacionMB() {
    }

    @PostConstruct
    public void init() {
        listaCotizacion = cotizacionBO.getAllCotizaciones();
        emp=new Empresa();
        campos = new CotizacionDTO();
        campos.setIdcotizacion(0);
        campos.setIdempresa(emp);
        listaAlmacenes= almacenBO.findAll();
        selectAlmacen="0";
    }

    public List<Cotizacion> consultar(ActionEvent actionEvent) {
        listaCotizacion=cotizacionBO.BuscarCotizacion(campos);
        return listaCotizacion;
    }
    
    public void crear(){}

    public CotizacionBO getCotizacionBO() {
        return cotizacionBO;
    }

    public void setCotizacionBO(CotizacionBO cotizacionBO) {
        this.cotizacionBO = cotizacionBO;
    }

    public List<Cotizacion> getListaCotizacion() {
        return listaCotizacion;
    }

    public void setListaCotizacion(List<Cotizacion> listaCotizacion) {
        this.listaCotizacion = listaCotizacion;
    }

    public CotizacionDTO getCampos() {
        return campos;
    }

    public void setCampos(CotizacionDTO campos) {
        this.campos = campos;
    }

    public List<Almacen> getListaAlmacenes() {
        return listaAlmacenes;
    }

    public void setListaAlmacenes(List<Almacen> listaAlmacenes) {
        this.listaAlmacenes = listaAlmacenes;
    }

    public AlmacenBO getAlmacenBO() {
        return almacenBO;
    }

    public void setAlmacenBO(AlmacenBO almacenBO) {
        this.almacenBO = almacenBO;
    }

    public String getSelectAlmacen() {
        return selectAlmacen;
    }

    public void setSelectAlmacen(String selectAlmacen) {
        this.selectAlmacen = selectAlmacen;
    }

    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        this.emp = emp;
    }

}
