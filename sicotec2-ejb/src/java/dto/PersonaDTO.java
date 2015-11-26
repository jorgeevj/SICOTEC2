/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Telefpersona;
import entidades.Ubipers;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jc7
 */
public class PersonaDTO {
    private Integer idpersona;
     private String nombre;
     private String apellido;
     private String dni;
     private String email;
     
     private List<Ubipers> ubipersList;
     private List<Telefpersona> telefpersonaList;

    public PersonaDTO() {
    }

    public PersonaDTO(Integer idpersona, String nombre, String apellido, String dni, String email) {
        this.idpersona = idpersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ubipers> getUbipersList() {
        return ubipersList;
    }

    public void setUbipersList(List<Ubipers> ubipersList) {
        this.ubipersList = ubipersList;
    }

    public List<Telefpersona> getTelefpersonaList() {
        return telefpersonaList;
    }

    public void setTelefpersonaList(List<Telefpersona> telefpersonaList) {
        this.telefpersonaList = telefpersonaList;
    }
}
