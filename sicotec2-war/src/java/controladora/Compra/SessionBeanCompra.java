/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Compra;

import dto.CompraDTO;
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
    
     
     
     
}
