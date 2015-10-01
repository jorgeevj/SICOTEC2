/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author rikardo308
 */
public class AlmacenDTO {
    private Integer idalmacen;
    private String nombre;
    private String telefono;
    private String direccion;
    private String codDept;
    private String codProv;

    /**
     * @return the idalmacen
     */
    public Integer getIdalmacen() {
        return idalmacen;
    }

    /**
     * @param idalmacen the idalmacen to set
     */
    public void setIdalmacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
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
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the codDept
     */
    public String getCodDept() {
        return codDept;
    }

    /**
     * @param codDept the codDept to set
     */
    public void setCodDept(String codDept) {
        this.codDept = codDept;
    }

    /**
     * @return the codProv
     */
    public String getCodProv() {
        return codProv;
    }

    /**
     * @param codProv the codProv to set
     */
    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }
    
    
}
