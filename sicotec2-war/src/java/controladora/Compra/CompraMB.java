/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Compra;

import Util.Utils;
import bo.AlmacenBO;
import bo.CompraBO;
import bo.CotizacionBO;
import bo.EmpresaBO;
import bo.PedidoBO;
import bo.PedidoaltipoitemBO;
import bo.UnidadBO;
import dto.AlmacenDTO;
import dto.CompraDTO;
import dto.EmpresaDTO;
import dto.PealtipoitemDTO;
import dto.UnidadDTO;
import dto.LoteDTO;
import entidades.Almacen;
import entidades.Compra;
import entidades.Documento;
import entidades.Empresa;
import entidades.Lote;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author jc7
 */
@ManagedBean
@SessionScoped
public class CompraMB {

    @EJB
    private CotizacionBO cotizacionBO;

    private int codigoAlamacen;
    private String nombreAlamacen;

    @EJB
    private CompraBO compraBO;
    @EJB
    private EmpresaBO empresaBO;
    @EJB
    private AlmacenBO almacenBO;
    @EJB
    private PedidoBO pedidoBO;
    @EJB
    private PedidoaltipoitemBO pedidoaltipoitemBO;
    @EJB
    private UnidadBO unidadBO;

    private List<CompraDTO> listaCompras;
    private List<AlmacenDTO> listaAlmacenes;
    private List<UnidadDTO> listaUnidad;
    private List<EmpresaDTO> listaEmpresasProveedoras;

    //AGREGAR
    private CompraDTO camposAdd;
    //Agregar Nueva Compra
    private Integer idcompraNuevo;
    private Date fechaNuevo;
    private Double totalNuevo;
    private String iddocumento;
    private String serieNuevo;
    private String correlativoNuevo;
    private int idalmacenNuevo;
    private int idempresaNuevo;
    private Integer estadoNuevo;

    //Busqueda
    private Date fechaInicioBusqueda;
    private Date fechaFinBusqueda;
    private int selectEstadoBusqueda = 100;
    private String serieBusqueda;
    private String correlativoBusqueda;
    private String nombreEmpresaBuqueda;

    private List<PealtipoitemDTO> listPealItem;
    private CompraDTO campos;
    private Empresa emp = new Empresa();
    private Documento doc = new Documento();
    private Almacen alm = new Almacen();
    //nuevos para edit
    private CompraDTO compraSelect;
    private List<LoteDTO> listaLoteDTO;
    private LoteDTO loteSelect;
    private List<PealtipoitemDTO> listPealItemTemp;
    private List<Lote> llTemp;
    private PealtipoitemDTO pealtiSelecDTO;

    private List<PealtipoitemDTO> listaPealTipoItemSelct;
//        private PealtipoitemDTO objPealtipoItemSelecDTO;
//        private PealtipoitemDTO objPealtipoItemQuitarDTO;

    private PealtipoitemDTO objPealTipoItem;
    private PealtipoitemDTO objPealTipoItemQuitar;
    private LoteDTO loteDTO;
    //
    private SessionBeanCompra sessionBeanCompra = new SessionBeanCompra();
    private boolean estadoEdit;
    private boolean estadoChoiceAlmacen;
    private boolean estadoBtnAgregar;
    private boolean estadoBtnQuitar;
    private boolean estadoBtnAgregarEdit;
    private boolean estadoBtnQuitarEdit;

    Utils ut = new Utils();

    @PostConstruct
    public void init() {
        estadoEdit = true;
        listaCompras = compraBO.getAllCompras();
        listaEmpresasProveedoras = empresaBO.findByConsulta(new EmpresaDTO((Integer) 3));
        camposAdd = new CompraDTO();
        listPealItem = new ArrayList<>();
        listaLoteDTO = new ArrayList<>();
        compraSelect = new CompraDTO();
        setListaUnidad(this.comboUnidades());
        limpiarCompras();
        emp = new Empresa();
        campos = new CompraDTO();
        getSessionBeanCompra().setListaEmpresaAdd(this.comboEmpresas());
        getSessionBeanCompra().setListaAlmacenesAdd(this.comboAlmacen());

        campos.setIdcompra(0);
        campos.setIdempresa(emp);
    }

