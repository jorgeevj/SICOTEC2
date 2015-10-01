/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Compra;

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
     private List<EmpresaDTO>listaEmpresa=new ArrayList<EmpresaDTO>();
    /**
     * @return the listaTCompra
     */
    public List<CompraDTO> getListaTCompra() {
        return listaCompra;
    }

    /**
     * @param listaTCompra the listaTCompra to set
     */
    public void setListaTCompra(List<CompraDTO> listaTCompra) {
        this.listaCompra = listaTCompra;
    }

    public List<CompraDTO> getListaCompra() {
        return listaCompra;
    }

    public void setListaCompra(List<CompraDTO> listaCompra) {
        this.listaCompra = listaCompra;
    }

    public List<EmpresaDTO> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<EmpresaDTO> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }
    
     
     
     
}
