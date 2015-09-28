/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Movimiento;

import Util.Utils;
import bo.DocumentoBO;
import bo.MovimientoBO;
import bo.TipoMovimientoBO;
import dto.MovimientoDTO;
import dto.TipomovimientoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class MovimientoMB {
    @EJB
    private MovimientoBO movimientoBO = new MovimientoBO();
    @EJB
    private TipoMovimientoBO tipoMovimientoBO = new TipoMovimientoBO();
    @EJB
    private DocumentoBO documentoBO = new DocumentoBO();
    
    private SessionBeanMovimiento sessionBeanMovimiento = new SessionBeanMovimiento();
    Utils ut = new Utils();

    @PostConstruct
    public void init(){
        getSessionBeanMovimiento().setListaMovimiento(movimientoBO.getAllMovimiento());
        getSessionBeanMovimiento().setListaTipoMovimiento(tipoMovimientoBO.getAllTipoMovimiento());
        getSessionBeanMovimiento().setListaEstados(this.llenarEstados());
        getSessionBeanMovimiento().setListaDocumento(documentoBO.getAllDocumentos());
    }
    
    public void selectRowTable(){
        
    }
    
    public void verItems(){
        MovimientoDTO mov = new MovimientoDTO();
        mov.setIdmovimiento(getSessionBeanMovimiento().getMovimientoSeleccionado().getIdmovimiento());
    }
    
    public void abrirEditMov(){
        
    }
    
    public ArrayList llenarEstados() {
        ArrayList estados = new ArrayList();
        estados.add(new SelectItem(0,"Inactivo"));
        estados.add(new SelectItem(1,"Activo"));
        estados.add(new SelectItem(2,"Usado"));
        estados.add(new SelectItem(3,"Agotado"));
        estados.add(new SelectItem(4,"Caducado"));
        estados.add(new SelectItem(5,"Malogrado"));
        
        return estados;
    }

    /**
     * @return the sessionBeanMovimiento
     */
    public SessionBeanMovimiento getSessionBeanMovimiento() {
        return sessionBeanMovimiento;
    }

    /**
     * @param sessionBeanMovimiento the sessionBeanMovimiento to set
     */
    public void setSessionBeanMovimiento(SessionBeanMovimiento sessionBeanMovimiento) {
        this.sessionBeanMovimiento = sessionBeanMovimiento;
    }


}
