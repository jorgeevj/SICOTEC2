/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author Jorge
 */
public class EmppersonaDTO {

    private EmpresaDTO empresa;

    private PersonaDTO persona;
    
    private String cargo;

    //extras
    private int idempresa;
    private int idpersona;

    public EmppersonaDTO() {
    
    }

    public EmppersonaDTO(PersonaDTO persona, String cargo, int idpersona) {
        this.persona = persona;
        this.cargo = cargo;
        this.idpersona = idpersona;
    }
    
    
    public EmppersonaDTO(int idempresa, int idpersona) {
        this.idempresa = idempresa;
        this.idpersona = idpersona;
        
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}
