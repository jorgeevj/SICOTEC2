/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Altipoitem;
import entidades.Docalmacen;
import entidades.Documento;
import entidades.Pealtipoitem;
import entidades.Tipoitem;
import java.util.Collection;

/**
 *
 * @author Jorge
 */
public class AlmacenDTO {
   private int idalmacen;
  
    private String nombre;
   
    private String telefono;
   
    private String direccion;
   
    private String codDept;
   
    private String codProv;
   
    private String codDist;
    
    private Documento documento;
    
    //EXTRAS
    private Pealtipoitem pealtipoitem;
    private Altipoitem altipoitem;
    private Tipoitem tipoItem;

    public AlmacenDTO() {
    }

    public int getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(int idalmacen) {
        this.idalmacen = idalmacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodDept() {
        return codDept;
    }

    public void setCodDept(String codDept) {
        this.codDept = codDept;
    }

    public String getCodProv() {
        return codProv;
    }

    public void setCodProv(String codProv) {
        this.codProv = codProv;
    }

    public String getCodDist() {
        return codDist;
    }

    public void setCodDist(String codDist) {
        this.codDist = codDist;
    }

    public Pealtipoitem getPealtipoitem() {
        return pealtipoitem;
    }

    public void setPealtipoitem(Pealtipoitem pealtipoitem) {
        this.pealtipoitem = pealtipoitem;
    }

    public Altipoitem getAltipoitem() {
        return altipoitem;
    }

    public void setAltipoitem(Altipoitem altipoitem) {
        this.altipoitem = altipoitem;
    }

    public Tipoitem getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(Tipoitem tipoItem) {
        this.tipoItem = tipoItem;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
   
   
  
}
