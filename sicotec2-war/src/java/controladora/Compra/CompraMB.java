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
import dto.loteDTO;
import entidades.Almacen;
import entidades.Altipoitem;
import entidades.Compra;
import entidades.Docalmacen;
import entidades.Documento;
import entidades.Empresa;
import entidades.Lote;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
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
    
     private List<CompraDTO> lista;
     private List<AlmacenDTO> listaAlmacenes;
      private ArrayList listaEstados = new ArrayList();
      private List<UnidadDTO>listaUnidad;
     
     
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
    private List<Compra> listaCompra;
    private CompraDTO campos;
    private Empresa emp=new Empresa();
    private Documento doc=new Documento();
    private Almacen alm=new Almacen();
    //nuevos para edit
    private CompraDTO co;    
    private List<Lote> ll;
    private List<PealtipoitemDTO> listPealItemTemp;
    private List<Lote> llTemp;
    private PealtipoitemDTO pealtiSelecDTO;
    private Lote loteSelec;
    private List<PealtipoitemDTO> listaPealTipoItemSelct;
//        private PealtipoitemDTO objPealtipoItemSelecDTO;
//        private PealtipoitemDTO objPealtipoItemQuitarDTO;
 
     private PealtipoitemDTO objPealTipoItem;
     private PealtipoitemDTO objPealTipoItemQuitar;
     private loteDTO objLote;
    //
    private SessionBeanCompra sessionBeanCompra = new SessionBeanCompra();
    Utils ut = new Utils();
    
    
     @PostConstruct
    public void init(){
         getSessionBeanCompra().setListaCompra(compraBO.getAllCompras());
         getSessionBeanCompra().setListaPealtipoitemAdd(pedidoaltipoitemBO.getAllPealtipoitems());
           setListaEstados(this.llenarEstados());
           setListaUnidad(this.comboUnidades());
           limpiarCompras();
        emp=new Empresa();
        campos = new CompraDTO();
        getSessionBeanCompra().setListaEmpresaAdd(this.comboEmpresas());
        getSessionBeanCompra().setListaAlmacenesAdd(this.comboAlmacen());
      
        campos.setIdcompra(0);
        campos.setIdempresa(emp);
        
        camposAdd = new CompraDTO();
        camposAdd.setIdcompra(0);
        camposAdd.setIdempresa(emp);
        
        co=new CompraDTO();
        ll=new ArrayList<>();
       // objPealTipoItemQuitar=new PealtipoitemDTO();
        
    }
    
    public List<CompraDTO> consultar(ActionEvent actionEvent) {
         // CompraDTO com = new CompraDTO();
        int estado      = getSelectEstadoBusqueda();
        Date fechaInicio = getFechaInicioBusqueda();
        Date fechaFin = getFechaFinBusqueda();
       
        campos.setEstado(estado);
        campos.setFechaFin(fechaFin);
        campos.setFechaInicio(fechaInicio);
        
        System.out.println("nombre"+campos.getNombreEmpresa());
            getSessionBeanCompra().setListaCompra(compraBO.BuscarCompra(campos));
            return getSessionBeanCompra().getListaCompra();
    }
    
