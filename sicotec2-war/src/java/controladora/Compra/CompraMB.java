/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Compra;

import Util.Utils;
import bo.AlmacenBO;
import bo.CompraBO;
import bo.EmpresaBO;
import bo.PedidoaltipoitemBO;
import dto.AlmacenDTO;
import dto.CompraDTO;
import dto.EmpresaDTO;
import entidades.Almacen;
import entidades.Compra;
import entidades.Documento;
import entidades.Empresa;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jc7
 */
@ManagedBean
@SessionScoped
public class CompraMB {
    
    private int codigoAlamacen;
    private String nombreAlamacen;
    
    

    @EJB
    private CompraBO compraBO;
    @EJB
    private EmpresaBO empresaBO;
    @EJB
    private AlmacenBO almacenBO;
    @EJB
    private PedidoaltipoitemBO pedidoaltipoitemBO;
    
     private List<CompraDTO> lista;
     private List<AlmacenDTO> listaAlmacenes;
     
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
     private Empresa idempresaNuevo;
     private Integer estadoNuevo;
    
     
    private List<Compra> listaCompra;
    private CompraDTO campos;
    private Empresa emp=new Empresa();
        private Documento doc=new Documento();
        private Almacen alm=new Almacen();
        
    private SessionBeanCompra sessionBeanCompra = new SessionBeanCompra();
    Utils ut = new Utils();
    
    
     @PostConstruct
    public void init(){
         getSessionBeanCompra().setListaTCompra(compraBO.getAllCompras());
         getSessionBeanCompra().setListaPealtipoitemAdd(pedidoaltipoitemBO.getAllPealtipoitems());
        emp=new Empresa();
        campos = new CompraDTO();
        getSessionBeanCompra().setListaEmpresaAdd(this.comboEmpresas());
        getSessionBeanCompra().setListaAlmacenesAdd(this.comboAlmacen());
        campos.setIdcompra(0);
        campos.setIdempresa(emp);
        
        camposAdd = new CompraDTO();
        camposAdd.setIdcompra(0);
        camposAdd.setIdempresa(emp);
       idempresaNuevo=emp;
    }
    public List<CompraDTO> consultar(ActionEvent actionEvent) {
        System.out.println("nombre"+campos.getIdempresa().getNombre());
            getSessionBeanCompra().setListaCompra(compraBO.BuscarCompra(campos));
            return getSessionBeanCompra().getListaCompra();
    }
    
    public void crear(ActionEvent actionEvent){
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addComprasModal').show();");
    }
    
    public List<EmpresaDTO> comboEmpresas(){
         List<EmpresaDTO> listaDto = empresaBO.getAllEmpresas();
         return listaDto;
     }
    
     public List<AlmacenDTO> comboAlmacen(){
         List<AlmacenDTO> listaDto = almacenBO.getAllAlmaces();
         return listaDto;
     }
    
     public void AlmacenForPedido(ActionEvent actionEvent){        
        
        AlmacenDTO lis=new AlmacenDTO();
        lis.setIdalmacen(codigoAlamacen);
        lis.setNombre(nombreAlamacen);
        
        
        listaAlmacenes=almacenBO.AlmacenForPedidos(lis);
       
    }
      public void addNuevoCompra(ActionEvent actionEvent){
          CompraDTO dto=new CompraDTO();
           dto.setIdalmacen(getIdalmacenNuevo());
           dto.setEstado(0);
           dto.setCorrelativo(getCorrelativoNuevo());
           dto.setFecha(new Date());
           dto.setSerie(getSerieNuevo());
           dto.setTotal(getTotalNuevo());
           dto.setIdempresa(getIdempresaNuevo());
           dto.setIddocumento(getIddocumento());
           System.out.println("data: " + dto.getCorrelativo());
           Compra entidad = compraBO.insertarNuevoCompra(dto);
        
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

    public Empresa getIdempresaNuevo() {
        return idempresaNuevo;
    }

    public void setIdempresaNuevo(Empresa idempresaNuevo) {
        this.idempresaNuevo = idempresaNuevo;
    }

    public Integer getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(Integer estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }
    
    
    
}
