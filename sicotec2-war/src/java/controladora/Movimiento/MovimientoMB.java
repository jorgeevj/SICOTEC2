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
import dto.AlmacenDTO;
import dto.CompraDTO;
import dto.DocumentoDTO;
import dto.EmpresaDTO;
import dto.ItemDTO;
import dto.MovimientoDTO;
import dto.MovimientoitemDTO;
import dto.MovimientoitemDTOVista;
import dto.TipomovimientoDTO;
import dto.loteDTO;
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
        private List<MovimientoitemDTOVista> listaItem = new ArrayList<MovimientoitemDTOVista>();
        private List<MovimientoitemDTOVista> listaItemAux = new ArrayList<MovimientoitemDTOVista>();
        private List<EmpresaDTO> listaEmpresasProveedoras = new ArrayList<EmpresaDTO>();
        private List<EmpresaDTO> listaEmpresasClientes = new ArrayList<EmpresaDTO>();
        private List<AlmacenDTO> listaAlmacenes = new ArrayList<AlmacenDTO>();
        private ArrayList listaEstados = new ArrayList();
        private List<MovimientoitemDTOVista> listaItemsMovimiento = new ArrayList<MovimientoitemDTOVista>();
        private List<CompraDTO> listaCompras = new ArrayList<CompraDTO>();
        private List<loteDTO> listaLotesCompra = new ArrayList<loteDTO>();
        private List<loteDTO> listaLotesCompraAux = new ArrayList<loteDTO>();
        
        //MOVIMIENTO SELECCIONADO
        private MovimientoDTO movimientoSeleccionado;
        
        //ITEM SELECCIONADO
        private MovimientoitemDTOVista itemSeleccionado = new MovimientoitemDTOVista();
        private MovimientoitemDTOVista itemSeleccionadoAux = new MovimientoitemDTOVista();
        
        //COMPRA SELECCIONADA
        private CompraDTO compraSeleccionada = new CompraDTO();
        private loteDTO loteSeleccionado = new loteDTO();
        private loteDTO loteSeleccionadoAux = new loteDTO();
        
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
        private boolean disableBTNCambioOCompra = true;

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
        System.out.println("IDALMACEN: "+idAlmacen);
        setListaItem(movimientoBO.getItemsByAlmacen(idAlmacen));
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabItemsDisp");
        context.execute("PF('dialog_nuevo_items').show();");
    }
    
    public void tipoMovElegidoNuevo(){
        int idTipoMov = getIdTipoMovimientoSelectNuevo();
        if(idTipoMov == getIdtipoMovimientoEntrada()){
            setPanelVisibleMovEntrada(true);
            setPanelVisibleMovSalida(false);
            setPanelVisibleMovAlmacenes(false);
            setStyleBTNICompras("display:block");
            setStyleBTNItems("display:none");
            setStyleBTNItemsAlmacen("display:none");
            
            setIdAlmacenDestinoNuevo(0);
            setIdAlmacenOrigenNuevo(0);
        }else if(idTipoMov == getIdtipoMovimientoSalida()){
            setPanelVisibleMovEntrada(false);
            setPanelVisibleMovSalida(true);
            setPanelVisibleMovAlmacenes(false);
            setStyleBTNICompras("display:none");
            setStyleBTNItems("display:block");
            setStyleBTNItemsAlmacen("display:none");
            
            setIdAlmacenDestinoNuevo(0);
            setIdAlmacenOrigenNuevo(0);
        }else if(idTipoMov == getIdtipoMovimientoEntradaAlmacen() || idTipoMov == getIdtipoMovimientoSalidaAlmacen()){
            setPanelVisibleMovEntrada(false);
            setPanelVisibleMovSalida(false);
            setPanelVisibleMovAlmacenes(true);
            setStyleBTNICompras("display:none");
            setStyleBTNItems("display:none");
            
            setIdAlmacenDestinoNuevo(0);
            setIdAlmacenOrigenNuevo(0);
        }else{
            setPanelVisibleMovEntrada(false);
            setPanelVisibleMovSalida(false);
            setPanelVisibleMovAlmacenes(false);
            setStyleBTNICompras("display:none");
            setStyleBTNItems("display:none");
            setStyleBTNItemsAlmacen("display:none");
            
            setIdAlmacenDestinoNuevo(0);
            setIdAlmacenOrigenNuevo(0);
        }
    }
    
    public void selectAlmacen(){
        if(getIdAlmacenDestinoNuevo() != 0 && getIdAlmacenOrigenNuevo()!= 0){
            setStyleBTNItemsAlmacen("display:block");
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
        
        setListaItem(itemBO.getAlliTems());
        setListaItemAux(new ArrayList<MovimientoitemDTOVista>());
        setListaCompras(compraBO.getComprasByEstado(1));
        setListaLotesCompra(new ArrayList<loteDTO>());
        setListaLotesCompraAux(new ArrayList<loteDTO>());

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialog_nuevo_mov').show();");
        context.update("formTabItemsDisp");
        context.update("formTabItemsSelecc");
    }
    
    public String validarCamposRegistro(int idTipoMov){
        String sms = "";
        if(idTipoMov == 0){
            sms = "Seleccione un Tipo de Movimiento";
        }
        else if(getIdDocumentoSelectNuevo()== 0){
            sms = "Seleccione un documento";
        }      
        else if(idTipoMov == getIdtipoMovimientoEntrada()){
            if(getNombreOrigenNuevo().equals("") || getNombreOrigenNuevo().equals(null)){
                sms = "Seleccione un proveedor origen";
            }
            else if(getIdAlmacenDestinoNuevo() == 0){
                sms = "Seleccione un almacen destino";
            }
            else if(getListaLotesCompraAux().isEmpty()){
                sms = "Ingrese lotes al movimiento";
            }
        }else if(idTipoMov == getIdtipoMovimientoSalida()){
            if(getIdAlmacenOrigenNuevo() == 0){
                sms = "Seleccione un almacen origen";
            }
            else if(getNombreDestinoNuevo().equals("") || getNombreDestinoNuevo().equals(null)){
                sms = "Seleccione un almacen Cliente";
            }
            else if(getListaItemAux().isEmpty()){
                sms = "Ingrese items al movimiento";
            }
        }else if(idTipoMov == getIdtipoMovimientoEntradaAlmacen() || idTipoMov == getIdtipoMovimientoSalidaAlmacen()){
            if(getIdAlmacenOrigenNuevo()== 0){
                sms = "Seleccione un almacen origen";
            }
            if(getIdAlmacenDestinoNuevo()== 0){
                sms = "Seleccione un almacen destino";
            }
        }
        
        return sms;
    }
    
    public String validarCamposEdicion(){
        String sms = "";
        if(getIdDocumentoEdit() == 0){
            sms = "Seleccione un documento";
        }else if(getEstadoEdit() == 0){
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
            List<ItemDTO> listaItemsReg = new ArrayList<ItemDTO>();
            
            MovimientoDTO mov = new MovimientoDTO();
            String motivo        = getMotivoNuevo();
            String comentario    = getComentarioNuevo();
            int idDocumento      = getIdDocumentoSelectNuevo();
            int estado = getEstadoMovimientoCreado();
            Date fecha = new Date();

            if(idTipoMovimiento == getIdtipoMovimientoEntradaAlmacen()|| idTipoMovimiento == getIdtipoMovimientoSalidaAlmacen()){
                int idAlmacenDestino = getIdAlmacenDestinoNuevo();
                int idAlmacenOrigen  = getIdAlmacenOrigenNuevo();

                mov.setIdalmacenDestino(idAlmacenDestino);
                mov.setIdalmacenOrigen(idAlmacenOrigen);
            }else if(idTipoMovimiento == getIdtipoMovimientoEntrada()){
                String nombreOrigen = getNombreOrigenNuevo();
                int idAlmacenDestino = getIdAlmacenDestinoNuevo();

                mov.setNombreOrigen(nombreOrigen);
                mov.setIdalmacenDestino(idAlmacenDestino);
                mov.setIdCompra(getCompraSeleccionada().getIdcompra());
                
                List<loteDTO> listaLoteSelecc = getListaLotesCompraAux();
                for(loteDTO DTO : listaLoteSelecc){
                    ItemDTO i = new ItemDTO();
                    i.setEstado("1");
                    i.setIdLote(DTO.getIdLote());
                    i.setOperatividad("0");
                    i.setIdTipoItem(DTO.getIdtipoitem());
                    i.setCantidad(DTO.getCantidadIngresar());
                    listaItemsReg.add(i);
                }
            }else if(idTipoMovimiento == getIdtipoMovimientoSalida()){
                int idAlmacenOrigen  = getIdAlmacenOrigenNuevo();
                String nombreDestino  = getNombreDestinoNuevo();

                mov.setIdalmacenOrigen(idAlmacenOrigen);
                mov.setNombreDestino(nombreDestino);
                
                List<MovimientoitemDTOVista> listaItemsSelec = getListaItemAux();
                for(MovimientoitemDTOVista DTO : listaItemsSelec){
                    MovimientoitemDTO movItem = new MovimientoitemDTO();
                    ItemDTO it = new ItemDTO();

                    it.setIditem(DTO.getIditem());

                    movItem.setEstado(getEstadoMovimientoCreado());
                    movItem.setItem(it);

                    listaItemsAg.add(movItem);
                }
            }

            mov.setComentario(comentario);
            mov.setFecha(fecha);
            mov.setEstado(estado);
            mov.setMotivo(motivo);
            mov.setIddocumento(idDocumento); 
            mov.setIdTipoMovimiento(idTipoMovimiento);

            
            List<MovimientoitemDTOVista> listaItemsSelec = getListaItemAux();
            for(MovimientoitemDTOVista DTO : listaItemsSelec){
                MovimientoitemDTO movItem = new MovimientoitemDTO();
                ItemDTO it = new ItemDTO();
                
                it.setIditem(DTO.getIditem());

                movItem.setEstado(getEstadoMovimientoCreado());
                movItem.setItem(it);

                listaItemsAg.add(movItem);
            }
            
            getMovimientoBO().insertMovimiento(mov,listaItemsAg, listaItemsReg, idTipoMovimiento);
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
        setListaItem(getItemBO().getAlliTems());
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
            mov.setIddocumento(getIdDocumentoEdit());
            mov.setMotivo(getMotivoEdit());
            if(tipoMov == getIdtipoMovimientoEntrada() || tipoMov == getIdtipoMovimientoSalida()){
                mov.setNombreOrigen(getNombreOrigenEdit());
                mov.setNombreDestino(getNombreDestinoEdit());
            }else{
                mov.setIdalmacenOrigen(getIdAlmacenOrigenEdit());
                mov.setIdalmacenDestino(getIdAlmacenDestinoEdit());
            }

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
        setItemSeleccionado((MovimientoitemDTOVista)event.getObject());
    }
    
    public void selectItemToMovimientoSelect(SelectEvent event){
        setItemSeleccionadoAux((MovimientoitemDTOVista)event.getObject());
    }
    
    public void agregarItemToMovimiento(){
        if(getItemSeleccionado() != null){
            getListaItemAux().add(getItemSeleccionado());
            getListaItem().remove(getItemSeleccionado());
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabItemsSelecc");
            context.update("formTabItemsDisp");
            setItemSeleccionado(new MovimientoitemDTOVista());
        }
        
    }
    
    public void eliminarItemToMovimiento(){
        if(getItemSeleccionadoAux() != null){
            getListaItemAux().remove(getItemSeleccionadoAux());
            getListaItem().add(getItemSeleccionadoAux());
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formTabItemsSelecc");
            context.update("formTabItemsDisp");
            setItemSeleccionadoAux(new MovimientoitemDTOVista());
        }
    }
    
    public void selectLoteAdd(SelectEvent event){
        setLoteSeleccionado((loteDTO)event.getObject());
    }
    
    public void selectLoteRemove(SelectEvent event){
        setLoteSeleccionadoAux((loteDTO)event.getObject());
    }
    
    public void openModalAddCantidadLote(){
        if(getLoteSeleccionado() != null){
            setCantidadLote("");
        
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formCantidadLote");
            context.execute("PF('dialog_add_cantidad_lote').show();");
        }
    }
    
    public void addCantidadToLoteLista(){
        loteDTO lote = getLoteSeleccionado();
        getListaLotesCompra().remove(lote);
        lote.setCantidadIngresar(Integer.parseInt(getCantidadLote()));
        
        getListaLotesCompraAux().add(lote);
        
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabLotesCompraSelec");
        context.update("formTabLotesCompra");
        
        context.execute("PF('dialog_add_cantidad_lote').hide();");
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
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabLotesCompra");
        context.update("formTabCompras");
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
    public List<MovimientoitemDTOVista> getListaItem() {
        return listaItem;
    }

    /**
     * @param listaItem the listaItem to set
     */
    public void setListaItem(List<MovimientoitemDTOVista> listaItem) {
        this.listaItem = listaItem;
    }

    /**
     * @return the listaItemAux
     */
    public List<MovimientoitemDTOVista> getListaItemAux() {
        return listaItemAux;
    }

    /**
     * @param listaItemAux the listaItemAux to set
     */
    public void setListaItemAux(List<MovimientoitemDTOVista> listaItemAux) {
        this.listaItemAux = listaItemAux;
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
    public MovimientoitemDTOVista getItemSeleccionado() {
        return itemSeleccionado;
    }

    /**
     * @param itemSeleccionado the itemSeleccionado to set
     */
    public void setItemSeleccionado(MovimientoitemDTOVista itemSeleccionado) {
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
    public MovimientoitemDTOVista getItemSeleccionadoAux() {
        return itemSeleccionadoAux;
    }

    /**
     * @param itemSeleccionadoAux the itemSeleccionadoAux to set
     */
    public void setItemSeleccionadoAux(MovimientoitemDTOVista itemSeleccionadoAux) {
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
    public List<MovimientoitemDTOVista> getListaItemsMovimiento() {
        return listaItemsMovimiento;
    }

    /**
     * @param listaItemsMovimiento the listaItemsMovimiento to set
     */
    public void setListaItemsMovimiento(List<MovimientoitemDTOVista> listaItemsMovimiento) {
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

    
    
    
}
