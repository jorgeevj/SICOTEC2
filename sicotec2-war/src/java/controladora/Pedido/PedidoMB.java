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
    //ARRAYS
    private List<TipoItemDTO> ListaItemsDisponibles;
    private List<TipoItemDTO> ListaItemsSeleccionado;
    private List<EmpresaDTO> ListaEmpresaAdd;
    private List<AlmacenDTO> listaAlmacenesAdd;
    
    //AGREGAR
    private PedidoDTO camposAdd;
    
    //EDITAR
    private PedidoDTO camposEdit;
    Utils ut = new Utils();
    
    
    @PostConstruct
    public void init(){
        emp=new Empresa();
        campos = new PedidoDTO();
        getSessionBeanPedido().setListPedido(pedidoBO.getAllPedido());
        ListaItemsDisponibles = tipoItemBO.getAllTipoItem();
        ListaItemsSeleccionado = new ArrayList<TipoItemDTO>(); 
        ListaEmpresaAdd = this.comboEmpresas();
        listaAlmacenesAdd = this.comboAlmacen();
        campos = new PedidoDTO();
        campos.setIdpedido(0);
        campos.setIdEmpresa(emp);
        
        camposAdd = new PedidoDTO();
        camposAdd.setIdpedido(0);
        camposAdd.setIdEmpresa(emp);
    }
        
    
    
    public List<PedidoDTO> buscarPedido(ActionEvent actionEvent){     
        getSessionBeanPedido().setListPedido(pedidoBO.getPedidosByFiltro(campos));
        return getSessionBeanPedido().getListPedido();
    }
    
    public void agregarTipoItem(ActionEvent actionEvent){
        ListaItemsSeleccionado.add(objTipoItem);
        ListaItemsDisponibles.remove(objTipoItem);
    }
    
    public void quitarTipoItem(ActionEvent actionEvent){
        ListaItemsDisponibles.add(objTipoItem);
        ListaItemsSeleccionado.remove(objTipoItem);
    }
    
    public void abrirModalAddPedido(){
        ListaItemsDisponibles = tipoItemBO.getAllTipoItem();
        ListaItemsSeleccionado = new ArrayList<TipoItemDTO>();
        this.crear(null);
    }
    
    public void addNuevoPedido(ActionEvent actionEvent){
        PedidoDTO  dto = pedidoBO.insertarNuevoPedido(camposAdd); 
        
        getSessionBeanPedido().setListPedido(pedidoBO.getAllPedido());
        this.cerrar();
        //pedidoBO.insertarNuevoPedido(dto);
    }
    
    public void editarPedido(ActionEvent actionEvent){
        pedidoBO.actualizarPedido(camposEdit);
        getSessionBeanPedido().setListPedido(pedidoBO.getAllPedido());
    }
    
    
    
    
    
    public void crear(ActionEvent actionEvent){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addPedidosModal').show();");
    }
    public void editar(ActionEvent actionEvent){
    
    }
     public void cerrar(){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addPedidosModal').hide();");
    }
     
     public void abrirModalAddItems(ActionEvent actionEvent){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addItemsPedidosModal').show();");
     }

     
     
     
     
     
     
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

    public List<TipoItemDTO> getListaItemsDisponibles() {
        return ListaItemsDisponibles;
    }

    public void setListaItemsDisponibles(List<TipoItemDTO> ListaItemsDisponibles) {
        this.ListaItemsDisponibles = ListaItemsDisponibles;
    }

    public List<TipoItemDTO> getListaItemsSeleccionado() {
        return ListaItemsSeleccionado;
    }

    public void setListaItemsSeleccionado(List<TipoItemDTO> ListaItemsSeleccionado) {
        this.ListaItemsSeleccionado = ListaItemsSeleccionado;
    }

    public List<EmpresaDTO> getListaEmpresaAdd() {
        return ListaEmpresaAdd;
    }

    public void setListaEmpresaAdd(List<EmpresaDTO> ListaEmpresaAdd) {
        this.ListaEmpresaAdd = ListaEmpresaAdd;
    }

    public List<AlmacenDTO> getListaAlmacenesAdd() {
        return listaAlmacenesAdd;
    }

    public void setListaAlmacenesAdd(List<AlmacenDTO> listaAlmacenesAdd) {
        this.listaAlmacenesAdd = listaAlmacenesAdd;
    }

    public PedidoDTO getCamposAdd() {
        return camposAdd;
    }

    public void setCamposAdd(PedidoDTO camposAdd) {
        this.camposAdd = camposAdd;
    }

    public PedidoDTO getCamposEdit() {
        return camposEdit;
    }

    public void setCamposEdit(PedidoDTO camposEdit) {
        this.camposEdit = camposEdit;
    }
    
}
