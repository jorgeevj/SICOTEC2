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
public class UbicacionDTO {

    private int idubicacion;

    private String nombre;

    private String numero;

    private String codDist;

    private String codProv;

    private String codDept;

    private int principal;

    private EmpresaDTO empresa;
    //extra
    private int idempresa;

    public UbicacionDTO() {
    }

    public UbicacionDTO(int idubicacion) {
        this.idubicacion = idubicacion;
    }
    
    public int getIdubicacion() {
        return idubicacion;
    }

    public void setIdubicacion(int idubicacion) {
        this.idubicacion = idubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodDist() {
        return codDist;
    }

    public void setCodDist(String codDist) {
        this.codDist = codDist;
    }

    public String getCodProv() {
        return codProv;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public String getCodDept() {
        return codDept;
    }

    public void setCodDept(String codDept) {
        this.codDept = codDept;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    

}
