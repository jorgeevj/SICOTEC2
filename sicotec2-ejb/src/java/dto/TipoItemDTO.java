/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Caracteristica;
import entidades.Categoria;
import entidades.Familia;
import entidades.Tipoitem;

/**
 *
 * @author Faviana
 */
public class TipoItemDTO {
    
    private String idtipoItem;
    private String nombre;
    private String tipo;
    private Double precioLista;
    private String desCliente;
    private String desDistribuidor;
    private Integer idCaracteristica;
    private Caracteristica caracteristica;
    private String descripcionCaracteristica;
    private Integer idFamilia;    
    private Familia familia;
    private Integer idMarca;
    private Integer idCategoria;
    private Categoria categoria;
    
    

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
    /**
     * @return the idtipoItem
     */
    public String getIdtipoItem() {
        return idtipoItem;
    }

    /**
     * @param idtipoItem the idtipoItem to set
     */
    public void setIdtipoItem(String idtipoItem) {
        this.idtipoItem = idtipoItem;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the precioLista
     */
    public Double getPrecioLista() {
        return precioLista;
    }

    /**
     * @param precioLista the precioLista to set
     */
    public void setPrecioLista(Double precioLista) {
        this.precioLista = precioLista;
    }

    /**
     * @return the desCliente
     */
    public String getDesCliente() {
        return desCliente;
    }

    /**
     * @param desCliente the desCliente to set
     */
    public void setDesCliente(String desCliente) {
        this.desCliente = desCliente;
    }

    /**
     * @return the desDistribuidor
     */
    public String getDesDistribuidor() {
        return desDistribuidor;
    }

    /**
     * @param desDistribuidor the desDistribuidor to set
     */
    public void setDesDistribuidor(String desDistribuidor) {
        this.desDistribuidor = desDistribuidor;
    }

    public Integer getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public String getDescripcionCaracteristica() {
        return descripcionCaracteristica;
    }

    public void setDescripcionCaracteristica(String descripcionCaracteristica) {
        this.descripcionCaracteristica = descripcionCaracteristica;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
    
    
    
    
    
}