    public void onRowSelectCot(SelectEvent event) {
        estadoEdit = false;
    }

    public List<CompraDTO> consultar(ActionEvent actionEvent) {
        // CompraDTO com = new CompraDTO();
        int estado = getSelectEstadoBusqueda();
        Date fechaInicio = getFechaInicioBusqueda();
        Date fechaFin = getFechaFinBusqueda();

        campos.setEstado(estado);
        campos.setFechaFin(fechaFin);
        campos.setFechaInicio(fechaInicio);

        System.out.println("nombre" + campos.getNombreEmpresa());
        listaCompras = compraBO.BuscarCompra(campos);
        return listaCompras;
    }

    public void cargarPedidosByAlmacen(ValueChangeEvent event) {
        if(event.getNewValue()==null){
             mostrarMensaje("Debes Seleccionar un Almacen", "msgRegCompra");
        return;
        }
        camposAdd.setIdAlmacen((int) event.getNewValue());
        listPealItem = compraBO.getPedidosbyAlmacen(camposAdd);
    }

    public void crear(ActionEvent actionEvent) {
        
        limpiaCrearCompra();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('addComprasModal').show();");
        context.update("formCompra:tabCompra");
    }

    private void limpiaCrearCompra() {
        compraSelect= new CompraDTO();
        camposAdd = new CompraDTO();
        listPealItem = new ArrayList<>();
        listaLoteDTO = new ArrayList<>();
        estadoChoiceAlmacen=false;
        estadoBtnAgregar=true;
        estadoBtnQuitar=true;
        listaAlmacenes=almacenBO.getAllAlmaces();
    }

    public List<EmpresaDTO> comboEmpresas() {
        List<EmpresaDTO> listaDto = empresaBO.getAllEmpresas();
        return listaDto;
    }

    public List<AlmacenDTO> comboAlmacen() {
        List<AlmacenDTO> listaDto = almacenBO.getAllAlmaces();
        return listaDto;
    }

    public List<UnidadDTO> comboUnidades() {
        List<UnidadDTO> listaDto = unidadBO.getAllUnidad();
        return listaDto;
    }

    public void selectTipoItemAdd(SelectEvent event) {
        estadoBtnAgregar=false;
        estadoBtnAgregarEdit=false; 
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formAddCompra:btnAgregarPedido");
         context.update("formEditCompra");
//        setObjPealTipoItem((PealtipoitemDTO) event.getObject());

    }

    public void selectTipoItemQuitar(SelectEvent event) {
        estadoBtnQuitar=false;
        estadoBtnQuitarEdit=false;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formAddCompra:btnQuitarPedido");
        context.update("formEditCompra:btnQuitarPedidoEdit");
// aqui colocar el control del Boton quitar

    }

    public void addNuevoCompra(ActionEvent actionEvent) {
        if(camposAdd.getIdEmpresa()==0){
            mostrarMensaje("Debes seleccionar un Proveedor", "msgRegCompra");
        return;
        }
        if(listaLoteDTO.isEmpty()){
            mostrarMensaje("Debes seleccionar al menos un pedido", "msgRegCompra");
        return;
        }
        for(LoteDTO ldto:listaLoteDTO){
            if(ldto.getCantidad()*ldto.getUnidadDTO().getUnidades()<ldto.getRequerimiento().getCantidad()){
            mostrarMensaje("Las cantidad a Comprar de "+ldto.getNombreTipoItem()+" debe ser mayor a la requerida", "msgRegCompra");
            return;
            }
        }
        camposAdd.setListaLoteDTO(listaLoteDTO);
        camposAdd.setEstado(2);
        compraBO.insertarNuevoCompra(camposAdd);

        listaCompras = compraBO.getAllCompras();
        mostrarMensaje("Se Envio la Compra", "formCompra;growlCompra");
        refreshCompras();
        this.cerrar();

    }