//    public void OpenAddPedido(ActionEvent actionEvent){
//        RequestContext context = RequestContext.getCurrentInstance(); 
//        context.update("formAddPedidoss");
//        context.execute("PF('addPedidosItemsModal').show();");
//    
//    }
    public void crear(ActionEvent actionEvent){
       limpiaCrearCompra();
        
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addComprasModal').show();");
    }
    private void limpiaCrearCompra() {
        setListaPealTipoItemSelct(new ArrayList<PealtipoitemDTO>());
        idempresaNuevo=0;
        idalmacenNuevo=0;
        }
    public List<EmpresaDTO> comboEmpresas(){
         List<EmpresaDTO> listaDto = empresaBO.getAllEmpresas();
         return listaDto;
     }
    
     public List<AlmacenDTO> comboAlmacen(){
         List<AlmacenDTO> listaDto = almacenBO.getAllAlmaces();
         return listaDto;
     }
     public List<UnidadDTO> comboUnidades(){
         List<UnidadDTO> listaDto = unidadBO.getAllUnidad();
         return listaDto;
     }
     public void selectTipoItemAdd(SelectEvent event){
         setObjPealTipoItem((PealtipoitemDTO)event.getObject());
     
     }
     public void selectTipoItemQuitar(SelectEvent event){
         setObjPealTipoItemQuitar((PealtipoitemDTO)event.getObject());
     
     }
    
     public void getAlmacenForPedidoForItems(ActionEvent actionEvent){        
        
        PealtipoitemDTO lis=new PealtipoitemDTO();
         Altipoitem alti = new Altipoitem();
            Almacen alm = new Almacen();
            alm.setIdalmacen(getIdalmacenNuevo());
         alti.setAlmacen(alm);
        lis.setAltipoitem(alti);
        
       //AlmacenDTO lis=new AlmacenDTO();
//       lis.setIdalmacen(codigoAlamacen);
//        lis.setNombre(nombreAlamacen);
////       
        
         setListPealItem(pedidoaltipoitemBO.AlmacenForPedidos(lis));
         setListaPealTipoItemSelct(new ArrayList<PealtipoitemDTO>());
       RequestContext context = RequestContext.getCurrentInstance(); 
       context.execute("PF('addPedidosItemsModal').show();");
        context.update("formAddPedidoss");
    }
      public void addNuevoCompra(ActionEvent actionEvent){
          CompraDTO dto=new CompraDTO();
           dto.setIdAlmacen(idalmacenNuevo);
           dto.setEstado(0);
//           dto.setCorrelativo(getCorrelativoNuevo());
           dto.setFecha(new Date());
//           dto.setSerie(getSerieNuevo());
           
             //Insert Update PealTipoitem
        //   List<PealtipoitemDTO> listAux = this.getListaPealtipoItemCompra(getListaPealTipoItemSelct(), 0);
           
           
           
           dto.setTotal(getTotalNuevo());
           dto.setIdEmpresa(getIdempresaNuevo());
           System.out.println("data: " + dto.getCorrelativo());
           
           Compra entidad = compraBO.insertarNuevoCompra(dto);
           /*List<PealtipoitemDTO> listaPealDTO = this.getListaPealtipoItemCompra(getListaPealTipoItemSelct(), entidad.getIdcompra());
           pedidoBO.updatePedidosItems(listaPealDTO);*/
           
        getSessionBeanCompra().setListaCompra(compraBO.getAllCompras());
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.update("formCompra");
        this.cerrar();
        
    }
      public List<PealtipoitemDTO> getListaPealtipoItemCompra(List<PealtipoitemDTO> listaTipoItem,int idCompra){
          if(idCompra != 0){
              for(PealtipoitemDTO dtoTI : listaTipoItem){
                    
                Compra entidadCompra = new Compra();
                    
                entidadCompra.setIdcompra(idCompra);
             }
          } else{
              for(PealtipoitemDTO dtoTI : listaTipoItem){
                    setTotalNuevo(getTotalNuevo()+dtoTI.getTotal());
             }
          }
          
            return listaTipoItem;
        }
 
    
      public void cerrar(){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addComprasModal').hide();");
    }
      public ArrayList llenarEstados() {
        ArrayList estados = new ArrayList();
        estados.add(new SelectItem(0,"Creado"));
        estados.add(new SelectItem(1,"Enviado"));
    
        return estados;
    }
      
    public void limpiar(ActionEvent actionEvent){
//        RequestContext context = RequestContext.getCurrentInstance(); 
//        context.update("formCompra");
        limpiarCompras();           
    }
    public void limpiarCompras(){
    
        campos=new CompraDTO();
        lista=compraBO.getAllCompras();
        setFechaFinBusqueda(null);
        setFechaInicioBusqueda(null);
        setSelectEstadoBusqueda(100); 
           
    }
     /////////////////// 
      public void edit(ActionEvent actionEvent){
        listPealItem=compraBO.getPedidosbyAlmacen(co);
        ll= compraBO.getLoteByCompra(co);
          
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditCompra");
        context.execute("PF('EditComprasModal').show();");
        
      }
       public void agregarTipoItems(ActionEvent actionEvent){
           if(!getListPealItem().isEmpty()){
               if(getObjPealTipoItem()!=null){
                   getListPealItem().remove(getObjPealTipoItem());
                   getListaPealTipoItemSelct().add(getObjPealTipoItem());

              RequestContext context = RequestContext.getCurrentInstance();
              context.update("formAddPedidoss");
              setObjPealTipoItem(new PealtipoitemDTO());
               }
           }            
      }
      public void quitarPedido(ActionEvent actionEvent){
          if(!getListaPealTipoItemSelct().isEmpty()){
              if(getObjPealTipoItemQuitar()!=null){
                  getListPealItem().add(getObjPealTipoItemQuitar());
                  getListaPealTipoItemSelct().remove(getObjPealTipoItemQuitar());

                  RequestContext context = RequestContext.getCurrentInstance();
                  context.update("formAddPedidoss");
                  setObjPealTipoItemQuitar(new PealtipoitemDTO());
          }  
        }
      }
      public void editCompra(ActionEvent actionEvent){
          CompraDTO dto=new CompraDTO();
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
      public void cerrarEdit(){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('EditComprasModal').hide();");
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

    public List<Compra> getListaCompra() {
        return listaCompra;
    }

    public void setListaCompra(List<Compra> listaCompra) {
        this.listaCompra = listaCompra;
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

    public List<CompraDTO> getLista() {
        return lista;
    }

    public void setLista(List<CompraDTO> lista) {
        this.lista = lista;
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


    public CompraDTO getCo() {
        return co;
    }

    public void setCo(CompraDTO co) {
        this.co = co;
    }

    public List<Lote> getLl() {
        return ll;
    }

    public void setLl(List<Lote> ll) {
        this.ll = ll;
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

    public Lote getLoteSelec() {
        return loteSelec;
    }

    public void setLoteSelec(Lote loteSelec) {
        this.loteSelec = loteSelec;
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

    public ArrayList getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(ArrayList listaEstados) {
        this.listaEstados = listaEstados;
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

    public loteDTO getObjLote() {
        return objLote;
    }

    public void setObjLote(loteDTO objLote) {
        this.objLote = objLote;
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

    

    
    
        
}
