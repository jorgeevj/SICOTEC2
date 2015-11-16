/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Almacen;

import bo.AlmacenBO;
import bo.UbigeoBO;
import dto.AlmacenDTO;
import entidades.Docalmacen;
import entidades.Documento;
import entidades.Ubigeo;
import java.util.ArrayList;
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

/**
 *
 * @author Faviana
 */
@ManagedBean
@SessionScoped
public class almacenMB {
    @EJB//falto esta notacion
    private AlmacenBO almacenBO;
    @EJB
    private UbigeoBO ubigeoBO;
    private String idAlmancen;
    private String nombre;
    private String telefono;
    private String direccion;
    private String cod_dept;
    private String cod_prov;
    private String cod_dist;
    
    private String codigoDocumento;
    private int serie;
    private int correlativo;
    
    private String departamentoSelect;
    private String provinciaSelect;
    private String distritoSelect;
    
    private boolean btnEditarEstado;
    
    private List<AlmacenDTO> lista=new ArrayList<AlmacenDTO>();
    private AlmacenDTO almacenSelect;
    private List<Ubigeo> listDepartamentos=new ArrayList<Ubigeo>();
    private List<Ubigeo> listProvincia=new ArrayList<Ubigeo>();
    private List<Ubigeo> listDistritos=new ArrayList<Ubigeo>();
   
    public almacenMB() {
    }
    
    @PostConstruct
    public void init(){
        lista=almacenBO.getAllAlmaces(); 
        listDepartamentos = ubigeoBO.getAllDepartamentos();       
        almacenSelect=new AlmacenDTO();
        btnEditarEstado=true;
        
    }
    
    public void onRowSelectAlmacen(){
        btnEditarEstado=false;
    }
    public void registrarAlmacen(){
        RequestContext context = RequestContext.getCurrentInstance();    
        context.execute("PF('registroAlmacen').show();");
    }
    
    public void editarAlmacen(){
        RequestContext context = RequestContext.getCurrentInstance();    
        context.execute("PF('editarAlmacen').show();");
        listProvincia=ubigeoBO.getAllProvincias(almacenSelect.getCodDept());
        listDistritos=ubigeoBO.getAllDistritos(almacenSelect.getCodDept(), almacenSelect.getCodProv());
    }
    
    public void cancelarRegistro(){
        RequestContext context = RequestContext.getCurrentInstance();    
        context.execute("PF('registroAlmacen').hide();");
        limpiar();
    }
    
    public void cancelarModificacion(){
        RequestContext context = RequestContext.getCurrentInstance();    
        context.execute("PF('editarAlmacen').hide();");
        limpiar();
    }
    
    public void limpiar(){
        setNombre("");
        setDireccion("");
        setTelefono("");
        setDireccion("");
        setDepartamentoSelect("");
        setProvinciaSelect("");
        setDistritoSelect("");
    }
    public void buscarAlmacen(ActionEvent actionEvent){
        AlmacenDTO lis= new AlmacenDTO();
        lis.setNombre(nombre);
        lis.setDireccion(direccion);
        lis.setCodDept(cod_dept);
        lis.setCodDist(cod_dist);
        lis.setCodProv(cod_prov);
        lista=almacenBO.buscarAlmacen(lis);
    }
    
