/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Pedido;

import Util.Utils;
import bo.AlmacenBO;
import bo.EmpresaBO;
import bo.PedidoBO;
import bo.TipoItemBO;
import static com.sun.javafx.logging.PulseLogger.addMessage;
import dto.AlmacenDTO;
import dto.EmpresaDTO;
import dto.PealtipoitemDTO;
import dto.PedidoDTO;
import dto.TipoItemDTO;
import entidades.Empresa;
import entidades.Tipoitem;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


@ManagedBean
@SessionScoped
public class PedidoMB{
    @EJB
    private PedidoBO  pedidoBO;
    @EJB
    private EmpresaBO empresaBO;
    @EJB
    private AlmacenBO almacenBO;
    @EJB
    private TipoItemBO tipoItemBO;
    
    private PedidoDTO campos;
    private PealtipoitemDTO camposPealtipoItem;
    private TipoItemDTO objTipoItem;
    private PedidoDTO objPedido;
    private TipoItemDTO objTipoItemQuitar;
    private Empresa emp;
    private Tipoitem tipItm;
    private SessionBeanPedido sessionBeanPedido = new SessionBeanPedido();
    Utils ut = new Utils();
    
    
    @PostConstruct
    public void init(){
        emp=new Empresa();
        campos = new PedidoDTO();
        getSessionBeanPedido().setListPedido(pedidoBO.getAllPedido());
        getSessionBeanPedido().setListaEmpresaAdd(this.comboEmpresas());
        getSessionBeanPedido().setListaAlmacenesAdd(this.comboAlmacen());
        getSessionBeanPedido().setListaItemsDisponibles(tipoItemBO.getAllTipoItem());
        campos = new PedidoDTO();
        campos.setIdpedido(0);
        campos.setIdEmpresa(emp);
    }
        
    
    
    public List<PedidoDTO> buscarPedido(ActionEvent actionEvent){     
        getSessionBeanPedido().setListPedido(pedidoBO.getPedidosByFiltro(campos));
        return getSessionBeanPedido().getListPedido();
    }
    
    public List<TipoItemDTO> agregarTipoItem(ActionEvent actionEvent){
        getSessionBeanPedido().getListaItemsSeleccionado().add(objTipoItem);
        getSessionBeanPedido().getListaItemsDisponibles().remove(objTipoItem);
        return getSessionBeanPedido().getListaItemsSeleccionado();
    }
    
    public List<TipoItemDTO> quitarTipoItem(ActionEvent actionEvent){
        getSessionBeanPedido().getListaItemsDisponibles().add(objTipoItem);
        getSessionBeanPedido().getListaItemsSeleccionado().remove(objTipoItem);
        return getSessionBeanPedido().getListaItemsDisponibles();
    }
    public void abrirModalAddPedido(){
        getSessionBeanPedido().setListaItemsDisponibles(tipoItemBO.getAllTipoItem());
        getSessionBeanPedido().setListaItemsSeleccionado(new ArrayList<TipoItemDTO>());
        System.out.println(getSessionBeanPedido().getListaItemsSeleccionado());
        this.crear(null);
    }
    
    public void addNuevoPedido(){
        PedidoDTO dto = new PedidoDTO();
        dto.setIdalmacen(campos.getIdalmacen());
        dto.setCorrelativo(campos.getCorrelativo());
        dto.setSerie(campos.getSerie());
        dto.setFecha(new Date());
        dto.setEmpresaId(campos.getEmpresaId());
        pedidoBO.insertarNuevoPedido(dto);
    }
    
    public void selectPedido(SelectEvent selectEvent){
                    PedidoDTO dto = (objPedido);
    }
    public void crear(ActionEvent actionEvent){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addPedidosModal').show();");
    }
    public void editar(ActionEvent actionEvent){
    
    }
     public void cerrar(){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('dlg2').hide();");
    }
     
     public void abrirModalAddItems(ActionEvent actionEvent){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addItemsPedidosModal').show();");
     }
     
     /*public List<TipoItemBO> setListaTipoItemSelecciona(){
        getSessionBeanPedido().setListaItemsSeleccionado(camposPealtipoItem);
        return lista;
     }*/
     
    
    
     
     
     public List<EmpresaDTO> comboEmpresas(){
         List<EmpresaDTO> listaDto = empresaBO.getAllEmpresas();
         return listaDto;
     }
     public List<AlmacenDTO> comboAlmacen(){
         List<AlmacenDTO> listaDto = almacenBO.getAllAlmaces();
         return listaDto;
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

    public PedidoDTO getCampos() {
        return campos;
    }

    public void setCampos(PedidoDTO campos) {
        this.campos = campos;
    }

    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        this.emp = emp;
    }

    public PealtipoitemDTO getCamposPealtipoItem() {
        return camposPealtipoItem;
    }

    public void setCamposPealtipoItem(PealtipoitemDTO camposPealtipoItem) {
        this.camposPealtipoItem = camposPealtipoItem;
    }

    public Tipoitem getTipItm() {
        return tipItm;
    }

    public void setTipItm(Tipoitem tipItm) {
        this.tipItm = tipItm;
    }

    public TipoItemDTO getObjTipoItem() {
        return objTipoItem;
    }

    public void setObjTipoItem(TipoItemDTO objTipoItem) {
        this.objTipoItem = objTipoItem;
    }

    public TipoItemDTO getObjTipoItemQuitar() {
        return objTipoItemQuitar;
    }

    public void setObjTipoItemQuitar(TipoItemDTO objTipoItemQuitar) {
        this.objTipoItemQuitar = objTipoItemQuitar;
    }
    
}
