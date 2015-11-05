/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Jorge
 */
public class TelefonoDTO {
  private int idtelefono;
   
    private String numero;
   
    private String tipo;
    
    private String operador;
   
    private String principal; 
    
    private EmpresaDTO empresa;
    
    //extras;
    private int idempresa;

    public TelefonoDTO() {
    }
    
    public int getIdtelefono() {
        return idtelefono;
    }

    public void setIdtelefono(int idtelefono) {
        this.idtelefono = idtelefono;
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

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }
    
}
