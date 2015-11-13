/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Persona;

/**
 *
 * @author Cesar
 */
public class TelefpersDTO {
    private Integer idtelefPersona;
    
    private String numero;
    
    private String tipo;
    
    private String operador;
    
    private Integer principal;
    
    private Persona idpersona;

    public Integer getIdtelefPersona() {
        return idtelefPersona;
    }

    public void setIdtelefPersona(Integer idtelefPersona) {
        this.idtelefPersona = idtelefPersona;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    public Persona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Persona idpersona) {
        this.idpersona = idpersona;
    }
    
    
}