    public void registrarNuevoAlmacen(){
        String sms="";
        sms=validarRegistroAlmacen();
        if(sms!=""){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, sms, "!!!"));
        }else{
            AlmacenDTO objAlmacen=new AlmacenDTO();
            objAlmacen.setNombre(nombre);
            objAlmacen.setTelefono(telefono);
            objAlmacen.setDireccion(direccion);
            objAlmacen.setCodDept(departamentoSelect);
            objAlmacen.setCodProv(provinciaSelect);
            objAlmacen.setCodDist(distritoSelect);
            almacenBO.registrarAlmacen(objAlmacen);
            lista=almacenBO.getAllAlmaces();
            RequestContext context = RequestContext.getCurrentInstance();    
            context.update("panelA");
            context.execute("PF('registroAlmacen').hide();");
            limpiar();
        }
    }
    
    public void modificarNuevoAlmacen(){
        String sms="";
        sms=validarModificarAlmacen();
        if(sms!=""){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, sms, "!!!"));
        }else{
            AlmacenDTO objAlmacen=new AlmacenDTO();
            objAlmacen.setNombre(almacenSelect.getNombre());
            objAlmacen.setTelefono(almacenSelect.getTelefono());
            objAlmacen.setDireccion(almacenSelect.getDireccion());
            objAlmacen.setCodDept(almacenSelect.getCodDept());
            objAlmacen.setCodProv(almacenSelect.getCodProv());
            objAlmacen.setCodDist(almacenSelect.getCodDist());
            almacenBO.modificarAlmacen(objAlmacen);
            lista=almacenBO.getAllAlmaces();
            RequestContext context = RequestContext.getCurrentInstance();    
            context.update("panelA");
            context.execute("PF('editarAlmacen').hide();");
            limpiar();
        }
        
    }
    
    
    public String validarRegistroAlmacen(){
        String msjError="";
        
        if(getNombre().equals(""))
            msjError="Ingrese un nombre";
        else if(buscarPorNom())
            msjError="El nombre ya existe";
        else if(getTelefono().equals("") )
            msjError="Ingrese un telefono";
        else if(getDireccion().equals(""))
            msjError="Ingrese una direccion";
        else if(Integer.parseInt(getDepartamentoSelect())==0)
            msjError="Seleccione un departamento";
        else if(Integer.parseInt(getProvinciaSelect())==0)
            msjError="Seleccione una provincia";
        else if(Integer.parseInt(getDistritoSelect())==0)
            msjError="Seleccione un distrito";
        return msjError;
    }
    public boolean buscarPorNom(){
        
        boolean encontro=almacenBO.buscarAlmacenNom(nombre.trim());
        return encontro;
    }
    
    public String validarModificarAlmacen(){
        String msjError="";
        if(almacenSelect.getNombre().equals(""))
            msjError="Ingrese un nombre para almacen";
        else if(buscarPorNom())
            msjError="El nombre ya existe";
        else if(almacenSelect.getTelefono().equals("") )
            msjError="Ingrese un telefono";
        else if(almacenSelect.getDireccion().equals(""))
            msjError="Ingrese una direccion";
        else if(Integer.parseInt(almacenSelect.getCodDept())==0)
            msjError="Seleccione un departamento";
        else if(Integer.parseInt(almacenSelect.getCodProv())==0)
            msjError="Seleccione una provincia";
        else if(Integer.parseInt(almacenSelect.getCodDist())==0)
            msjError="Seleccione un distrito";
        return msjError;
    }
    public void cargarProvincias(ValueChangeEvent event) {
        listProvincia = ubigeoBO.getAllProvincias(event.getNewValue().toString());
        listDistritos = new ArrayList<Ubigeo>();
        provinciaSelect="";
    }
    public void cargarDistritos(ValueChangeEvent event) {
        listDistritos = ubigeoBO.getAllDistritos(departamentoSelect, event.getNewValue().toString());
    }
    
    //////////////////////////////////////////////////
    public String getIdAlmancen() {
        return idAlmancen;
    }

    public void setIdAlmancen(String idAlmancen) {
        this.idAlmancen = idAlmancen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCod_dept() {
        return cod_dept;
    }

    public void setCod_dept(String cod_dept) {
        this.cod_dept = cod_dept;
    }

    public String getCod_prov() {
        return cod_prov;
    }

    public void setCod_prov(String cod_prov) {
        this.cod_prov = cod_prov;
    }

    public String getCod_dist() {
        return cod_dist;
    }

    public void setCod_dist(String cod_dist) {
        this.cod_dist = cod_dist;
    }

    public String getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(String codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public List<AlmacenDTO> getLista() {
        return lista;
    }

    public void setLista(List<AlmacenDTO> lista) {
        this.lista = lista;
    }

    public AlmacenDTO getAlmacenSelect() {
        return almacenSelect;
    }

    public void setAlmacenSelect(AlmacenDTO almacenSelect) {
        this.almacenSelect = almacenSelect;
    }

    public AlmacenBO getAlmacenBO() {
        return almacenBO;
    }

    public void setAlmacenBO(AlmacenBO almacenBO) {
        this.almacenBO = almacenBO;
    }

    public List<Ubigeo> getListDepartamentos() {
        return listDepartamentos;
    }

    public void setListDepartamentos(List<Ubigeo> listDepartamentos) {
        this.listDepartamentos = listDepartamentos;
    }

    public List<Ubigeo> getListProvincia() {
        return listProvincia;
    }

    public void setListProvincia(List<Ubigeo> listProvincia) {
        this.listProvincia = listProvincia;
    }

    public List<Ubigeo> getListDistritos() {
        return listDistritos;
    }

    public void setListDistritos(List<Ubigeo> listDistritos) {
        this.listDistritos = listDistritos;
    }

    public UbigeoBO getUbigeoBO() {
        return ubigeoBO;
    }

    public void setUbigeoBO(UbigeoBO ubigeoBO) {
        this.ubigeoBO = ubigeoBO;
    }

    public String getDepartamentoSelect() {
        return departamentoSelect;
    }

    public void setDepartamentoSelect(String departamentoSelect) {
        this.departamentoSelect = departamentoSelect;
    }

    public String getProvinciaSelect() {
        return provinciaSelect;
    }

    public void setProvinciaSelect(String provinciaSelect) {
        this.provinciaSelect = provinciaSelect;
    }

    public String getDistritoSelect() {
        return distritoSelect;
    }

    public void setDistritoSelect(String distritoSelect) {
        this.distritoSelect = distritoSelect;
    }

    public boolean isBtnEditarEstado() {
        return btnEditarEstado;
    }

    public void setBtnEditarEstado(boolean btnEditarEstado) {
        this.btnEditarEstado = btnEditarEstado;
    }
    
}
