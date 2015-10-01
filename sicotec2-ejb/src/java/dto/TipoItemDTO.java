/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Faviana
 */
public class TipoItemDTO {
    
    private Integer idtipoItem;
    private String nombre;
    private String tipo;
    private Double precioLista;
    private String desCliente;
    private String desDistribuidor;
    
    /**
     * @return the idtipoItem
     */
    public Integer getIdtipoItem() {
        return idtipoItem;
    }

    /**
     * @param idtipoItem the idtipoItem to set
     */
    public void setIdtipoItem(Integer idtipoItem) {
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
    
    
    
}
