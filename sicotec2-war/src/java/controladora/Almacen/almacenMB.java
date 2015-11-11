/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Almacen;

import bo.AlmacenBO;
import dto.AlmacenDTO;
import entidades.Docalmacen;
import entidades.Documento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Faviana
 */
@ManagedBean
@SessionScoped
public class almacenMB {
    @EJB//falto esta notacion
    private AlmacenBO almacenBO;
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
    
    private String documentoSelect;
    
    private List<AlmacenDTO> lista;
    private AlmacenDTO almacenSelect;
    private List<Documento> listaDocumento;
    private List<Docalmacen> lista2;
    public almacenMB() {
    }
    
    @PostConstruct
    public void init(){
        lista=almacenBO.getAllAlmaces();
        listarDocumento();
        almacenSelect=new AlmacenDTO();
        almacenSelect.setDocumento(new Documento());
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
        AlmacenDTO objAlmacen=new AlmacenDTO();
        objAlmacen.setNombre(nombre);
        objAlmacen.setTelefono(telefono);
        objAlmacen.setDireccion(direccion);
        objAlmacen.setCodDept(cod_dept);
        objAlmacen.setCodProv(cod_prov);
        objAlmacen.setCodDist(cod_dist);
        almacenBO.registrarAlmacen(objAlmacen, lista2);
    }
    
    public void modificarAlmacen(){
        AlmacenDTO objAlmacen=new AlmacenDTO();
        objAlmacen.setNombre(almacenSelect.getNombre());
        objAlmacen.setTelefono(almacenSelect.getTelefono());
        objAlmacen.setDireccion(almacenSelect.getDireccion());
        objAlmacen.setCodDept(almacenSelect.getCodDept());
        objAlmacen.setCodProv(almacenSelect.getCodProv());
        objAlmacen.setCodDist(almacenSelect.getCodDist());
        almacenBO.modificarAlmacen(objAlmacen, lista2);
    }
    
    public void registrarDocAlmacen(){
        
    }
    public String validarRegistroAlmacen(){
        String msjError="";
        return msjError;
    }
    
    public String validarModificarAlmacen(){
        String msjError="";
        return msjError;
    }
    public void listarDocumento(){
        listaDocumento=almacenBO.getNombreDocumento();
    }
    
   /* public List<Documento> listarTablaDocumentos(ActionEvent actionEvent){
        
        Documento obj1=new Documento();
        Docalmacen obj2=new Docalmacen();
        obj1=almacenBO.getDocumentoXID(Integer.parseInt(documentoSelect));
        obj2.setSerie(serie);
        obj2.setCorrelativo(correlativo);
        lista2.add(obj);
        
        for(Caracteristica ite: listaCaracteristicas){
            if(ite.getIdcaracteristica()==obj.getIdcaracteristica())
            {
                listaCaracteristicas.remove(ite);
                break;
            }
        }
        
       return lista2;
    }*/
   //////
   
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

    public List<Documento> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<Documento> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public List<Docalmacen> getLista2() {
        return lista2;
    }

    public void setLista2(List<Docalmacen> lista2) {
        this.lista2 = lista2;
    }

    public String getDocumentoSelect() {
        return documentoSelect;
    }

    public void setDocumentoSelect(String documentoSelect) {
        this.documentoSelect = documentoSelect;
    }
    
}
