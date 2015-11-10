/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Caracteristica;
import entidades.Categoria;
import entidades.Color;
import entidades.Familia;
import entidades.Marca;
import entidades.Tipoitem;

/**
 *
 * @author Faviana
 */
public class TipoItemDTO {
    
    private String idtipoItem;
    private String numParte;
    private String nombre;
    private String descipcion;
    private String tipo;
    private Double precioLista;
    private Double desCliente;
    private Double desDistribuidor;
    
    private Integer idFamilia; 
    private Integer idMarca;
    private Integer idColor;    
    private Integer idCaracteristica; 
    private Integer idCategoria;
    
    private Caracteristica caracteristica;   
    private Familia familia;
    private Marca marca;
    private Color color;
    private Categoria categoria;
    
    private int idLote;
    
    //EXTRAS
    private Integer cantidad;
    
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
    
    
    
    
    public String getIdtipoItem() {
        return idtipoItem;
    }

    
    public void setIdtipoItem(String idtipoItem) {
        this.idtipoItem = idtipoItem;
    }

    
    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getTipo() {
        return tipo;
    }

    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    public Double getPrecioLista() {
        return precioLista;
    }

    
    public void setPrecioLista(Double precioLista) {
        this.precioLista = precioLista;
    }

    
    public Double getDesCliente() {
        return desCliente;
    }

    
    public void setDesCliente(Double desCliente) {
        this.desCliente = desCliente;
    }

    
    public Double getDesDistribuidor() {
        return desDistribuidor;
    }

    
    public void setDesDistribuidor(Double desDistribuidor) {
        this.desDistribuidor = desDistribuidor;
    }

    public Integer getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
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

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public String getNumParte() {
        return numParte;
    }

    public void setNumParte(String numParte) {
        this.numParte = numParte;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the idLote
     */
    public int getIdLote() {
        return idLote;
    }

    /**
     * @param idLote the idLote to set
     */
    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    
    
    
    
    
    
}
