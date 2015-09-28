/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Pedido;

import Util.Utils;
import bo.PedidoBO;
import dto.TipomovimientoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;


@ManagedBean
@SessionScoped
public class PedidoMB{
    @EJB
    private PedidoBO pedidoBO = new PedidoBO();
    
    private SessionBeanPedido sessionBeanPedido = new SessionBeanPedido();
    Utils ut = new Utils();
    
    
    @PostConstruct
    public void init(){
        
        getSessionBeanPedido().setListPedido(pedidoBO.getAllPedido());
    }
    
    
    public void selectDataFromView(){
        
    }
    
    

    public PedidoBO getPedidoBO() {
        return pedidoBO;
    }

    public void setPedidoBO(PedidoBO PedidoBO) {
        this.pedidoBO = PedidoBO;
    }

    public SessionBeanPedido getSessionBeanPedido() {
        return sessionBeanPedido;
    }

    public void setSessionBeanPedido(SessionBeanPedido sessionBeanPedido) {
        this.sessionBeanPedido = sessionBeanPedido;
    }
    
    
    
}
