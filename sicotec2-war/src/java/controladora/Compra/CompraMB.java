/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Compra;

import Util.Utils;
import bo.CompraBO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jc7
 */
@ManagedBean
@SessionScoped
public class CompraMB {
    @EJB
    private CompraBO CompraBO = new CompraBO();
    
    private SessionBeanCompra sessionBeanCompra = new SessionBeanCompra();
    Utils ut = new Utils();
    
    
     @PostConstruct
    public void init(){
         getSessionBeanCompra().setListaTCompra(CompraBO.getAllCompras());
  
    }

    /**
     * @return the CompraBO
     */
    public CompraBO getCompraBO() {
        return CompraBO;
    }

    /**
     * @param CompraBO the CompraBO to set
     */
    public void setCompraBO(CompraBO CompraBO) {
        this.CompraBO = CompraBO;
    }

    /**
     * @return the sessionBeanCompra
     */
    public SessionBeanCompra getSessionBeanCompra() {
        return sessionBeanCompra;
    }

    /**
     * @param sessionBeanCompra the sessionBeanCompra to set
     */
    public void setSessionBeanCompra(SessionBeanCompra sessionBeanCompra) {
        this.sessionBeanCompra = sessionBeanCompra;
    }
    
    
    
}
