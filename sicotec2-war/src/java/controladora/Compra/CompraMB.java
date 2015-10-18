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
import dto.AlmacenDTO;
import dto.CompraDTO;
import dto.EmpresaDTO;
import entidades.Almacen;
import entidades.Compra;
import entidades.Documento;
import entidades.Empresa;
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

    @EJB
    private CompraBO compraBO;
    @EJB
    private EmpresaBO empresaBO;
    @EJB
    private AlmacenBO almacenBO;
    
    
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
        emp=new Empresa();
        campos = new CompraDTO();
        getSessionBeanCompra().setListaEmpresaAdd(this.comboEmpresas());
        getSessionBeanCompra().setListaAlmacenesAdd(this.comboAlmacen());
        campos.setIdcompra(0);
        campos.setIdempresa(emp);
       
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
    
    
    
}