    public void guardarCompra(ActionEvent actionEvent) {
        if(camposAdd.getIdEmpresa()==0){
            mostrarMensaje("Debes seleccionar un Proveedor", "msgRegCompra");
        return;
        }
        if(listaLoteDTO.isEmpty()){
            mostrarMensaje("Debes seleccionar al menos un pedido", "msgRegCompra");
        return;
        }
        
        for(LoteDTO ldto:listaLoteDTO){
            if(ldto.getCantidad()*ldto.getUnidadDTO().getUnidades()<ldto.getRequerimiento().getCantidad()){
            mostrarMensaje("Las cantidad a Comprar de "+ldto.getNombreTipoItem()+" debe ser mayor a la requerida", "msgRegCompra");
            return;
            }
        }
        
        camposAdd.setListaLoteDTO(listaLoteDTO);
        camposAdd.setEstado(1);
        //persistimos a compra
        //persistimos los requerimientos y lotes y updateamos pealtipoitem
        compraBO.guardarCompra(camposAdd);

        listaCompras = compraBO.getAllCompras();
//        mostrarMensaje("Se Guardo correctamente la Compra", "formCompra;growlCompra");
        refreshCompras();
        this.cerrar();
    }

    private void refreshCompras() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Guardo Correctamente La Compra", ""));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCompra");
    }

    public List<PealtipoitemDTO> getListaPealtipoItemCompra(List<PealtipoitemDTO> listaTipoItem, int idCompra) {
        if (idCompra != 0) {
            for (PealtipoitemDTO dtoTI : listaTipoItem) {

                Compra entidadCompra = new Compra();

                entidadCompra.setIdcompra(idCompra);
            }
        } else {
            for (PealtipoitemDTO dtoTI : listaTipoItem) {
                setTotalNuevo(getTotalNuevo() + dtoTI.getTotal());
            }
        }

        return listaTipoItem;
    }

    public void cerrar() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('addComprasModal').hide();");
    }

    public void limpiar(ActionEvent actionEvent) {
//        RequestContext context = RequestContext.getCurrentInstance(); 
//        context.update("formCompra");
        limpiarCompras();
    }

    public void limpiarCompras() {

        campos = new CompraDTO();
        listaCompras = compraBO.getAllCompras();
        setFechaFinBusqueda(null);
        setFechaInicioBusqueda(null);
        setSelectEstadoBusqueda(100);

    }

    /////////////////// 
    public void edit(ActionEvent actionEvent) {
        if (compraSelect.getEstado() == 2) {
            mostrarMensaje("No puedes Editar una Compra Enviada","formCompra:growlCompra");
            return;
        }
        estadoChoiceAlmacen=true;
        estadoEdit = true;
        estadoBtnAgregarEdit=true;
        estadoBtnQuitarEdit=true;
        listPealItem = compraBO.getPedidosbyAlmacen(compraSelect);
        listaLoteDTO = compraBO.getLoteByCompra(compraSelect);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditCompra");
        context.update("formCompra");
        context.execute("PF('editComprasModal').show();");
        

    }

    private void mostrarMensaje(String msg,String idComponente) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(idComponente);
    }

    public void guardarEditar(ActionEvent actionEvent) {
        if(compraSelect.getIdEmpresa()==0){
            mostrarMensaje("Debes seleccionar un Proveedor", "msgEditCompra");
        return;
        }
        if(listaLoteDTO.isEmpty()){
            mostrarMensaje("Debes seleccionar al menos un pedido", "msgEditCompra");
        return;
        }
        for(LoteDTO ldto:listaLoteDTO){
            if(ldto.getCantidad()*ldto.getUnidadDTO().getUnidades()<ldto.getRequerimiento().getCantidad()){
            mostrarMensaje("La cantidad a Comprar "+ldto.getNombreTipoItem()+" debe ser mayor a la requerida", "msgEditCompra");
            return;
            }
        }
        guardarEditarCompra();
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCompra");
        context.execute("PF('editComprasModal').hide();");
        
    }

    private void guardarEditarCompra() {
        //quitamos el id de requirimientos a pealtipoitem y retornamos su estado
        //eliminamos los eliminamos los lotes y sus respectivos requerimientos de la compra
        compraBO.clearCompra(compraSelect);

        //cargamos la compra con la nueva lista de lotes que contiene los requerimientos pedidos
        compraSelect.setListaLoteDTO(listaLoteDTO);

        //edita la compra
        //guardamos los nuevos  requerimientos editando su id de requerimiento y estado , guardamos los lotes editados
        compraSelect.setEstado(1);
        compraBO.guardarEdit(compraSelect);
        mostrarMensaje("Se Edito correctamente la Compra", "formCompra;growlCompra");
    }

    public void agregarTipoItems(ActionEvent actionEvent) {
        
        if (listPealItem.isEmpty()) {
            mostrarMensaje("No hay Pedidos", "msgRegCompra");
            return;
        }
        if (objPealTipoItem == null) {
            mostrarMensaje("Debes Seleccionar un Pedido", "msgRegCompra");
            return;
        }
        estadoChoiceAlmacen=true;
        estadoBtnAgregar=true;
        estadoBtnAgregarEdit=true;
        for (LoteDTO dto : listaLoteDTO) {
            if (dto.getIdtipoitem().equals(objPealTipoItem.getIdtipoitem())) {
                dto.getRequerimiento().getPealtipoitemList().add(objPealTipoItem);
                dto.getRequerimiento().setCantidad(dto.getRequerimiento().getCantidad() + objPealTipoItem.getCantidad());
                dto.setCantidad(dto.getRequerimiento().getCantidad());
                listPealItem.remove(objPealTipoItem);
                return;
            }
        }
        loteDTO = new LoteDTO();
        loteDTO.getRequerimiento().getPealtipoitemList().add(objPealTipoItem);
        loteDTO.getRequerimiento().setCantidad(objPealTipoItem.getCantidad());
        loteDTO.setIdAlmacen(objPealTipoItem.getIdalmacen());
        loteDTO.setNombreTipoItem(objPealTipoItem.getNombreItems());
        loteDTO.setIdtipoitem(objPealTipoItem.getIdtipoitem());
        loteDTO.setCantidad(loteDTO.getRequerimiento().getCantidad());
        loteDTO.getUnidadDTO().setIdunidades(1);
        loteDTO.setPrecioUni(0.0);
        listPealItem.remove(objPealTipoItem);
        listaLoteDTO.add(loteDTO);
    }

    public void quitarPedido(ActionEvent actionEvent) {
        if (listaLoteDTO.isEmpty()) {
            return;
        }
        if (loteSelect == null) {
            
            return;
        }
        estadoBtnQuitar=true;
        estadoBtnQuitarEdit=true;
        for (PealtipoitemDTO pa : loteSelect.getRequerimiento().getPealtipoitemList()) {
            listPealItem.add(pa);
        }
        listaLoteDTO.remove(loteSelect);
        if(listaLoteDTO.isEmpty()){
            estadoChoiceAlmacen=false;
        }
    }

    public void editCompra(ActionEvent actionEvent) {
        CompraDTO dto = new CompraDTO();
        dto.setIdAlmacen(idalmacenNuevo);
        dto.setEstado(0);
//           dto.setCorrelativo(getCorrelativoNuevo());
        dto.setFecha(new Date());
//           dto.setSerie(getSerieNuevo());
        dto.setTotal(getTotalNuevo());
        dto.setIdEmpresa(getIdempresaNuevo());
        System.out.println("data: " + dto.getCorrelativo());

        Compra entidad = compraBO.insertarNuevoCompra(dto);

        getSessionBeanCompra().setListaCompra(compraBO.getAllCompras());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("tabCompraFrom");
        this.cerrar();

    }

    public void enviarEditar(ActionEvent actionEvent) {
        if(compraSelect.getIdEmpresa()==0){
            mostrarMensaje("Debes seleccionar un Proveedor", "msgEditCompra");
        return;
        }
        if(listaLoteDTO.isEmpty()){
            mostrarMensaje("Debes seleccionar al menos un pedido", "msgEditCompra");
        return;
        }
        for(LoteDTO ldto:listaLoteDTO){
            if(ldto.getCantidad()*ldto.getUnidadDTO().getUnidades()<ldto.getRequerimiento().getCantidad()){
            mostrarMensaje("Las cantidad a Comprar de "+ldto.getNombreTipoItem()+" debe ser mayor a la requerida", "msgEditCompra");
            return;
            }
        }
        //guardar la edicion usaremos el metodo para guardar
        guardarEditarCompra();
        //cambiamos el estado de la compra a enviada
        compraSelect.setEstado(2);
        compraBO.updateCompraAndAlmacen(compraSelect);
        mostrarMensaje("Se Envio correctamente la Compra", "formCompra;growlCompra");
        cerrarEdit();
        refreshCompras();

    }

    public void cerrarEdit() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('editComprasModal').hide();");
    }
    
    public void ver(ActionEvent actionEvent){
        if(compraSelect==null){
            mostrarMensaje("Debes seleccionar una Compra", "formCompra:growlCompra");
        return;
        }
        estadoEdit=true;
        listaLoteDTO=compraBO.getLoteByCompra(compraSelect);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCompra");
        context.execute("PF('verComprasModal').show();");
        context.update("formVerCompra");
    }
    
    
    

    public CompraBO getCompraBO() {
        return compraBO;
    }

    public void setCompraBO(CompraBO CompraBO) {
        this.compraBO = CompraBO;
    }

    public SessionBeanCompra getSessionBeanCompra() {
        return sessionBeanCompra;
    }

    public void setSessionBeanCompra(SessionBeanCompra sessionBeanCompra) {
        this.sessionBeanCompra = sessionBeanCompra;
    }

    public Utils getUt() {
        return ut;
    }

    public void setUt(Utils ut) {
        this.ut = ut;
    }

    public CompraDTO getCampos() {
        return campos;
    }

    public void setCampos(CompraDTO campos) {
        this.campos = campos;
    }

    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        this.emp = emp;
    }

    public Documento getDoc() {
        return doc;
    }

    public void setDoc(Documento doc) {
        this.doc = doc;
    }

    public Almacen getAlm() {
        return alm;
    }

    public void setAlm(Almacen alm) {
        this.alm = alm;
    }

    public int getCodigoAlamcen() {
        return codigoAlamacen;
    }

    public void setCodigoAlamcen(int codigoAlamcen) {
        this.codigoAlamacen = codigoAlamcen;
    }

    public String getNombreAlamcen() {
        return nombreAlamacen;
    }

    public void setNombreAlamcen(String nombreAlamcen) {
        this.nombreAlamacen = nombreAlamcen;
    }

    public int getCodigoAlamacen() {
        return codigoAlamacen;
    }

    public void setCodigoAlamacen(int codigoAlamacen) {
        this.codigoAlamacen = codigoAlamacen;
    }

    public String getNombreAlamacen() {
        return nombreAlamacen;
    }

    public void setNombreAlamacen(String nombreAlamacen) {
        this.nombreAlamacen = nombreAlamacen;
    }

    public EmpresaBO getEmpresaBO() {
        return empresaBO;
    }

    public void setEmpresaBO(EmpresaBO empresaBO) {
        this.empresaBO = empresaBO;
    }

    public AlmacenBO getAlmacenBO() {
        return almacenBO;
    }

    public void setAlmacenBO(AlmacenBO almacenBO) {
        this.almacenBO = almacenBO;
    }

    public PedidoaltipoitemBO getPedidoaltipoitemBO() {
        return pedidoaltipoitemBO;
    }

    public void setPedidoaltipoitemBO(PedidoaltipoitemBO pedidoaltipoitemBO) {
        this.pedidoaltipoitemBO = pedidoaltipoitemBO;
    }

    public List<AlmacenDTO> getListaAlmacenes() {
        return listaAlmacenes;
    }

    public void setListaAlmacenes(List<AlmacenDTO> listaAlmacenes) {
        this.listaAlmacenes = listaAlmacenes;
    }

    public CompraDTO getCamposAdd() {
        return camposAdd;
    }

    public void setCamposAdd(CompraDTO camposAdd) {
        this.camposAdd = camposAdd;
    }

    public Integer getIdcompraNuevo() {
        return idcompraNuevo;
    }

    public void setIdcompraNuevo(Integer idcompraNuevo) {
        this.idcompraNuevo = idcompraNuevo;
    }

    public Date getFechaNuevo() {
        return fechaNuevo;
    }

    public void setFechaNuevo(Date fechaNuevo) {
        this.fechaNuevo = fechaNuevo;
    }

    public Double getTotalNuevo() {
        return totalNuevo;
    }

    public void setTotalNuevo(Double totalNuevo) {
        this.totalNuevo = totalNuevo;
    }

    public String getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(String iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getSerieNuevo() {
        return serieNuevo;
    }

    public void setSerieNuevo(String serieNuevo) {
        this.serieNuevo = serieNuevo;
    }

    public String getCorrelativoNuevo() {
        return correlativoNuevo;
    }

    public void setCorrelativoNuevo(String correlativoNuevo) {
        this.correlativoNuevo = correlativoNuevo;
    }

    public int getIdalmacenNuevo() {
        return idalmacenNuevo;
    }

    public void setIdalmacenNuevo(int idalmacenNuevo) {
        this.idalmacenNuevo = idalmacenNuevo;
    }

    public int getIdempresaNuevo() {
        return idempresaNuevo;
    }

    public void setIdempresaNuevo(int idempresaNuevo) {
        this.idempresaNuevo = idempresaNuevo;
    }

    public Integer getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(Integer estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public List<PealtipoitemDTO> getListPealItem() {
        return listPealItem;
    }

    public void setListPealItem(List<PealtipoitemDTO> listPealItem) {
        this.listPealItem = listPealItem;
    }

    public CompraDTO getCompraSelect() {
        return compraSelect;
    }

    public void setCompraSelect(CompraDTO compraSelect) {
        this.compraSelect = compraSelect;
    }

    public List<PealtipoitemDTO> getListPealItemTemp() {
        return listPealItemTemp;
    }

    public void setListPealItemTemp(List<PealtipoitemDTO> listPealItemTemp) {
        this.listPealItemTemp = listPealItemTemp;
    }

    public List<Lote> getLlTemp() {
        return llTemp;
    }

    public void setLlTemp(List<Lote> llTemp) {
        this.llTemp = llTemp;
    }

    public PealtipoitemDTO getPealtiSelecDTO() {
        return pealtiSelecDTO;
    }

    public void setPealtiSelecDTO(PealtipoitemDTO pealtiSelecDTO) {
        this.pealtiSelecDTO = pealtiSelecDTO;
    }

    public CotizacionBO getCotizacionBO() {
        return cotizacionBO;
    }

    public void setCotizacionBO(CotizacionBO cotizacionBO) {
        this.cotizacionBO = cotizacionBO;
    }

    public Date getFechaInicioBusqueda() {
        return fechaInicioBusqueda;
    }

    public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
        this.fechaInicioBusqueda = fechaInicioBusqueda;
    }

    public Date getFechaFinBusqueda() {
        return fechaFinBusqueda;
    }

    public void setFechaFinBusqueda(Date fechaFinBusqueda) {
        this.fechaFinBusqueda = fechaFinBusqueda;
    }

    public int getSelectEstadoBusqueda() {
        return selectEstadoBusqueda;
    }

    public void setSelectEstadoBusqueda(int selectEstadoBusqueda) {
        this.selectEstadoBusqueda = selectEstadoBusqueda;
    }

    public String getSerieBusqueda() {
        return serieBusqueda;
    }

    public void setSerieBusqueda(String serieBusqueda) {
        this.serieBusqueda = serieBusqueda;
    }

    public String getCorrelativoBusqueda() {
        return correlativoBusqueda;
    }

    public void setCorrelativoBusqueda(String correlativoBusqueda) {
        this.correlativoBusqueda = correlativoBusqueda;
    }

    public String getNombreEmpresaBuqueda() {
        return nombreEmpresaBuqueda;
    }

    public void setNombreEmpresaBuqueda(String nombreEmpresaBuqueda) {
        this.nombreEmpresaBuqueda = nombreEmpresaBuqueda;
    }

    public List<PealtipoitemDTO> getListaPealTipoItemSelct() {
        return listaPealTipoItemSelct;
    }

    public void setListaPealTipoItemSelct(List<PealtipoitemDTO> listaPealTipoItemSelct) {
        this.listaPealTipoItemSelct = listaPealTipoItemSelct;
    }

    public PedidoBO getPedidoBO() {
        return pedidoBO;
    }

    public void setPedidoBO(PedidoBO pedidoBO) {
        this.pedidoBO = pedidoBO;
    }

    public PealtipoitemDTO getObjPealTipoItem() {
        return objPealTipoItem;
    }

    public void setObjPealTipoItem(PealtipoitemDTO objPealTipoItem) {
        this.objPealTipoItem = objPealTipoItem;
    }

    public UnidadBO getUnidadBO() {
        return unidadBO;
    }

    public void setUnidadBO(UnidadBO unidadBO) {
        this.unidadBO = unidadBO;
    }

    public List<UnidadDTO> getListaUnidad() {
        return listaUnidad;
    }

    public void setListaUnidad(List<UnidadDTO> listaUnidad) {
        this.listaUnidad = listaUnidad;
    }

    public PealtipoitemDTO getObjPealTipoItemQuitar() {
        return objPealTipoItemQuitar;
    }

    public void setObjPealTipoItemQuitar(PealtipoitemDTO objPealTipoItemQuitar) {
        this.objPealTipoItemQuitar = objPealTipoItemQuitar;
    }

    public List<CompraDTO> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<CompraDTO> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public List<EmpresaDTO> getListaEmpresasProveedoras() {
        return listaEmpresasProveedoras;
    }

    public void setListaEmpresasProveedoras(List<EmpresaDTO> listaEmpresasProveedoras) {
        this.listaEmpresasProveedoras = listaEmpresasProveedoras;
    }

    public List<LoteDTO> getListaLoteDTO() {
        return listaLoteDTO;
    }

    public void setListaLoteDTO(List<LoteDTO> listaLoteDTO) {
        this.listaLoteDTO = listaLoteDTO;
    }

    public LoteDTO getLoteDTO() {
        return loteDTO;
    }

    public void setLoteDTO(LoteDTO loteDTO) {
        this.loteDTO = loteDTO;
    }

    public LoteDTO getLoteSelect() {
        return loteSelect;
    }

    public void setLoteSelect(LoteDTO loteSelect) {
        this.loteSelect = loteSelect;
    }

    public boolean isEstadoEdit() {
        return estadoEdit;
    }

    public void setEstadoEdit(boolean estadoEdit) {
        this.estadoEdit = estadoEdit;
    }

    public boolean isEstadoChoiceAlmacen() {
        return estadoChoiceAlmacen;
    }

    public void setEstadoChoiceAlmacen(boolean estadoChoiceAlmacen) {
        this.estadoChoiceAlmacen = estadoChoiceAlmacen;
    }

    public boolean isEstadoBtnAgregar() {
        return estadoBtnAgregar;
    }

    public void setEstadoBtnAgregar(boolean estadoBtnAgregar) {
        this.estadoBtnAgregar = estadoBtnAgregar;
    }

    public boolean isEstadoBtnQuitar() {
        return estadoBtnQuitar;
    }

    public void setEstadoBtnQuitar(boolean estadoBtnQuitar) {
        this.estadoBtnQuitar = estadoBtnQuitar;
    }

    public boolean isEstadoBtnAgregarEdit() {
        return estadoBtnAgregarEdit;
    }

    public void setEstadoBtnAgregarEdit(boolean estadoBtnAgregarEdit) {
        this.estadoBtnAgregarEdit = estadoBtnAgregarEdit;
    }

    public boolean isEstadoBtnQuitarEdit() {
        return estadoBtnQuitarEdit;
    }

    public void setEstadoBtnQuitarEdit(boolean estadoBtnQuitarEdit) {
        this.estadoBtnQuitarEdit = estadoBtnQuitarEdit;
    }

}
