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
import bo.PedidoaltipoitemBO;
import bo.TipoItemBO;
import dto.AlmacenDTO;
import dto.AltipoitemDTO;
import dto.EmpresaDTO;
import dto.PealtipoitemDTO;
import dto.PedidoDTO;
import dto.TipoItemDTO;
import entidades.Almacen;
import entidades.Altipoitem;
import entidades.AltipoitemPK;
import entidades.Empresa;
import entidades.Pedido;
import entidades.Tipoitem;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
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
    @EJB
    private PedidoaltipoitemBO pedidoaltipoitemBO;
    
    private PedidoDTO campos;
    private PealtipoitemDTO camposPealtipoItem;
    private TipoItemDTO objTipoItem;
    private TipoItemDTO objTipoItemQuitar;
    private Empresa emp;
    private Tipoitem tipItm;
    private SessionBeanPedido sessionBeanPedido = new SessionBeanPedido();
    //ARRAYS
    private List<TipoItemDTO> ListaItemsDisponibles;
    private List<TipoItemDTO> ListaItemsSeleccionado;
    private List<EmpresaDTO> ListaEmpresaAdd;
    private List<AlmacenDTO> listaAlmacenesAdd;
    private List<EmpresaDTO> ListaEmpresaEdit;
    private List<AlmacenDTO> listaAlmacenesEdit;
    private List<PealtipoitemDTO> listaItemsPedido;
    private List<PealtipoitemDTO> listaAllItemsPedido;
    
    //AGREGAR
    private PedidoDTO camposAdd;
    private String serieAdd;
    private String correlativoAdd;
    private Integer empresaAdd;
    private Integer almacenAdd;
    private String cantidadAdd;
    
    //EDITAR
    private PealtipoitemDTO objPealTipoEdit;
    private PedidoDTO objPedidoEditar;
    private Integer pedidoEdit;
    private Integer empresasEdit;
    private Integer almacenEdit;
    private String correlativEdit;
    private String serieEdit;
    private Date fechaEdit;
    private Integer idPedidoEdit;
    private List<PealtipoitemDTO> listaPealtipoitemDelete;
    Utils ut = new Utils();
    
    //BUSCAR
    private List<EmpresaDTO> listaEmpresaBusqueda;
    private List<AlmacenDTO> listaAlmacenBusqueda;
    private Integer empresaBusqueda;
    private Integer almacenBusqueda;
    private String rucBusqueda;
    
    
    private boolean disableEditar = true;
    private boolean disableVerItems = true;
    private boolean disableQuitarItemEdit = true;
    private boolean disableAgregarItemAdd = true;
    private boolean disableQuitarItemAdd  = true;
    
    @PostConstruct
    public void init(){
        emp=new Empresa();
        campos = new PedidoDTO();
        getSessionBeanPedido().setListPedido(pedidoBO.getAllPedido());
        ListaEmpresaAdd = this.comboEmpresas();
        listaAlmacenesAdd = this.comboAlmacen();
        setListaEmpresaBusqueda(this.comboEmpresas());
        setListaAlmacenBusqueda(this.comboAlmacen());
        campos = new PedidoDTO();
        campos.setIdpedido(0);
        campos.setIdEmpresa(emp);
        setDisableEditar(true);
        setDisableVerItems(true);
        camposAdd = new PedidoDTO();
        camposAdd.setIdpedido(0);
        camposAdd.setIdEmpresa(emp);
        objTipoItemQuitar = new TipoItemDTO();
    }
        
    
    
    public List<PedidoDTO> buscarPedido(ActionEvent actionEvent){
        PedidoDTO dto = new PedidoDTO();
            Empresa entidadEmpresa = new Empresa();
            entidadEmpresa.setIdempresa(getEmpresaBusqueda());
            entidadEmpresa.setRuc(getRucBusqueda());
        dto.setIdEmpresa(entidadEmpresa);
        Integer aux = getAlmacenBusqueda();
        dto.setIdalmacen(getAlmacenBusqueda());
        getSessionBeanPedido().setListPedido(pedidoBO.getPedidosByFiltro(dto));
        return getSessionBeanPedido().getListPedido();
    }
    
    public void agregarTipoItem(ActionEvent actionEvent){
        if(getObjTipoItem() == null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", "No ha seleccionado ningun item");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            setCantidadAdd("");
            RequestContext context = RequestContext.getCurrentInstance(); 
            context.update("formCantidadItem");
            context.execute("PF('addCantidad').show();");
        }
    }
    
    public void quitarTipoItem(ActionEvent actionEvent){
        if(getObjTipoItemQuitar() != null){
            getListaItemsDisponibles().add(getObjTipoItemQuitar());
            getListaItemsSeleccionado().remove(getObjTipoItemQuitar());
            if(getListaItemsDisponibles().size() != 0){
                setDisableAgregarItemAdd(false);
            } else{
                setDisableAgregarItemAdd(true);
            }
            if(getListaItemsSeleccionado().size() != 0){
                setDisableQuitarItemAdd(false);
            } else{
                setDisableQuitarItemAdd(true);
            }
            RequestContext context = RequestContext.getCurrentInstance(); 
            context.update("formAddItems");
            context.update("formTbSelec");
            setObjTipoItemQuitar(new TipoItemDTO());
        } else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", "No ha seleccionado ningun item");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void abrirModalAddPedido(ActionEvent ActionEvent){
        limpiarRefrescar();
        setDisableEditar(true);
        setDisableVerItems(true);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formBotones");
        this.crear(null);
    }
    
    public void addNuevoPedido(ActionEvent actionEvent){
        String sms = this.validarCamposRegistro();
        if(!sms.isEmpty()){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Faltan Algunos Campos", sms);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            PedidoDTO dto = new PedidoDTO();
            Empresa entidadEmpresa = new Empresa();
            entidadEmpresa.setIdempresa(getEmpresaAdd());
            dto.setIdEmpresa(entidadEmpresa);
            dto.setIdalmacen(getAlmacenAdd());
            dto.setSerie(getSerieAdd());
            dto.setCorrelativo(getCorrelativoAdd());
            Pedido entidad = pedidoBO.insertarNuevoPedido(dto); 
            //Insert Update Tipo Item
            List<AltipoitemDTO> listATI = this.getListaAlTipoItem(getListaItemsSeleccionado());
            pedidoBO.insertUpdateAlTipoItem(listATI);

            //Insert Pedido Tipo Item
            List<PealtipoitemDTO> listPXI = this.getListaPealtipoItem(getListaItemsSeleccionado() , entidad);
            pedidoBO.insertarPedidoTipoItem(listPXI);


            getSessionBeanPedido().setListPedido(pedidoBO.getAllPedido());
            RequestContext context = RequestContext.getCurrentInstance(); 
            context.update("tabPedidosFrom");
            this.cerrar();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "Se registraron los datos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        //pedidoBO.insertarNuevoPedido(dto);
    }
    
    public List<AltipoitemDTO> getListaAlTipoItem(List<TipoItemDTO> listaTipoItem){
        List<AltipoitemDTO> listALTI= new ArrayList<AltipoitemDTO>();
        for(TipoItemDTO dtoTipo : getListaItemsSeleccionado()){
            AltipoitemDTO altioiitemDTO = new AltipoitemDTO();
                Tipoitem tipoItemEntidad = new Tipoitem();
                tipoItemEntidad.setIdtipoItem(dtoTipo.getIdtipoItem());
            altioiitemDTO.setTipoitem(tipoItemEntidad);
            Almacen almacen = new Almacen();
                almacen.setIdalmacen(getAlmacenAdd());
            altioiitemDTO.setAlmacen(almacen);
            altioiitemDTO.setCantidad(0);
            altioiitemDTO.setComprados(0);
            altioiitemDTO.setEstado(1);
            altioiitemDTO.setReservado(dtoTipo.getCantidad());
            listALTI.add(altioiitemDTO);
        }
        return listALTI;
    }
    
    public List<PealtipoitemDTO> getListaPealtipoItem(List<TipoItemDTO> listaTipoItem, Pedido entidad){
        List<PealtipoitemDTO> listaPATI = new ArrayList<PealtipoitemDTO>();
        for(TipoItemDTO dtoTI : getListaItemsSeleccionado()){
            Altipoitem altipoitem = new Altipoitem();
            Almacen almacen = new Almacen();
                almacen.setIdalmacen(getAlmacenAdd());
                altipoitem.setAlmacen(almacen);
            PealtipoitemDTO dtoPXI = new PealtipoitemDTO();
            Tipoitem tipoItemEntidad = new Tipoitem();
            dtoPXI.setPedido(entidad);
                tipoItemEntidad.setIdtipoItem(dtoTI.getIdtipoItem());
                altipoitem.setTipoitem(tipoItemEntidad);
                altipoitem.setAltipoitemPK(new AltipoitemPK(altipoitem.getAlmacen().getIdalmacen(), altipoitem.getTipoitem().getIdtipoItem()));
            dtoPXI.setAltipoitem(altipoitem);
            dtoPXI.setCostoUni(dtoTI.getPrecioLista());
            dtoPXI.setCantidad(dtoTI.getCantidad());
            dtoPXI.setEstado(0);
            listaPATI.add(dtoPXI);
        }
        return listaPATI;
    }
    
    public void agregarCantidad(ActionEvent actionEvent){
        RequestContext context = RequestContext.getCurrentInstance(); 
        if(!isNumeric(getCantidadAdd())){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", "Debe ingresar un numero");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            if(Integer.parseInt(getCantidadAdd()) <= 0){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", "Debe ingresar una cantidad mayor a 0");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else{
                getListaItemsDisponibles().remove(getObjTipoItem());
                getObjTipoItem().setCantidad(Integer.parseInt(getCantidadAdd()));
                getListaItemsSeleccionado().add(getObjTipoItem()); 
                if(getListaItemsDisponibles().size() != 0){
                    setDisableAgregarItemAdd(false);
                } else{
                    setDisableAgregarItemAdd(true);
                }

                if(getListaItemsSeleccionado().size() != 0){
                    setDisableQuitarItemAdd(false);
                } else{
                    setDisableQuitarItemAdd(true);
                }
                setObjTipoItem(new TipoItemDTO());
                context.update("formAddItems");
                context.update("formTbSelec");
                context.execute("PF('addCantidad').hide();");            
                
            }
        }
        
    }    
    
    public void abrirModalAgregarItemsEdit(ActionEvent actionEvent){
        PealtipoitemDTO dto = new PealtipoitemDTO();
            Pedido entidadPedido = new Pedido();
            entidadPedido.setIdpedido(getObjPedidoEditar().getIdpedido());
        dto.setPedido(entidadPedido);
        setListaItemsPedido(pedidoaltipoitemBO.getItemsForPedido(dto));
        if(getListaItemsPedido().size() != 0){
            setDisableQuitarItemEdit(false);
        } else{
            setDisableQuitarItemEdit(true);
        }
        setListaPealtipoitemDelete(new ArrayList<PealtipoitemDTO>());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formItemsPedido");
        context.execute("PF('verItemsEdit').show();");
    }
    
    public void selectTipoItemAdd(SelectEvent event) {
        setObjTipoItem((TipoItemDTO)event.getObject());
    }
    
    public void selectTipoItemQuitar(SelectEvent event) {
        setObjTipoItemQuitar((TipoItemDTO)event.getObject());
    }
    
    public void selectPedidoEditar(SelectEvent event) {
        setDisableEditar(false);
        setDisableVerItems(false);
        setObjPedidoEditar((PedidoDTO)event.getObject());
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.update("formBotones");
    }
    
    public void selectPealTipoItemEditar(SelectEvent event){
        setObjPealTipoEdit((PealtipoitemDTO)event.getObject());
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

     public void limpiarRefrescar(){
        setListaItemsDisponibles(tipoItemBO.getAllTipoItem());
        if(getListaItemsDisponibles().size() != 0 ){
            setDisableAgregarItemAdd(false);
        } else{
            setDisableAgregarItemAdd(true);
        }
        setListaItemsSeleccionado(new ArrayList<TipoItemDTO>());
        setDisableQuitarItemAdd(true);
        setSerieAdd("");
        setCorrelativoAdd("");
        setAlmacenAdd(0);
        setEmpresaAdd(0);
        
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.update("formAddPedido");
        context.update("formAddItems");
        context.update("formTbSelec");
     }
     
     public String validarCamposRegistro(){
        String sms = "";
        if(getAlmacenAdd()== 0){
            sms = "Seleccione un almacen";
        }      
        else if(getEmpresaAdd() == 0){
            sms = "Seleccione una empresa";
        }
        if(getListaItemsSeleccionado().size() == 0){
            sms = "Debe seleccionar al menos 1 item";
        }
        return sms;
    }

     public List<EmpresaDTO> comboEmpresas(){
         List<EmpresaDTO> listaDto = empresaBO.getAllEmpresas();
         return listaDto;
     }
     public List<AlmacenDTO> comboAlmacen(){
         List<AlmacenDTO> listaDto = almacenBO.getAllAlmaces();
         return listaDto;
    }
     
    public void abirModalEditPedido(ActionEvent actionEvent){
        setIdPedidoEdit(objPedidoEditar.getIdpedido());
        setListaEmpresaEdit(this.comboEmpresas());
        setListaAlmacenesEdit(this.comboAlmacen());
        setEmpresasEdit(getObjPedidoEditar().getEmpresaId());
        setAlmacenEdit(getObjPedidoEditar().getIdalmacen());
        setCorrelativEdit(getObjPedidoEditar().getCorrelativo());
        setSerieEdit(getObjPedidoEditar().getSerie());
        setFechaEdit(getObjPedidoEditar().getFecha());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditPedido");
        context.execute("PF('editPedidoModal').show();");
    }
    
    public void editarPedido(ActionEvent actionEvent){
        String msj = this.validarCamposEdit();
        if(!msj.equals("")){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Faltan Algunos Campos", msj);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            PedidoDTO dto = new PedidoDTO();
            dto.setIdpedido(getIdPedidoEdit());
            dto.setCorrelativo(getCorrelativEdit());
            dto.setSerie(getSerieEdit());
            dto.setFecha(getFechaEdit());
                Empresa entidadEmrpresa = new Empresa();
                entidadEmrpresa.setIdempresa(getEmpresasEdit());
            dto.setIdEmpresa(entidadEmrpresa);
            dto.setIdalmacen(getAlmacenEdit());
            getSessionBeanPedido().setListPedido(pedidoBO.getAllPedido());
            setDisableEditar(true);
            pedidoBO.updateAltipoItems(this.getListaAltipoitemEdit(getListaPealtipoitemDelete()),0,dto.getIdpedido());
            pedidoBO.updateAltipoItems(this.getListaAltipoitemEdit(getListaItemsPedido()),1,dto.getIdpedido());
            pedidoBO.deletePedidosItems(getListaPealtipoitemDelete()); 
            pedidoBO.updatePedidosItems(getListaItemsPedido());
            pedidoBO.actualizarPedido(dto,getListaItemsPedido().size());
            RequestContext context = RequestContext.getCurrentInstance(); 
            context.update("formBotones");
            context.update("tabPedidosFrom");
            context.execute("PF('editPedidoModal').hide();");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO", "Se editaron los campos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public List<AltipoitemDTO> getListaAltipoitemEdit(List<PealtipoitemDTO> listaDTO){
        List<AltipoitemDTO> altiDTO = new ArrayList<AltipoitemDTO>();
        for(PealtipoitemDTO paltiDTO : listaDTO){
            AltipoitemDTO dto = new AltipoitemDTO();
                Almacen entidadAlmacen = new Almacen();
                entidadAlmacen.setIdalmacen(paltiDTO.getAltipoitem().getAlmacen().getIdalmacen());
            dto.setAlmacen(entidadAlmacen);
                Tipoitem entidadTipoitem = new Tipoitem();
                entidadTipoitem.setIdtipoItem(paltiDTO.getAltipoitem().getTipoitem().getIdtipoItem());
            dto.setTipoitem(entidadTipoitem);
            dto.setReservado(paltiDTO.getCantidad());
            altiDTO.add(dto);
        }
        return altiDTO;
    }
    
    public String validarCamposEdit(){
        String sms = "";
        if(getAlmacenEdit() == 0){
            sms = "Seleccione un almacen";
        }      
        else if(getCorrelativEdit().isEmpty()){
            sms = "Ingrese el correlativo";
        }
        else if(getEmpresasEdit() == 0){
            sms = "Seleccione una empresa";
        }
        else if(getSerieEdit().isEmpty()){
            sms = "Ingrese la serie";
        }
        return sms;
    }
    
    public void quitarPealTipoItem(ActionEvent actionEvent){
        getListaPealtipoitemDelete().add(getObjPealTipoEdit());
        getListaItemsPedido().remove(getObjPealTipoEdit());
        if(getListaItemsPedido().size() != 0){
            setDisableQuitarItemEdit(false);
        } else{
            setDisableQuitarItemEdit(true);
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formItemsPedido");
    }  
    
    public void verItemsByPedido(ActionEvent actionEvent){
        PealtipoitemDTO dto = new PealtipoitemDTO();
            Pedido entidadPedido = new Pedido();
            entidadPedido.setIdpedido(getObjPedidoEditar().getIdpedido());
        dto.setPedido(entidadPedido);
        setListaAllItemsPedido(pedidoaltipoitemBO.getAllItemsByPedido(dto));
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('verItemsPeadido').show();");
        context.update("formTbAllItems");
    }
    
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
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

    public String getSerieAdd() {
        return serieAdd;
    }

    public void setSerieAdd(String serieAdd) {
        this.serieAdd = serieAdd;
    }

    public String getCorrelativoAdd() {
        return correlativoAdd;
    }

    public void setCorrelativoAdd(String correlativoAdd) {
        this.correlativoAdd = correlativoAdd;
    }

    public Integer getEmpresaAdd() {
        return empresaAdd;
    }

    public void setEmpresaAdd(Integer empresaAdd) {
        this.empresaAdd = empresaAdd;
    }

    public Integer getAlmacenAdd() {
        return almacenAdd;
    }

    public void setAlmacenAdd(Integer almacenAdd) {
        this.almacenAdd = almacenAdd;
    }

    public String getCantidadAdd() {
        return cantidadAdd;
    }

    public void setCantidadAdd(String cantidadAdd) {
        this.cantidadAdd = cantidadAdd;
    }

    public Integer getPedidoEdit() {
        return pedidoEdit;
    }

    public void setPedidoEdit(Integer pedidoEdit) {
        this.pedidoEdit = pedidoEdit;
    }

    public Integer getEmpresasEdit() {
        return empresasEdit;
    }

    public void setEmpresasEdit(Integer empresasEdit) {
        this.empresasEdit = empresasEdit;
    }

    public Integer getAlmacenEdit() {
        return almacenEdit;
    }

    public void setAlmacenEdit(Integer almacenEdit) {
        this.almacenEdit = almacenEdit;
    }

    public String getCorrelativEdit() {
        return correlativEdit;
    }

    public void setCorrelativEdit(String correlativEdit) {
        this.correlativEdit = correlativEdit;
    }

    public String getSerieEdit() {
        return serieEdit;
    }

    public void setSerieEdit(String serieEdit) {
        this.serieEdit = serieEdit;
    }

    public Date getFechaEdit() {
        return fechaEdit;
    }

    public void setFechaEdit(Date fechaEdit) {
        this.fechaEdit = fechaEdit;
    }
    
    public List<EmpresaDTO> getListaEmpresaEdit() {
        return ListaEmpresaEdit;
    }

    public void setListaEmpresaEdit(List<EmpresaDTO> ListaEmpresaEdit) {
        this.ListaEmpresaEdit = ListaEmpresaEdit;
    }

    public List<AlmacenDTO> getListaAlmacenesEdit() {
        return listaAlmacenesEdit;
    }

    public void setListaAlmacenesEdit(List<AlmacenDTO> listaAlmacenesEdit) {
        this.listaAlmacenesEdit = listaAlmacenesEdit;
    }

    public PedidoDTO getObjPedidoEditar() {
        return objPedidoEditar;
    }

    public void setObjPedidoEditar(PedidoDTO objPedidoEditar) {
        this.objPedidoEditar = objPedidoEditar;
    }

    public boolean isDisableEditar() {
        return disableEditar;
    }

    public void setDisableEditar(boolean disableEditar) {
        this.disableEditar = disableEditar;
    }

    public boolean isDisableVerItems() {
        return disableVerItems;
    }

    public void setDisableVerItems(boolean disableVerItems) {
        this.disableVerItems = disableVerItems;
    }

    public List<PealtipoitemDTO> getListaItemsPedido() {
        return listaItemsPedido;
    }

    public void setListaItemsPedido(List<PealtipoitemDTO> listaItemsPedido) {
        this.listaItemsPedido = listaItemsPedido;
    }

    public PealtipoitemDTO getObjPealTipoEdit() {
        return objPealTipoEdit;
    }

    public void setObjPealTipoEdit(PealtipoitemDTO objPealTipoEdit) {
        this.objPealTipoEdit = objPealTipoEdit;
    }

    public Integer getIdPedidoEdit() {
        return idPedidoEdit;
    }

    public void setIdPedidoEdit(Integer idPedidoEdit) {
        this.idPedidoEdit = idPedidoEdit;
    }

    public Integer getEmpresaBusqueda() {
        return empresaBusqueda;
    }

    public void setEmpresaBusqueda(Integer empresaBusqueda) {
        this.empresaBusqueda = empresaBusqueda;
    }
    
    

    public Integer getAlmacenBusqueda() {
        return almacenBusqueda;
    }

    public void setAlmacenBusqueda(Integer almacenBusqueda) {
        this.almacenBusqueda = almacenBusqueda;
    }

    public String getRucBusqueda() {
        return rucBusqueda;
    }

    public void setRucBusqueda(String rucBusqueda) {
        this.rucBusqueda = rucBusqueda;
    }

    public List<EmpresaDTO> getListaEmpresaBusqueda() {
        return listaEmpresaBusqueda;
    }

    public void setListaEmpresaBusqueda(List<EmpresaDTO> listaEmpresaBusqueda) {
        this.listaEmpresaBusqueda = listaEmpresaBusqueda;
    }

    public List<AlmacenDTO> getListaAlmacenBusqueda() {
        return listaAlmacenBusqueda;
    }

    public void setListaAlmacenBusqueda(List<AlmacenDTO> listaAlmacenBusqueda) {
        this.listaAlmacenBusqueda = listaAlmacenBusqueda;
    }

    public List<PealtipoitemDTO> getListaPealtipoitemDelete() {
        return listaPealtipoitemDelete;
    }

    public void setListaPealtipoitemDelete(List<PealtipoitemDTO> listaPealtipoitemDelete) {
        this.listaPealtipoitemDelete = listaPealtipoitemDelete;
    }

    public List<PealtipoitemDTO> getListaAllItemsPedido() {
        return listaAllItemsPedido;
    }

    public void setListaAllItemsPedido(List<PealtipoitemDTO> listaAllItemsPedido) {
        this.listaAllItemsPedido = listaAllItemsPedido;
    }    

    public boolean isDisableQuitarItemEdit() {
        return disableQuitarItemEdit;
    }

    public void setDisableQuitarItemEdit(boolean disableQuitarItemEdit) {
        this.disableQuitarItemEdit = disableQuitarItemEdit;
    }

    public boolean isDisableAgregarItemAdd() {
        return disableAgregarItemAdd;
    }

    public void setDisableAgregarItemAdd(boolean disableAgregarItemAdd) {
        this.disableAgregarItemAdd = disableAgregarItemAdd;
    }

    public boolean isDisableQuitarItemAdd() {
        return disableQuitarItemAdd;
    }

    public void setDisableQuitarItemAdd(boolean disableQuitarItemAdd) {
        this.disableQuitarItemAdd = disableQuitarItemAdd;
    }
    
}
