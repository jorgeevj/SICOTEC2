/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Pedido;

import dto.AlmacenDTO;
import dto.EmpresaDTO;
import dto.PedidoDTO;
import dto.TipoItemDTO;
import entidades.Almacen;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author Cesar
 */

@ManagedBean
@SessionScoped
public class SessionBeanPedido {
    //ARRAYS
    private List<PedidoDTO> listPedido = new ArrayList<PedidoDTO>();
    private List<EmpresaDTO> ListaEmpresaAdd = new ArrayList<EmpresaDTO>();
    private List<TipoItemDTO> listaItems = new ArrayList<TipoItemDTO>();
    private List<AlmacenDTO> listaAlmacenesAdd = new ArrayList<AlmacenDTO>();
    //BUSCAR
    private Date fechaInicioBuscar;
    private Date fechaTerminoBuscar;
    private String nombreEmpresa;
    
    

    public List<PedidoDTO> getListPedido() {
        return listPedido;
    }

    public void setListPedido(List<PedidoDTO> listPedido) {
        this.listPedido = listPedido; 
    }

    public Date getFechaInicioBuscar() {
        return fechaInicioBuscar;
    }

    public void setFechaInicioBuscar(Date fechaInicioBuscar) {
        this.fechaInicioBuscar = fechaInicioBuscar;
    }

    public Date getFechaTerminoBuscar() {
        return fechaTerminoBuscar;
    }

    public void setFechaTerminoBuscar(Date fechaTerminoBuscar) {
        this.fechaTerminoBuscar = fechaTerminoBuscar;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public List<EmpresaDTO> getListaEmpresaAdd() {
        return ListaEmpresaAdd;
    }

    public void setListaEmpresaAdd(List<EmpresaDTO> ListaEmpresaAdd) {
        this.ListaEmpresaAdd = ListaEmpresaAdd;
    }

    public List<TipoItemDTO> getListaItems() {
        return listaItems;
    }

    public void setListaItems(List<TipoItemDTO> listaItems) {
        this.listaItems = listaItems;
    }

    public List<AlmacenDTO> getListaAlmacenesAdd() {
        return listaAlmacenesAdd;
    }

    public void setListaAlmacenesAdd(List<AlmacenDTO> listaAlmacenesAdd) {
        this.listaAlmacenesAdd = listaAlmacenesAdd;
    }
    
}
