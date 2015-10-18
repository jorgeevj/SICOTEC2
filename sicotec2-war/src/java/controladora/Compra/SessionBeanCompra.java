/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Compra;

import dto.AlmacenDTO;
import dto.CompraDTO;
import dto.EmpresaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jc7
 */
@ManagedBean
@SessionScoped
public class SessionBeanCompra {
    
     private List<CompraDTO> listaCompra = new ArrayList<CompraDTO>();
     private List<EmpresaDTO>listaEmpresaAdd=new ArrayList<EmpresaDTO>();
     private List<AlmacenDTO> listaAlmacenesAdd = new ArrayList<AlmacenDTO>();
   
    public List<CompraDTO> getListaTCompra() {
        return listaCompra;
    }

  
    public void setListaTCompra(List<CompraDTO> listaTCompra) {
        this.listaCompra = listaTCompra;
    }

    public List<CompraDTO> getListaCompra() {
        return listaCompra;
    }

    public void setListaCompra(List<CompraDTO> listaCompra) {
        this.listaCompra = listaCompra;
    }

    

    public List<AlmacenDTO> getListaAlmacenesAdd() {
        return listaAlmacenesAdd;
    }

    public void setListaAlmacenesAdd(List<AlmacenDTO> listaAlmacenesAdd) {
        this.listaAlmacenesAdd = listaAlmacenesAdd;
    }

    public List<EmpresaDTO> getListaEmpresaAdd() {
        return listaEmpresaAdd;
    }

    public void setListaEmpresaAdd(List<EmpresaDTO> listaEmpresaAdd) {
        this.listaEmpresaAdd = listaEmpresaAdd;
    }
    
     
     
     
}
