/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Movimiento;

import Util.Utils;
import bo.AlmacenBO;
import bo.CompraBO;
import bo.DocumentoBO;
import bo.ItemBO;
import bo.LoteBO;
import bo.MovimientoBO;
import bo.TipoMovimientoBO;
import bo.VentasBO;
import dto.AlmacenDTO;
import dto.CompraDTO;
import dto.CotipoitemDTO;
import dto.DocumentoDTO;
import dto.EmpresaDTO;
import dto.ItemDTO;
import dto.MovimientoDTO;
import dto.MovimientoitemDTO;
import dto.TipoItemDTO;
import entidades.Movimientoitemvista;
import dto.TipomovimientoDTO;
import dto.VentaDTO;
import dto.loteDTO;
import entidades.Movimientoitemvista;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class MovimientoMB implements Serializable{
    @EJB
    private MovimientoBO movimientoBO = new MovimientoBO();
    @EJB
    private TipoMovimientoBO tipoMovimientoBO = new TipoMovimientoBO();
    @EJB
    private DocumentoBO documentoBO = new DocumentoBO();
    @EJB
    private ItemBO itemBO = new ItemBO();
    @EJB
    private AlmacenBO almacenBO = new AlmacenBO();
    @EJB
    private CompraBO compraBO = new CompraBO();
    @EJB
    private LoteBO loteBO = new LoteBO();
    @EJB
    private VentasBO ventasBO = new VentasBO();
    
    private Utils ut = new Utils();
    
    //CONSTANTES
    private int idtipoMovimientoEntrada        = 1;
    private int idtipoMovimientoSalida         = 2;
    private int idtipoMovimientoEntradaAlmacen = 3;
    private int idtipoMovimientoSalidaAlmacen  = 4;
    
    private int estadoMovimientoCreado = 0;
    private int estadoEnTransito       = 1;
    private int estadoConfirmado       = 2;
    
    private boolean disableEditar   = true;
    private boolean disableVerItems = true;
    
    private String styleBTNItems    = "display:none";
    private String styleBTNICompras = "display:none";
    private String styleBTNItemsAlmacen = "display:none";
    
    
    //VARIABLES
        //LISTAS
        private List<TipomovimientoDTO> listaTipoMovimiento = new ArrayList<TipomovimientoDTO>();
        private List<MovimientoDTO> listaMovimiento = new ArrayList<MovimientoDTO>();
        private List<DocumentoDTO> listaDocumento = new ArrayList<DocumentoDTO>();
        private List<TipoItemDTO> listaItemAlmacen = new ArrayList<TipoItemDTO>();
        private List<TipoItemDTO> listaItemAlmacenAux = new ArrayList<TipoItemDTO>();
        private List<EmpresaDTO> listaEmpresasProveedoras = new ArrayList<EmpresaDTO>();
        private List<EmpresaDTO> listaEmpresasClientes = new ArrayList<EmpresaDTO>();
        private List<AlmacenDTO> listaAlmacenes = new ArrayList<AlmacenDTO>();
        private ArrayList listaEstados = new ArrayList();
        private List<Movimientoitemvista> listaItemsMovimiento = new ArrayList<Movimientoitemvista>();
        private List<CompraDTO> listaCompras = new ArrayList<CompraDTO>();
        private List<loteDTO> listaLotesCompra = new ArrayList<loteDTO>();
        private List<loteDTO> listaLotesCompraAux = new ArrayList<loteDTO>();
        private List<VentaDTO> listaVentas = new ArrayList<VentaDTO>();
        private List<ItemDTO> listaItemsVenta = new ArrayList<ItemDTO>();
        private List<ItemDTO> listaItemsVentaAux = new ArrayList<ItemDTO>();
        
        //MOVIMIENTO SELECCIONADO
        private MovimientoDTO movimientoSeleccionado;
        
        //ITEM SELECCIONADO Movimiento
        private TipoItemDTO itemSeleccionado = new TipoItemDTO();
        private TipoItemDTO itemSeleccionadoAux = new TipoItemDTO();
        
        //ITEM SELECCIONADO VENTA
        private ItemDTO itemSeleccionadoVenta = new ItemDTO();
        private ItemDTO itemSeleccionadoVentaAux = new ItemDTO();
        
        //COMPRA SELECCIONADA
        private CompraDTO compraSeleccionada = new CompraDTO();
        private loteDTO loteSeleccionado = new loteDTO();
        private loteDTO loteSeleccionadoAux = new loteDTO();
        
        //VENTA SELECCIONADA
        private VentaDTO ventaSeleccionada = new VentaDTO();
        private boolean disableBTNCambioOVenta = true;
        
        //REGISTRO
        private int idTipoMovimientoSelectNuevo;
        private int idDocumentoSelectNuevo;
        private int idAlmacenOrigenNuevo;
        private int idAlmacenDestinoNuevo;
        private String nombreOrigenNuevo;
        private String nombreDestinoNuevo;
        private String motivoNuevo;
        private String comentarioNuevo;
        
        //BUSQUEDA
        private int idTipoMovimientoSelectBusqueda;
        private int idDocumentoSelectBusqueda;
        private int selectEstadoBusqueda = 100;
        private String serieBusqueda;
        private Date fechaInicioBusqueda;
        private Date fechaFinBusqueda;
        private String correlativoBusqueda;
        
        //EDICION
        private String comentarioEdit;
        private String motivoEdit;
        private int idDocumentoEdit;
        private int estadoEdit;
        private String nombreOrigenEdit;
        private String nombreDestinoEdit;
        private int idAlmacenOrigenEdit;
        private int idAlmacenDestinoEdit;
        
        //CAMBIOS
        private int cambioEditMovimiento;
        
        //SELECCIONADOS
        private boolean panelVisibleMovAlmacenes = false;
        private boolean panelVisibleMovEntrada = false;
        private boolean panelVisibleMovSalida = false;
        
        //ADD CANTIDAD TO LOTES
        private String cantidadLote;
        private boolean disableTablaCompras = false;
        private boolean disableTablaVentas = false;
        private boolean disableBTNCambioOCompra = true;
        private String codItemCompra;
        
        //CAMBIAR ITEMS EN ALMACEN
        private String codItemTransaldo;
        
        
        //ADD CANTIDAD TO ITEM
        private String cantidadItem;
        
        private String cantidadItemAlmacen;
        
        //ARREGLAR ALMACENES NULOS
        private int idAlmacenOrigenSeleccRegis;
        private int idAlmacenDestinoSeleccRegis;
        
        //LISTA DE CODIGOS
        private List<ItemDTO> listaCodigosCompra = new ArrayList<ItemDTO>();
        private List<ItemDTO> listaCodigosCompraAux = new ArrayList<ItemDTO>();
        
        private List<ItemDTO> listaCodigosTranslado = new ArrayList<ItemDTO>();
        private List<ItemDTO> listaCodigosTransladoAux = new ArrayList<ItemDTO>();
        
        //COMPONENTES PARA LOS CODIGOS
        private boolean panelCodigoVisible  = false;
        private boolean panelCantCodDisable = false;
        private int cantidadCodigosAux      = 1;
        private int cantidadCodigosAux1      = 1;
 

    @PostConstruct
    public void init(){
        setListaMovimiento(getMovimientoBO().getAllMovimiento());
        setListaTipoMovimiento(getTipoMovimientoBO().getAllTipoMovimiento());
        setListaEstados(this.llenarEstados());
        setListaDocumento(getDocumentoBO().getAllDocumentos());
        setListaAlmacenes(getAlmacenBO().getAllAlmaces());
    }
    
    public void verItems(){
        if(getMovimientoSeleccionado() != null){
            MovimientoDTO mov = new MovimientoDTO();
            mov.setIdmovimiento(getMovimientoSeleccionado().getIdmovimiento());
            setListaItemsMovimiento(getMovimientoBO().getItemsByMov(mov));
            
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabItemsMovimiento");
            context.execute("PF('dialog_ver_items').show();");
        }
    }
    
    public void limpiar(){
        setIdTipoMovimientoSelectBusqueda(0);
        setIdDocumentoEdit(0);
        setSelectEstadoBusqueda(100);
        setSerieBusqueda(null);
        setFechaFinBusqueda(null);
        setFechaInicioBusqueda(null);
        setCorrelativoBusqueda(null);
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formBusqueda");
    }
    
    public void itemsByAlmacen(){
        int idAlmacen = getIdAlmacenOrigenNuevo();
        //setListaItem(movimientoBO.getItemsByAlmacen(idAlmacen));
        
        setListaItemAlmacen(movimientoBO.getItemsByAlmacen(idAlmacen));
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabItemsDisp");
        context.execute("PF('dialog_nuevo_items').show();");
    }
    
    public void tipoMovElegidoNuevo(){
        int idTipoMov = getIdTipoMovimientoSelectNuevo();
        if(idTipoMov == getIdtipoMovimientoEntrada()){
            //setPanelVisibleMovEntrada(true);
            //setPanelVisibleMovSalida(false);
            setPanelVisibleMovAlmacenes(false);
            setStyleBTNICompras("display:block");
            setStyleBTNItems("display:none");
            setStyleBTNItemsAlmacen("display:none");
            
            setIdAlmacenDestinoNuevo(0);
            setIdAlmacenOrigenNuevo(0);
        }else if(idTipoMov == getIdtipoMovimientoSalida()){
            //setPanelVisibleMovEntrada(false);
            //setPanelVisibleMovSalida(true);
            setPanelVisibleMovAlmacenes(false);
            setStyleBTNICompras("display:none");
            setStyleBTNItems("display:block");
            setStyleBTNItemsAlmacen("display:none");
            
            setIdAlmacenDestinoNuevo(0);
            setIdAlmacenOrigenNuevo(0);
        }else if(idTipoMov == getIdtipoMovimientoEntradaAlmacen() || idTipoMov == getIdtipoMovimientoSalidaAlmacen()){
            //setPanelVisibleMovEntrada(false);
            //setPanelVisibleMovSalida(false);
            setPanelVisibleMovAlmacenes(true);
            setStyleBTNICompras("display:none");
            setStyleBTNItems("display:none");
            setStyleBTNItemsAlmacen("display:none");
            
            setIdAlmacenDestinoNuevo(0);
            setIdAlmacenOrigenNuevo(0);
        }else{
            //setPanelVisibleMovEntrada(false);
            //setPanelVisibleMovSalida(false);
            setPanelVisibleMovAlmacenes(false);
            setStyleBTNICompras("display:none");
            setStyleBTNItems("display:none");
            setStyleBTNItemsAlmacen("display:none");
            
            setIdAlmacenDestinoNuevo(0);
            setIdAlmacenOrigenNuevo(0);
        }
        
        setListaItemsVenta(new ArrayList<ItemDTO>());
        setListaItemsVentaAux(new ArrayList<ItemDTO>());
        setListaLotesCompra(new ArrayList<loteDTO>());
        setListaLotesCompraAux(new ArrayList<loteDTO>());
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabLotesCompraSelec");
        context.update("formTabLotesCompra");
        context.update("formTabItemsVenta");
        context.update("formTabItemsVentasSelec");
    }
    
    public void selectAlmacen(){
        if(getIdAlmacenDestinoNuevo() != 0 && getIdAlmacenOrigenNuevo()!= 0 && getIdAlmacenDestinoNuevo() != getIdAlmacenOrigenNuevo()){
            setStyleBTNItemsAlmacen("display:block");
            setIdAlmacenOrigenSeleccRegis(getIdAlmacenOrigenNuevo());
            setIdAlmacenDestinoSeleccRegis(getIdAlmacenDestinoNuevo());
        }else{
            setStyleBTNItemsAlmacen("display:none");
        }
    }
    
    public void abrirModalNuevoMovimiento(){
        setIdTipoMovimientoSelectNuevo(0);
        setIdDocumentoSelectNuevo(0);
        setMotivoNuevo("");
        setComentarioNuevo("");
        setNombreDestinoNuevo("");
        setNombreOrigenNuevo("");
        setIdAlmacenDestinoNuevo(0);
        setIdAlmacenOrigenNuevo(0);
        
        setStyleBTNICompras("display:none");
        setStyleBTNItems("display:none");
        setPanelVisibleMovAlmacenes(false);
        setPanelVisibleMovEntrada(false);
        setPanelVisibleMovSalida(false);
        
        setDisableBTNCambioOCompra(true);
        setDisableTablaCompras(false);
        
        setListaItemAlmacen(new ArrayList<TipoItemDTO>());
        setListaItemAlmacenAux(new ArrayList<TipoItemDTO>());
        setListaCompras(compraBO.getComprasByEstado(1));
        setListaLotesCompra(new ArrayList<loteDTO>());
        setListaLotesCompraAux(new ArrayList<loteDTO>());
        setListaVentas(ventasBO.getVentasByEstado(1));

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialog_nuevo_mov').show();");
        //context.update("formTabItemsDisp");
        //context.update("formTabItemsSelecc");
    }
    
    public String validarCamposRegistro(int idTipoMov){
        String sms = "";
        if(idTipoMov == 0){
            sms = "Seleccione un Tipo de Movimiento";
        }     
        else if(idTipoMov == getIdtipoMovimientoEntrada()){
            if(getListaLotesCompraAux().isEmpty()){
                sms = "Ingrese lotes al movimiento";
            }
        }else if(idTipoMov == getIdtipoMovimientoSalida()){
            if(getListaItemsVentaAux().isEmpty()){
                sms = "Ingrese items al movimiento";
            }
        }else if(idTipoMov == getIdtipoMovimientoEntradaAlmacen() || idTipoMov == getIdtipoMovimientoSalidaAlmacen()){
            
        }
        
        return sms;
    }
    
    public String validarCamposEdicion(){
        String sms = "";
        if(getEstadoEdit() == 100){
            sms = "Seleccione un estado";
        }
        
        return sms;
    }
    
    public void registrarMovimiento(){
        int idTipoMovimiento = getIdTipoMovimientoSelectNuevo();
        
        String sms = validarCamposRegistro(idTipoMovimiento);
        if(!sms.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Faltan Algunos Campos", sms));
        }else{
            List<MovimientoitemDTO> listaItemsAg = new ArrayList<MovimientoitemDTO>();
            List<ItemDTO> listaItemsTransaction = new ArrayList<ItemDTO>();
            
            MovimientoDTO mov = new MovimientoDTO();
            String motivo        = getMotivoNuevo();
            String comentario    = getComentarioNuevo();
            int estado = getEstadoMovimientoCreado();
            Date fecha = new Date();

            if(idTipoMovimiento == getIdtipoMovimientoEntradaAlmacen()|| idTipoMovimiento == getIdtipoMovimientoSalidaAlmacen()){
                int idAlmacenDestino = getIdAlmacenDestinoSeleccRegis();
                int idAlmacenOrigen  = getIdAlmacenOrigenSeleccRegis();

                mov.setIdalmacenDestino(idAlmacenDestino);
                mov.setIdalmacenOrigen(idAlmacenOrigen);
                
                 /*List<Movimientoitemvista> listaItemSelecc = getListaItemAux();
                 for(Movimientoitemvista DTO : listaItemSelecc){
                     ItemDTO i = new ItemDTO();
                     i.setEstado("1");
                     i.setIditem(DTO.getIditem());
                     i.setOperatividad("0");
                     i.setIdTipoItem(DTO.getIdtipoitem());
                     i.setCantidad(DTO.getIdmovimiento());//USADO PARA LA CANTIDAD
                     listaItemsTransaction.add(i);
                 }*/
                 
            }else if(idTipoMovimiento == getIdtipoMovimientoEntrada()){
                mov.setNombreOrigen(getCompraSeleccionada().getNombreEmpresa());
                mov.setIdalmacenDestino(getCompraSeleccionada().getIdAlmacen());
                mov.setIdCompra(getCompraSeleccionada().getIdcompra());
                mov.setIddocumento(4); 
                
                listaItemsTransaction = getListaCodigosCompraAux();
            }else if(idTipoMovimiento == getIdtipoMovimientoSalida()){
                mov.setIdalmacenOrigen(getVentaSeleccionada().getIdalmacen());
                mov.setNombreDestino(getVentaSeleccionada().getNombreEmpresa());
                mov.setIdVenta(getVentaSeleccionada().getIdventa());
                mov.setIddocumento(3); 
                
                listaItemsTransaction = getListaItemsVentaAux();
            }

            mov.setComentario(comentario);
            mov.setFecha(fecha);
            mov.setEstado(estado);
            mov.setMotivo(motivo);
            mov.setIdTipoMovimiento(idTipoMovimiento);
            
            getMovimientoBO().insertMovimiento(mov, listaItemsTransaction, idTipoMovimiento);
            setListaMovimiento(getMovimientoBO().getAllMovimiento());
            setDisableEditar(true);
            setDisableVerItems(true);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dialog_nuevo_mov').hide();");
            context.update("formTabla");
            context.update("formBotones");
        }   
    }
    
    public void abrirModalItems(){
        //setListaItem(getItemBO().getAlliTems());
    }
    
    public void selectMovimiento(SelectEvent event){
        setMovimientoSeleccionado((MovimientoDTO)event.getObject());
        setDisableEditar(false);
        setDisableVerItems(false);
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formBotones");
    }
    
    public void abrirModalEidt(){
        setIdDocumentoEdit(movimientoSeleccionado.getIddocumento());
        setMotivoEdit(movimientoSeleccionado.getMotivo());
        setComentarioEdit(movimientoSeleccionado.getComentario());
        setEstadoEdit(movimientoSeleccionado.getEstado());
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialog_edit_mov').show();");
        context.update("formEdit");
    }
    
    public void editarMovimiento(){
        String sms = validarCamposEdicion();
        if(!sms.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Faltan Algunos Campos", sms));
        }else{
            MovimientoDTO mov = getMovimientoSeleccionado();
            int tipoMov = mov.getIdTipoMovimiento();

            mov.setComentario(getComentarioEdit());
            mov.setMotivo(getMotivoEdit());
            /*if(tipoMov == getIdtipoMovimientoEntrada() || tipoMov == getIdtipoMovimientoSalida()){
                mov.setNombreOrigen(getNombreOrigenEdit());
                mov.setNombreDestino(getNombreDestinoEdit());
            }else{
                mov.setIdalmacenOrigen(getIdAlmacenOrigenEdit());
                mov.setIdalmacenDestino(getIdAlmacenDestinoEdit());
            }*/

            mov.setEstado(getEstadoEdit());

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dialog_edit_mov').hide();");

            getMovimientoBO().updateMovimiento(mov);
            setListaMovimiento(getMovimientoBO().getAllMovimiento());
            context.update("formTabla");    
            
            setDisableEditar(true);
            setDisableVerItems(true);
            context.update("formBotones"); 
        }
    }
    
    public void buscarMovimiento(){
        MovimientoDTO mov = new MovimientoDTO();
        
        int idDocumento = getIdDocumentoSelectBusqueda();
        int idTipoMov   = getIdTipoMovimientoSelectBusqueda();
        int estado      = getSelectEstadoBusqueda();
        String nSerie   = getSerieBusqueda();
        String correlativo = getCorrelativoBusqueda();
        Date fechaInicio = getFechaInicioBusqueda();
        Date fechaFin = getFechaFinBusqueda();

        mov.setFechaFin(fechaFin);
        mov.setFechaInicio(fechaInicio);
        mov.setIddocumento(idDocumento);
        mov.setEstado(estado);
        mov.setIdTipoMovimiento(idTipoMov);
        mov.setSerie(nSerie);
        
        mov.setCorrelativo(correlativo);
        
        setListaMovimiento(getMovimientoBO().busquedaMovimiento(mov));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabla");
    }
    
    public void cambioEditMovimiento(ValueChangeEvent event) {
        setCambioEditMovimiento(1);
    }
    
    public void selectItemToMovimientoDispo(SelectEvent event){
        setItemSeleccionado((TipoItemDTO)event.getObject());
    }
    
    public void selectItemToMovimientoSelect(SelectEvent event){
        setItemSeleccionadoAux((TipoItemDTO)event.getObject());
    }
    
    public void agregarItemToMovimiento(){
        if(getItemSeleccionado() != null){
            //getListaItemAux().add(getItemSeleccionado());
            //getListaItem().remove(getItemSeleccionado());
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabItemsSelecc");
            context.update("formTabItemsDisp");
            //setItemSeleccionado(new Movimientoitemvista());
        }
    }
    
    public void eliminarItemToMovimiento(){
        if(getItemSeleccionadoAux() != null){
            //getListaItemAux().remove(getItemSeleccionadoAux());
            //getListaItem().add(getItemSeleccionadoAux());
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabItemsSelecc");
            context.update("formTabItemsDisp");
            //setItemSeleccionadoAux(new Movimientoitemvista());
        }
    }
    
    public void selectLoteAdd(SelectEvent event){
        RequestContext context = RequestContext.getCurrentInstance();
        setLoteSeleccionado((loteDTO)event.getObject());
        
        setCantidadLote(null);
        setCantidadCodigosAux(0);
        getListaCodigosCompra().clear();
        setCodItemCompra("");
        
        setPanelCodigoVisible(false);
        setPanelCantCodDisable(false);
        
        context.update("formCodigoItemCompra");
        context.update("formCantidadLote");
    }
    
    public void selectLoteRemove(SelectEvent event){
        setLoteSeleccionadoAux((loteDTO)event.getObject());
    }
    
    public void openModalAddCantidadLote(){
        if(getLoteSeleccionado() != null){
            setCantidadLote("");
            setCodItemCompra("");
        
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formCantidadLote");
            context.execute("PF('dialog_add_cantidad_lote').show();");
        }
    }
    
    public void openModalAddCantidadItem(){
        if(getItemSeleccionadoVenta() != null){
            //setCantidadItem("");

            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formCantidadItem");
            context.execute("PF('dialog_add_cantidad_item').show();");  
        } 
    }
    
    public void openModalAddCantidadItemAlmacen(){
        if(getItemSeleccionado() != null){
            setCodItemTransaldo("");

            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formCodigoItemTranslado");
            context.execute("PF('dialog_add_cantidad_item_almacen').show();");  
        } 
    }
    
    public void addCantidadToLoteLista(){
        int cantidad = Integer.parseInt(getCantidadLote());
        if(cantidad <= getLoteSeleccionado().getCantidadConvertida() && cantidad > 0){
            setPanelCodigoVisible(true);
            setPanelCantCodDisable(true);
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formCodigoItemCompra");
            context.update("formCantidadLote");
        }else{//ERROR
            
        }
    }
    
    public void insertCodigoItemToLista(){
        RequestContext context = RequestContext.getCurrentInstance();
        String cod = getCodItemCompra();
        //int cantidad = Integer.parseInt(getCantidadLote());
        boolean tof = itemBO.validarCodDuplicado(cod);
        if((cod != null && !cod.trim().equals("")) && getCantidadCodigosAux() < getLoteSeleccionado().getCantidadConvertida()
            && !getListaCodigosCompra().contains(cod) && !tof && !getListaCodigosCompraAux().contains(cod)){
            ItemDTO dto = new ItemDTO();
            dto.setIditem(cod);
            dto.setIdTipoItem(getLoteSeleccionado().getIdtipoitem());
            dto.setIdLote(getLoteSeleccionado().getIdLote());
            getListaCodigosCompra().add(dto);
            
            setCantidadCodigosAux(getCantidadCodigosAux() + 1);
            
            setCodItemCompra("");
            context.update("formCodigoItemCompra");
        }
    }
    
    public void aceptarInsertCodifoItemLista(){
        //int cantidad = Integer.parseInt(getCantidadLote());
        if(getCantidadCodigosAux() == getLoteSeleccionado().getCantidadConvertida()){
            loteDTO l = getLoteSeleccionado();
            getListaLotesCompra().remove(l);
            getListaLotesCompraAux().add(l);
            
            for(ItemDTO cod : getListaCodigosCompra()){
                getListaCodigosCompraAux().add(cod);
            }
            
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabLotesCompra");
            context.update("formTabLotesCompraSelec");
            context.execute("PF('dialog_add_cantidad_lote').hide();");
        }
    }
    
    public void eliminarCoditoItemTolista(ActionEvent actionEvent){
        if(getCantidadCodigosAux1() >= 1){
            int index = (int)actionEvent.getComponent().getAttributes().get("indexCod");
            getListaCodigosTranslado().remove(index);

            setCantidadCodigosAux1(getCantidadCodigosAux1() - 1);

            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formCodigoItemCompra");
        }
    }
    
    public void insertCodigoItemToLista1(){
        RequestContext context = RequestContext.getCurrentInstance();
        String cod = getCodItemTransaldo();
        if((cod != null && !cod.trim().equals("")) && getCantidadCodigosAux1() < getItemSeleccionado().getCantidad()
            && !getListaCodigosTranslado().contains(cod) && !getListaCodigosTransladoAux().contains(cod)){
            ItemDTO dto = new ItemDTO();
            dto.setIditem(cod);
            dto.setIdTipoItem(getLoteSeleccionado().getIdtipoitem());
            dto.setIdLote(getLoteSeleccionado().getIdLote());
            getListaCodigosTranslado().add(dto);
            
            setCantidadCodigosAux1(getCantidadCodigosAux1() + 1);
            
            setCodItemTransaldo("");
            context.update("formCodigoItemTranslado");
        }
    }
    
    public void aceptarInsertCodifoItemLista1(){
        if(getCantidadCodigosAux1() == getLoteSeleccionado().getCantidadConvertida()){
            loteDTO l = getLoteSeleccionado();
            getListaLotesCompra().remove(l);
            getListaLotesCompraAux().add(l);
            
            for(ItemDTO cod : getListaCodigosCompra()){
                getListaCodigosCompraAux().add(cod);
            }
            
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabLotesCompra");
            context.update("formTabLotesCompraSelec");
            context.execute("PF('dialog_add_cantidad_lote').hide();");
        }
    }
    
    public void eliminarCoditoItemTolista1(ActionEvent actionEvent){
        if(getCantidadCodigosAux1() >= 1){
            int index = (int)actionEvent.getComponent().getAttributes().get("indexCod");
            getListaCodigosCompra().remove(index);

            setCantidadCodigosAux(getCantidadCodigosAux() - 1);

            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formCodigoItemTranslado");
        }
    }
    
    public void addCantidadToItemLista(){
        if(getItemSeleccionadoVenta() != null){
            ItemDTO item = getItemSeleccionadoVenta();
            getListaItemsVenta().remove(item);
            //item.setCantidad(Integer.parseInt(getCantidadItem()));

            getListaItemsVentaAux().add(item);

            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabItemsVentasSelec");
            context.update("formTabItemsVenta");

            //context.execute("PF('dialog_add_cantidad_item').hide();");
        }  
    }
    
    public void addCantidadToItemAlmacenLista(){
        //Movimientoitemvista item = getItemSeleccionado();
        //getListaItem().remove(item);
        //item.setIdmovimiento(Integer.parseInt(getCantidadItemAlmacen()));//PARA LA CANTIDAD
        
        //getListaItemAux().add(item);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabItemsDisp");
        context.update("formTabItemsSelecc");
        
        context.execute("PF('dialog_add_cantidad_item_almacen').hide();");
    }
    
    public void removeItemToLista(){
        if(getItemSeleccionadoVentaAux()!= null){
            ItemDTO item = getItemSeleccionadoVentaAux();
        
            getListaItemsVentaAux().remove(item);
            getListaItemsVenta().add(item);
            
            for(ItemDTO i : getListaCodigosCompraAux()){
                if(i.getIdTipoItem().equals(getLoteSeleccionadoAux().getIdtipoitem())){
                    getListaCodigosCompraAux().remove(i);
                }
            }

            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabItemsVentasSelec");
            context.update("formTabItemsVenta");
        }
    }
    
    public void removeLoteToLista(){
        if(getLoteSeleccionadoAux() != null){
            loteDTO lote = getLoteSeleccionadoAux();
        
            getListaLotesCompraAux().remove(lote);
            getListaLotesCompra().add(lote);

            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabLotesCompraSelec");
            context.update("formTabLotesCompra");
        }
    }
    
    public void selectCompra(SelectEvent event){
        setCompraSeleccionada((CompraDTO)event.getObject());
        setListaLotesCompra(loteBO.getLotesByCompra(getCompraSeleccionada().getIdcompra()));
        setDisableTablaCompras(true);
        setDisableBTNCambioOCompra(false);
        getListaCodigosCompraAux().clear();

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabLotesCompra");
        context.update("formTabCompras");  
    }
    
    public void selectVenta(SelectEvent event){
        setVentaSeleccionada((VentaDTO)event.getObject());
        setListaItemsVenta(ventasBO.listaItemsxVenta(getVentaSeleccionada().getIdventa()));
        
        setDisableTablaVentas(true);
        setDisableBTNCambioOVenta(false);
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabItemsVenta");
        context.update("formTabVentas");
    }
    
    public void selectItemVenta(SelectEvent event){
        setItemSeleccionadoVenta((ItemDTO)event.getObject());
    }
    
    public void selectItemVentaAux(SelectEvent event){
        setItemSeleccionadoVentaAux((ItemDTO)event.getObject());
    }
    
    public void cambiarOCompra(){
        setListaLotesCompra(new ArrayList<loteDTO>());
        setListaLotesCompraAux(new ArrayList<loteDTO>());
        setLoteSeleccionado(null);
        setLoteSeleccionadoAux(null);
        
        setDisableTablaCompras(false);
        setDisableBTNCambioOCompra(true);
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabLotesCompra");
        context.update("formTabLotesCompraSelec");
        context.update("formTabCompras");
    }
    
    public void cambiarOVenta(){
        setListaItemsVenta(new ArrayList<ItemDTO>());
        setListaItemsVentaAux(new ArrayList<ItemDTO>());
        setItemSeleccionadoVenta(null);
        setItemSeleccionadoVentaAux(null);
        
        setDisableTablaVentas(false);
        setDisableBTNCambioOVenta(true);
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabVentas");
        context.update("formTabItemsVenta");
        context.update("formTabItemsVentasSelec");
    }
    
    public void abrirEditMov(){
        
    }
    
    public void tipoMovimientoNuevo(){
        int idTipoMovimiento = getIdTipoMovimientoSelectNuevo();
        
        if(idTipoMovimiento == getIdtipoMovimientoEntrada()){
            
        }else if(idTipoMovimiento == getIdtipoMovimientoSalida()){
            
        }else if(idTipoMovimiento == getIdtipoMovimientoEntradaAlmacen()){
            
        }else if(idTipoMovimiento == getIdtipoMovimientoSalidaAlmacen()){
            
        }
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
     * @return the movimientoBO
     */
    public MovimientoBO getMovimientoBO() {
        return movimientoBO;
    }

    /**
     * @param movimientoBO the movimientoBO to set
     */
    public void setMovimientoBO(MovimientoBO movimientoBO) {
        this.movimientoBO = movimientoBO;
    }

    /**
     * @return the tipoMovimientoBO
     */
    public TipoMovimientoBO getTipoMovimientoBO() {
        return tipoMovimientoBO;
    }

    /**
     * @param tipoMovimientoBO the tipoMovimientoBO to set
     */
    public void setTipoMovimientoBO(TipoMovimientoBO tipoMovimientoBO) {
        this.tipoMovimientoBO = tipoMovimientoBO;
    }

    /**
     * @return the documentoBO
     */
    public DocumentoBO getDocumentoBO() {
        return documentoBO;
    }

    /**
     * @param documentoBO the documentoBO to set
     */
    public void setDocumentoBO(DocumentoBO documentoBO) {
        this.documentoBO = documentoBO;
    }

    /**
     * @return the itemBO
     */
    public ItemBO getItemBO() {
        return itemBO;
    }

    /**
     * @param itemBO the itemBO to set
     */
    public void setItemBO(ItemBO itemBO) {
        this.itemBO = itemBO;
    }

    /**
     * @return the almacenBO
     */
    public AlmacenBO getAlmacenBO() {
        return almacenBO;
    }

    /**
     * @param almacenBO the almacenBO to set
     */
    public void setAlmacenBO(AlmacenBO almacenBO) {
        this.almacenBO = almacenBO;
    }

    /**
     * @return the ut
     */
    public Utils getUt() {
        return ut;
    }

    /**
     * @param ut the ut to set
     */
    public void setUt(Utils ut) {
        this.ut = ut;
    }

    /**
     * @return the idtipoMovimientoEntrada
     */
    public int getIdtipoMovimientoEntrada() {
        return idtipoMovimientoEntrada;
    }

    /**
     * @param idtipoMovimientoEntrada the idtipoMovimientoEntrada to set
     */
    public void setIdtipoMovimientoEntrada(int idtipoMovimientoEntrada) {
        this.idtipoMovimientoEntrada = idtipoMovimientoEntrada;
    }

    /**
     * @return the idtipoMovimientoSalida
     */
    public int getIdtipoMovimientoSalida() {
        return idtipoMovimientoSalida;
    }

    /**
     * @param idtipoMovimientoSalida the idtipoMovimientoSalida to set
     */
    public void setIdtipoMovimientoSalida(int idtipoMovimientoSalida) {
        this.idtipoMovimientoSalida = idtipoMovimientoSalida;
    }

    /**
     * @return the idtipoMovimientoEntradaAlmacen
     */
    public int getIdtipoMovimientoEntradaAlmacen() {
        return idtipoMovimientoEntradaAlmacen;
    }

    /**
     * @param idtipoMovimientoEntradaAlmacen the idtipoMovimientoEntradaAlmacen to set
     */
    public void setIdtipoMovimientoEntradaAlmacen(int idtipoMovimientoEntradaAlmacen) {
        this.idtipoMovimientoEntradaAlmacen = idtipoMovimientoEntradaAlmacen;
    }

    /**
     * @return the idtipoMovimientoSalidaAlmacen
     */
    public int getIdtipoMovimientoSalidaAlmacen() {
        return idtipoMovimientoSalidaAlmacen;
    }

    /**
     * @param idtipoMovimientoSalidaAlmacen the idtipoMovimientoSalidaAlmacen to set
     */
    public void setIdtipoMovimientoSalidaAlmacen(int idtipoMovimientoSalidaAlmacen) {
        this.idtipoMovimientoSalidaAlmacen = idtipoMovimientoSalidaAlmacen;
    }

    /**
     * @return the estadoMovimientoCreado
     */
    public int getEstadoMovimientoCreado() {
        return estadoMovimientoCreado;
    }

    /**
     * @param estadoMovimientoCreado the estadoMovimientoCreado to set
     */
    public void setEstadoMovimientoCreado(int estadoMovimientoCreado) {
        this.estadoMovimientoCreado = estadoMovimientoCreado;
    }

    /**
     * @return the estadoEnTransito
     */
    public int getEstadoEnTransito() {
        return estadoEnTransito;
    }

    /**
     * @param estadoEnTransito the estadoEnTransito to set
     */
    public void setEstadoEnTransito(int estadoEnTransito) {
        this.estadoEnTransito = estadoEnTransito;
    }

    /**
     * @return the estadoConfirmado
     */
    public int getEstadoConfirmado() {
        return estadoConfirmado;
    }

    /**
     * @param estadoConfirmado the estadoConfirmado to set
     */
    public void setEstadoConfirmado(int estadoConfirmado) {
        this.estadoConfirmado = estadoConfirmado;
    }

    /**
     * @return the listaTipoMovimiento
     */
    public List<TipomovimientoDTO> getListaTipoMovimiento() {
        return listaTipoMovimiento;
    }

    /**
     * @param listaTipoMovimiento the listaTipoMovimiento to set
     */
    public void setListaTipoMovimiento(List<TipomovimientoDTO> listaTipoMovimiento) {
        this.listaTipoMovimiento = listaTipoMovimiento;
    }

    /**
     * @return the listaMovimiento
     */
    public List<MovimientoDTO> getListaMovimiento() {
        return listaMovimiento;
    }

    /**
     * @param listaMovimiento the listaMovimiento to set
     */
    public void setListaMovimiento(List<MovimientoDTO> listaMovimiento) {
        this.listaMovimiento = listaMovimiento;
    }

    /**
     * @return the listaDocumento
     */
    public List<DocumentoDTO> getListaDocumento() {
        return listaDocumento;
    }

    /**
     * @param listaDocumento the listaDocumento to set
     */
    public void setListaDocumento(List<DocumentoDTO> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    /**
     * @return the listaItem
     */
    public List<TipoItemDTO> getListaItemAlmacen() {
        return listaItemAlmacen;
    }

    /**
     * @param listaItem the listaItem to set
     */
    public void setListaItemAlmacen(List<TipoItemDTO> listaItemAlmacen) {
        this.listaItemAlmacen = listaItemAlmacen;
    }

    /**
     * @return the listaItemAux
     */
    public List<TipoItemDTO> getListaItemAlmacenAux() {
        return listaItemAlmacenAux;
    }

    /**
     * @param listaItemAux the listaItemAux to set
     */
    public void setListaItemAlmacenAux(List<TipoItemDTO> listaItemAlmacenAux) {
        this.listaItemAlmacenAux = listaItemAlmacenAux;
    }

    /**
     * @return the listaEmpresasProveedoras
     */
    public List<EmpresaDTO> getListaEmpresasProveedoras() {
        return listaEmpresasProveedoras;
    }

    /**
     * @param listaEmpresasProveedoras the listaEmpresasProveedoras to set
     */
    public void setListaEmpresasProveedoras(List<EmpresaDTO> listaEmpresasProveedoras) {
        this.listaEmpresasProveedoras = listaEmpresasProveedoras;
    }

    /**
     * @return the listaEmpresasClientes
     */
    public List<EmpresaDTO> getListaEmpresasClientes() {
        return listaEmpresasClientes;
    }

    /**
     * @param listaEmpresasClientes the listaEmpresasClientes to set
     */
    public void setListaEmpresasClientes(List<EmpresaDTO> listaEmpresasClientes) {
        this.listaEmpresasClientes = listaEmpresasClientes;
    }

    /**
     * @return the listaAlmacenes
     */
    public List<AlmacenDTO> getListaAlmacenes() {
        return listaAlmacenes;
    }

    /**
     * @param listaAlmacenes the listaAlmacenes to set
     */
    public void setListaAlmacenes(List<AlmacenDTO> listaAlmacenes) {
        this.listaAlmacenes = listaAlmacenes;
    }

    /**
     * @return the listaEstados
     */
    public ArrayList getListaEstados() {
        return listaEstados;
    }

    /**
     * @param listaEstados the listaEstados to set
     */
    public void setListaEstados(ArrayList listaEstados) {
        this.listaEstados = listaEstados;
    }

    /**
     * @return the movimientoSeleccionado
     */
    public MovimientoDTO getMovimientoSeleccionado() {
        return movimientoSeleccionado;
    }

    /**
     * @param movimientoSeleccionado the movimientoSeleccionado to set
     */
    public void setMovimientoSeleccionado(MovimientoDTO movimientoSeleccionado) {
        this.movimientoSeleccionado = movimientoSeleccionado;
    }

    /**
     * @return the itemSeleccionado
     */
    public TipoItemDTO getItemSeleccionado() {
        return itemSeleccionado;
    }

    /**
     * @param itemSeleccionado the itemSeleccionado to set
     */
    public void setItemSeleccionado(TipoItemDTO itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    /**
     * @return the idTipoMovimientoSelectNuevo
     */
    public int getIdTipoMovimientoSelectNuevo() {
        return idTipoMovimientoSelectNuevo;
    }

    /**
     * @param idTipoMovimientoSelectNuevo the idTipoMovimientoSelectNuevo to set
     */
    public void setIdTipoMovimientoSelectNuevo(int idTipoMovimientoSelectNuevo) {
        this.idTipoMovimientoSelectNuevo = idTipoMovimientoSelectNuevo;
    }

    /**
     * @return the idDocumentoSelectNuevo
     */
    public int getIdDocumentoSelectNuevo() {
        return idDocumentoSelectNuevo;
    }

    /**
     * @param idDocumentoSelectNuevo the idDocumentoSelectNuevo to set
     */
    public void setIdDocumentoSelectNuevo(int idDocumentoSelectNuevo) {
        this.idDocumentoSelectNuevo = idDocumentoSelectNuevo;
    }

    /**
     * @return the idAlmacenOrigenNuevo
     */
    public int getIdAlmacenOrigenNuevo() {
        return idAlmacenOrigenNuevo;
    }

    /**
     * @param idAlmacenOrigenNuevo the idAlmacenOrigenNuevo to set
     */
    public void setIdAlmacenOrigenNuevo(int idAlmacenOrigenNuevo) {
        this.idAlmacenOrigenNuevo = idAlmacenOrigenNuevo;
    }

    /**
     * @return the idAlmacenDestinoNuevo
     */
    public int getIdAlmacenDestinoNuevo() {
        return idAlmacenDestinoNuevo;
    }

    /**
     * @param idAlmacenDestinoNuevo the idAlmacenDestinoNuevo to set
     */
    public void setIdAlmacenDestinoNuevo(int idAlmacenDestinoNuevo) {
        this.idAlmacenDestinoNuevo = idAlmacenDestinoNuevo;
    }

    /**
     * @return the nombreOrigenNuevo
     */
    public String getNombreOrigenNuevo() {
        return nombreOrigenNuevo;
    }

    /**
     * @param nombreOrigenNuevo the nombreOrigenNuevo to set
     */
    public void setNombreOrigenNuevo(String nombreOrigenNuevo) {
        this.nombreOrigenNuevo = nombreOrigenNuevo;
    }

    /**
     * @return the nombreDestinoNuevo
     */
    public String getNombreDestinoNuevo() {
        return nombreDestinoNuevo;
    }

    /**
     * @param nombreDestinoNuevo the nombreDestinoNuevo to set
     */
    public void setNombreDestinoNuevo(String nombreDestinoNuevo) {
        this.nombreDestinoNuevo = nombreDestinoNuevo;
    }

    /**
     * @return the motivoNuevo
     */
    public String getMotivoNuevo() {
        return motivoNuevo;
    }

    /**
     * @param motivoNuevo the motivoNuevo to set
     */
    public void setMotivoNuevo(String motivoNuevo) {
        this.motivoNuevo = motivoNuevo;
    }

    /**
     * @return the comentarioNuevo
     */
    public String getComentarioNuevo() {
        return comentarioNuevo;
    }

    /**
     * @param comentarioNuevo the comentarioNuevo to set
     */
    public void setComentarioNuevo(String comentarioNuevo) {
        this.comentarioNuevo = comentarioNuevo;
    }

    /**
     * @return the idTipoMovimientoSelectBusqueda
     */
    public int getIdTipoMovimientoSelectBusqueda() {
        return idTipoMovimientoSelectBusqueda;
    }

    /**
     * @param idTipoMovimientoSelectBusqueda the idTipoMovimientoSelectBusqueda to set
     */
    public void setIdTipoMovimientoSelectBusqueda(int idTipoMovimientoSelectBusqueda) {
        this.idTipoMovimientoSelectBusqueda = idTipoMovimientoSelectBusqueda;
    }

    /**
     * @return the idDocumentoSelectBusqueda
     */
    public int getIdDocumentoSelectBusqueda() {
        return idDocumentoSelectBusqueda;
    }

    /**
     * @param idDocumentoSelectBusqueda the idDocumentoSelectBusqueda to set
     */
    public void setIdDocumentoSelectBusqueda(int idDocumentoSelectBusqueda) {
        this.idDocumentoSelectBusqueda = idDocumentoSelectBusqueda;
    }

    /**
     * @return the selectEstadoBusqueda
     */
    public int getSelectEstadoBusqueda() {
        return selectEstadoBusqueda;
    }

    /**
     * @param selectEstadoBusqueda the selectEstadoBusqueda to set
     */
    public void setSelectEstadoBusqueda(int selectEstadoBusqueda) {
        this.selectEstadoBusqueda = selectEstadoBusqueda;
    }

    /**
     * @return the serieBusqueda
     */
    public String getSerieBusqueda() {
        return serieBusqueda;
    }

    /**
     * @param serieBusqueda the serieBusqueda to set
     */
    public void setSerieBusqueda(String serieBusqueda) {
        this.serieBusqueda = serieBusqueda;
    }

    /**
     * @return the comentarioEdit
     */
    public String getComentarioEdit() {
        return comentarioEdit;
    }

    /**
     * @param comentarioEdit the comentarioEdit to set
     */
    public void setComentarioEdit(String comentarioEdit) {
        this.comentarioEdit = comentarioEdit;
    }

    /**
     * @return the motivoEdit
     */
    public String getMotivoEdit() {
        return motivoEdit;
    }

    /**
     * @param motivoEdit the motivoEdit to set
     */
    public void setMotivoEdit(String motivoEdit) {
        this.motivoEdit = motivoEdit;
    }

    /**
     * @return the idDocumentoEdit
     */
    public int getIdDocumentoEdit() {
        return idDocumentoEdit;
    }

    /**
     * @param idDocumentoEdit the idDocumentoEdit to set
     */
    public void setIdDocumentoEdit(int idDocumentoEdit) {
        this.idDocumentoEdit = idDocumentoEdit;
    }

    /**
     * @return the nombreOrigenEdit
     */
    public String getNombreOrigenEdit() {
        return nombreOrigenEdit;
    }

    /**
     * @param nombreOrigenEdit the nombreOrigenEdit to set
     */
    public void setNombreOrigenEdit(String nombreOrigenEdit) {
        this.nombreOrigenEdit = nombreOrigenEdit;
    }

    /**
     * @return the nombreDestinoEdit
     */
    public String getNombreDestinoEdit() {
        return nombreDestinoEdit;
    }

    /**
     * @param nombreDestinoEdit the nombreDestinoEdit to set
     */
    public void setNombreDestinoEdit(String nombreDestinoEdit) {
        this.nombreDestinoEdit = nombreDestinoEdit;
    }

    /**
     * @return the idAlmacenOrigenEdit
     */
    public int getIdAlmacenOrigenEdit() {
        return idAlmacenOrigenEdit;
    }

    /**
     * @param idAlmacenOrigenEdit the idAlmacenOrigenEdit to set
     */
    public void setIdAlmacenOrigenEdit(int idAlmacenOrigenEdit) {
        this.idAlmacenOrigenEdit = idAlmacenOrigenEdit;
    }

    /**
     * @return the idAlmacenDestinoEdit
     */
    public int getIdAlmacenDestinoEdit() {
        return idAlmacenDestinoEdit;
    }

    /**
     * @param idAlmacenDestinoEdit the idAlmacenDestinoEdit to set
     */
    public void setIdAlmacenDestinoEdit(int idAlmacenDestinoEdit) {
        this.idAlmacenDestinoEdit = idAlmacenDestinoEdit;
    }

    /**
     * @return the cambioEditMovimiento
     */
    public int getCambioEditMovimiento() {
        return cambioEditMovimiento;
    }

    /**
     * @param cambioEditMovimiento the cambioEditMovimiento to set
     */
    public void setCambioEditMovimiento(int cambioEditMovimiento) {
        this.cambioEditMovimiento = cambioEditMovimiento;
    }

    /**
     * @return the panelVisibleMovAlmacenes
     */
    public boolean isPanelVisibleMovAlmacenes() {
        return panelVisibleMovAlmacenes;
    }

    /**
     * @param panelVisibleMovAlmacenes the panelVisibleMovAlmacenes to set
     */
    public void setPanelVisibleMovAlmacenes(boolean panelVisibleMovAlmacenes) {
        this.panelVisibleMovAlmacenes = panelVisibleMovAlmacenes;
    }

    /**
     * @return the panelVisibleMovEntrada
     */
    public boolean isPanelVisibleMovEntrada() {
        return panelVisibleMovEntrada;
    }

    /**
     * @param panelVisibleMovEntrada the panelVisibleMovEntrada to set
     */
    public void setPanelVisibleMovEntrada(boolean panelVisibleMovEntrada) {
        this.panelVisibleMovEntrada = panelVisibleMovEntrada;
    }

    /**
     * @return the panelVisibleMovSalida
     */
    public boolean isPanelVisibleMovSalida() {
        return panelVisibleMovSalida;
    }

    /**
     * @param panelVisibleMovSalida the panelVisibleMovSalida to set
     */
    public void setPanelVisibleMovSalida(boolean panelVisibleMovSalida) {
        this.panelVisibleMovSalida = panelVisibleMovSalida;
    }

    /**
     * @return the itemSeleccionadoAux
     */
    public TipoItemDTO getItemSeleccionadoAux() {
        return itemSeleccionadoAux;
    }

    /**
     * @param itemSeleccionadoAux the itemSeleccionadoAux to set
     */
    public void setItemSeleccionadoAux(TipoItemDTO itemSeleccionadoAux) {
        this.itemSeleccionadoAux = itemSeleccionadoAux;
    }

    /**
     * @return the estadoEdit
     */
    public int getEstadoEdit() {
        return estadoEdit;
    }

    /**
     * @param estadoEdit the estadoEdit to set
     */
    public void setEstadoEdit(int estadoEdit) {
        this.estadoEdit = estadoEdit;
    }

    /**
     * @return the listaItemsMovimiento
     */
    public List<Movimientoitemvista> getListaItemsMovimiento() {
        return listaItemsMovimiento;
    }

    /**
     * @param listaItemsMovimiento the listaItemsMovimiento to set
     */
    public void setListaItemsMovimiento(List<Movimientoitemvista> listaItemsMovimiento) {
        this.listaItemsMovimiento = listaItemsMovimiento;
    }

    /**
     * @return the fechaInicioBusqueda
     */
    public Date getFechaInicioBusqueda() {
        return fechaInicioBusqueda;
    }

    /**
     * @param fechaInicioBusqueda the fechaInicioBusqueda to set
     */
    public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
        this.fechaInicioBusqueda = fechaInicioBusqueda;
    }

    /**
     * @return the fechaFinBusqueda
     */
    public Date getFechaFinBusqueda() {
        return fechaFinBusqueda;
    }

    /**
     * @param fechaFinBusqueda the fechaFinBusqueda to set
     */
    public void setFechaFinBusqueda(Date fechaFinBusqueda) {
        this.fechaFinBusqueda = fechaFinBusqueda;
    }

    /**
     * @return the correlativoBusqueda
     */
    public String getCorrelativoBusqueda() {
        return correlativoBusqueda;
    }

    /**
     * @param correlativoBusqueda the correlativoBusqueda to set
     */
    public void setCorrelativoBusqueda(String correlativoBusqueda) {
        this.correlativoBusqueda = correlativoBusqueda;
    }

    /**
     * @return the disableEditar
     */
    public boolean isDisableEditar() {
        return disableEditar;
    }

    /**
     * @param disableEditar the disableEditar to set
     */
    public void setDisableEditar(boolean disableEditar) {
        this.disableEditar = disableEditar;
    }

    /**
     * @return the disableVerItems
     */
    public boolean isDisableVerItems() {
        return disableVerItems;
    }

    /**
     * @param disableVerItems the disableVerItems to set
     */
    public void setDisableVerItems(boolean disableVerItems) {
        this.disableVerItems = disableVerItems;
    }

    /**
     * @return the styleBTNItems
     */
    public String getStyleBTNItems() {
        return styleBTNItems;
    }

    /**
     * @param styleBTNItems the styleBTNItems to set
     */
    public void setStyleBTNItems(String styleBTNItems) {
        this.styleBTNItems = styleBTNItems;
    }

    /**
     * @return the styleBTNICompras
     */
    public String getStyleBTNICompras() {
        return styleBTNICompras;
    }

    /**
     * @param styleBTNICompras the styleBTNICompras to set
     */
    public void setStyleBTNICompras(String styleBTNICompras) {
        this.styleBTNICompras = styleBTNICompras;
    }

    /**
     * @return the listaCompras
     */
    public List<CompraDTO> getListaCompras() {
        return listaCompras;
    }

    /**
     * @param listaCompras the listaCompras to set
     */
    public void setListaCompras(List<CompraDTO> listaCompras) {
        this.listaCompras = listaCompras;
    }

    /**
     * @return the compraSeleccionada
     */
    public CompraDTO getCompraSeleccionada() {
        return compraSeleccionada;
    }

    /**
     * @param compraSeleccionada the compraSeleccionada to set
     */
    public void setCompraSeleccionada(CompraDTO compraSeleccionada) {
        this.compraSeleccionada = compraSeleccionada;
    }

    /**
     * @return the listaLotesCompra
     */
    public List<loteDTO> getListaLotesCompra() {
        return listaLotesCompra;
    }

    /**
     * @param listaLotesCompra the listaLotesCompra to set
     */
    public void setListaLotesCompra(List<loteDTO> listaLotesCompra) {
        this.listaLotesCompra = listaLotesCompra;
    }

    /**
     * @return the listaLotesCompraAux
     */
    public List<loteDTO> getListaLotesCompraAux() {
        return listaLotesCompraAux;
    }

    /**
     * @param listaLotesCompraAux the listaLotesCompraAux to set
     */
    public void setListaLotesCompraAux(List<loteDTO> listaLotesCompraAux) {
        this.listaLotesCompraAux = listaLotesCompraAux;
    }

    /**
     * @return the loteSeleccionado
     */
    public loteDTO getLoteSeleccionado() {
        return loteSeleccionado;
    }

    /**
     * @param loteSeleccionado the loteSeleccionado to set
     */
    public void setLoteSeleccionado(loteDTO loteSeleccionado) {
        this.loteSeleccionado = loteSeleccionado;
    }

    /**
     * @return the loteSeleccionadoAux
     */
    public loteDTO getLoteSeleccionadoAux() {
        return loteSeleccionadoAux;
    }

    /**
     * @param loteSeleccionadoAux the loteSeleccionadoAux to set
     */
    public void setLoteSeleccionadoAux(loteDTO loteSeleccionadoAux) {
        this.loteSeleccionadoAux = loteSeleccionadoAux;
    }

    /**
     * @return the cantidadLote
     */
    public String getCantidadLote() {
        return cantidadLote;
    }

    /**
     * @param cantidadLote the cantidadLote to set
     */
    public void setCantidadLote(String cantidadLote) {
        this.cantidadLote = cantidadLote;
    }

    /**
     * @return the disableTablaCompras
     */
    public boolean isDisableTablaCompras() {
        return disableTablaCompras;
    }

    /**
     * @param disableTablaCompras the disableTablaCompras to set
     */
    public void setDisableTablaCompras(boolean disableTablaCompras) {
        this.disableTablaCompras = disableTablaCompras;
    }

    /**
     * @return the disableBTNCambioOCompra
     */
    public boolean isDisableBTNCambioOCompra() {
        return disableBTNCambioOCompra;
    }

    /**
     * @param disableBTNCambioOCompra the disableBTNCambioOCompra to set
     */
    public void setDisableBTNCambioOCompra(boolean disableBTNCambioOCompra) {
        this.disableBTNCambioOCompra = disableBTNCambioOCompra;
    }

    /**
     * @return the styleBTNItemsAlmacen
     */
    public String getStyleBTNItemsAlmacen() {
        return styleBTNItemsAlmacen;
    }

    /**
     * @param styleBTNItemsAlmacen the styleBTNItemsAlmacen to set
     */
    public void setStyleBTNItemsAlmacen(String styleBTNItemsAlmacen) {
        this.styleBTNItemsAlmacen = styleBTNItemsAlmacen;
    }

    /**
     * @return the listaVentas
     */
    public List<VentaDTO> getListaVentas() {
        return listaVentas;
    }

    /**
     * @param listaVentas the listaVentas to set
     */
    public void setListaVentas(List<VentaDTO> listaVentas) {
        this.listaVentas = listaVentas;
    }

    /**
     * @return the disableTablaVentas
     */
    public boolean isDisableTablaVentas() {
        return disableTablaVentas;
    }

    /**
     * @param disableTablaVentas the disableTablaVentas to set
     */
    public void setDisableTablaVentas(boolean disableTablaVentas) {
        this.disableTablaVentas = disableTablaVentas;
    }

    /**
     * @return the ventaSeleccionada
     */
    public VentaDTO getVentaSeleccionada() {
        return ventaSeleccionada;
    }

    /**
     * @param ventaSeleccionada the ventaSeleccionada to set
     */
    public void setVentaSeleccionada(VentaDTO ventaSeleccionada) {
        this.ventaSeleccionada = ventaSeleccionada;
    }

    /**
     * @return the disableBTNCambioOVenta
     */
    public boolean isDisableBTNCambioOVenta() {
        return disableBTNCambioOVenta;
    }

    /**
     * @param disableBTNCambioOVenta the disableBTNCambioOVenta to set
     */
    public void setDisableBTNCambioOVenta(boolean disableBTNCambioOVenta) {
        this.disableBTNCambioOVenta = disableBTNCambioOVenta;
    }

    /**
     * @return the listaItemsVenta
     */
    public List<ItemDTO> getListaItemsVenta() {
        return listaItemsVenta;
    }

    /**
     * @param listaItemsVenta the listaItemsVenta to set
     */
    public void setListaItemsVenta(List<ItemDTO> listaItemsVenta) {
        this.listaItemsVenta = listaItemsVenta;
    }

    /**
     * @return the listaItemsVentaAux
     */
    public List<ItemDTO> getListaItemsVentaAux() {
        return listaItemsVentaAux;
    }

    /**
     * @param listaItemsVentaAux the listaItemsVentaAux to set
     */
    public void setListaItemsVentaAux(List<ItemDTO> listaItemsVentaAux) {
        this.listaItemsVentaAux = listaItemsVentaAux;
    }

    /**
     * @return the itemSeleccionadoVenta
     */
    public ItemDTO getItemSeleccionadoVenta() {
        return itemSeleccionadoVenta;
    }

    /**
     * @param itemSeleccionadoVenta the itemSeleccionadoVenta to set
     */
    public void setItemSeleccionadoVenta(ItemDTO itemSeleccionadoVenta) {
        this.itemSeleccionadoVenta = itemSeleccionadoVenta;
    }

    /**
     * @return the itemSeleccionadoVentaAux
     */
    public ItemDTO getItemSeleccionadoVentaAux() {
        return itemSeleccionadoVentaAux;
    }

    /**
     * @param itemSeleccionadoVentaAux the itemSeleccionadoVentaAux to set
     */
    public void setItemSeleccionadoVentaAux(ItemDTO itemSeleccionadoVentaAux) {
        this.itemSeleccionadoVentaAux = itemSeleccionadoVentaAux;
    }

    /**
     * @return the cantidadItem
     */
    public String getCantidadItem() {
        return cantidadItem;
    }

    /**
     * @param cantidadItem the cantidadItem to set
     */
    public void setCantidadItem(String cantidadItem) {
        this.cantidadItem = cantidadItem;
    }

    /**
     * @return the codItemCompra
     */
    public String getCodItemCompra() {
        return codItemCompra;
    }

    /**
     * @param codItemCompra the codItemCompra to set
     */
    public void setCodItemCompra(String codItemCompra) {
        this.codItemCompra = codItemCompra;
    }

    /**
     * @return the cantidadItemAlmacen
     */
    public String getCantidadItemAlmacen() {
        return cantidadItemAlmacen;
    }

    /**
     * @param cantidadItemAlmacen the cantidadItemAlmacen to set
     */
    public void setCantidadItemAlmacen(String cantidadItemAlmacen) {
        this.cantidadItemAlmacen = cantidadItemAlmacen;
    }

    /**
     * @return the idAlmacenOrigenSeleccRegis
     */
    public int getIdAlmacenOrigenSeleccRegis() {
        return idAlmacenOrigenSeleccRegis;
    }

    /**
     * @param idAlmacenOrigenSeleccRegis the idAlmacenOrigenSeleccRegis to set
     */
    public void setIdAlmacenOrigenSeleccRegis(int idAlmacenOrigenSeleccRegis) {
        this.idAlmacenOrigenSeleccRegis = idAlmacenOrigenSeleccRegis;
    }

    /**
     * @return the idAlmacenDestinoSeleccRegis
     */
    public int getIdAlmacenDestinoSeleccRegis() {
        return idAlmacenDestinoSeleccRegis;
    }

    /**
     * @param idAlmacenDestinoSeleccRegis the idAlmacenDestinoSeleccRegis to set
     */
    public void setIdAlmacenDestinoSeleccRegis(int idAlmacenDestinoSeleccRegis) {
        this.idAlmacenDestinoSeleccRegis = idAlmacenDestinoSeleccRegis;
    }

    /**
     * @return the listaCodigosCompra
     */
    public List<ItemDTO> getListaCodigosCompra() {
        return listaCodigosCompra;
    }

    /**
     * @param listaCodigosCompra the listaCodigosCompra to set
     */
    public void setListaCodigosCompra(List<ItemDTO> listaCodigosCompra) {
        this.listaCodigosCompra = listaCodigosCompra;
    }

    /**
     * @return the panelCodigoVisible
     */
    public boolean isPanelCodigoVisible() {
        return panelCodigoVisible;
    }

    /**
     * @param panelCodigoVisible the panelCodigoVisible to set
     */
    public void setPanelCodigoVisible(boolean panelCodigoVisible) {
        this.panelCodigoVisible = panelCodigoVisible;
    }

    /**
     * @return the cantidadCodigosAux
     */
    public int getCantidadCodigosAux() {
        return cantidadCodigosAux;
    }

    /**
     * @param cantidadCodigosAux the cantidadCodigosAux to set
     */
    public void setCantidadCodigosAux(int cantidadCodigosAux) {
        this.cantidadCodigosAux = cantidadCodigosAux;
    }

    /**
     * @return the panelCantCodDisable
     */
    public boolean isPanelCantCodDisable() {
        return panelCantCodDisable;
    }

    /**
     * @param panelCantCodDisable the panelCantCodDisable to set
     */
    public void setPanelCantCodDisable(boolean panelCantCodDisable) {
        this.panelCantCodDisable = panelCantCodDisable;
    }

    /**
     * @return the listaCodigosCompraAux
     */
    public List<ItemDTO> getListaCodigosCompraAux() {
        return listaCodigosCompraAux;
    }

    /**
     * @param listaCodigosCompraAux the listaCodigosCompraAux to set
     */
    public void setListaCodigosCompraAux(List<ItemDTO> listaCodigosCompraAux) {
        this.listaCodigosCompraAux = listaCodigosCompraAux;
    }

    /**
     * @return the codItemTransaldo
     */
    public String getCodItemTransaldo() {
        return codItemTransaldo;
    }

    /**
     * @param codItemTransaldo the codItemTransaldo to set
     */
    public void setCodItemTransaldo(String codItemTransaldo) {
        this.codItemTransaldo = codItemTransaldo;
    }

    /**
     * @return the listaCodigosTranslado
     */
    public List<ItemDTO> getListaCodigosTranslado() {
        return listaCodigosTranslado;
    }

    /**
     * @param listaCodigosTranslado the listaCodigosTranslado to set
     */
    public void setListaCodigosTranslado(List<ItemDTO> listaCodigosTranslado) {
        this.listaCodigosTranslado = listaCodigosTranslado;
    }

    /**
     * @return the listaCodigosTransladoAux
     */
    public List<ItemDTO> getListaCodigosTransladoAux() {
        return listaCodigosTransladoAux;
    }

    /**
     * @param listaCodigosTransladoAux the listaCodigosTransladoAux to set
     */
    public void setListaCodigosTransladoAux(List<ItemDTO> listaCodigosTransladoAux) {
        this.listaCodigosTransladoAux = listaCodigosTransladoAux;
    }

    /**
     * @return the cantidadCodigosAux1
     */
    public int getCantidadCodigosAux1() {
        return cantidadCodigosAux1;
    }

    /**
     * @param cantidadCodigosAux1 the cantidadCodigosAux1 to set
     */
    public void setCantidadCodigosAux1(int cantidadCodigosAux1) {
        this.cantidadCodigosAux1 = cantidadCodigosAux1;
    }

}
