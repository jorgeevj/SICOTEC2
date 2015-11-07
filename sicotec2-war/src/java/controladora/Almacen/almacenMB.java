/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Almacen;

import bo.AlmacenBO;
import dto.AlmacenDTO;
import entidades.Documento;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Faviana
 */
@ManagedBean
@RequestScoped
public class almacenMB {

    private AlmacenBO almacenBO;
    private String idAlmancen;
    private String nombre;
    private String telefono;
    private String direccion;
    private String cod_dept;
    private String cod_prov;
    private String cod_dist;
    
    private String codigoDocumento;
    private String serie;
    private String correlativo;
    
    private List<AlmacenDTO> lista;
    private AlmacenDTO almacenSelect;
    private List<Documento> listaDocumento;
    public almacenMB() {
    }
    
    public void init(){
        lista = new ArrayList<>();
        almacenSelect=new AlmacenDTO();
        almacenSelect.setDocumento(new Documento());
    }
    
    public void listarDocumento(){
        listaDocumento=almacenBO.getNombreDocumento();
    }

    public AlmacenBO getAlmacenBO() {
        return almacenBO;
    }

    public void setAlmacenBO(AlmacenBO almacenBO) {
        this.almacenBO = almacenBO;
    }

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

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
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
    
}
