/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Cotipoitem;
import entidades.Empresa;
import entidades.Evento;
import entidades.Pedido;
import entidades.Persona;
import entidades.Rol;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class UsuarioDTO implements Serializable{
private Integer idusuario;
    private String nombre;
    private String clave;
    private Persona idpersona;
    private Rol idrol;
    
    //EXTRAS
    private int idRol;
    
    //
    private List<Evento> eventoList;

    /**
     * @return the idusuario
     */
    public Integer getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
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
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the idpersona
     */
    public Persona getIdpersona() {
        return idpersona;
    }

    /**
     * @param idpersona the idpersona to set
     */
    public void setIdpersona(Persona idpersona) {
        this.idpersona = idpersona;
    }

    /**
     * @return the idrol
     */
    public Rol getIdrol() {
        return idrol;
    }

    /**
     * @param idrol the idrol to set
     */
    public void setIdrol(Rol idrol) {
        this.idrol = idrol;
    }

    /**
     * @return the eventoList
     */
    public List<Evento> getEventoList() {
        return eventoList;
    }

    /**
     * @param eventoList the eventoList to set
     */
    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    /**
     * @return the idRol
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
}
