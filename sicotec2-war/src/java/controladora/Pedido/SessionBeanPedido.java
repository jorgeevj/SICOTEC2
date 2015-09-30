/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Pedido;

import dto.EmpresaDTO;
import dto.PedidoDTO;
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
    //BUSCAR
    private String rucBuscar;
    private String idEmpresa;
    private Date fechaInicioBuscar;
    private Date fechaTerminoBuscar;
    private List<Almacen> listaAlmacenes;
    private List<EmpresaDTO> ListaEmpresa = new ArrayList();

    public List<PedidoDTO> getListPedido() {
        return listPedido;
    }

    public void setListPedido(List<PedidoDTO> listPedido) {
        this.listPedido = listPedido; 
    }

    public String getRucBuscar() {
        return rucBuscar;
    }

    public void setRucBuscar(String rucBuscar) {
        this.rucBuscar = rucBuscar;
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

    public List<EmpresaDTO> getListaEmpresa() {
        return ListaEmpresa;
    }

    public void setListaEmpresa(List<EmpresaDTO> ListaEmpresa) {
        this.ListaEmpresa = ListaEmpresa;
    }



    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<Almacen> getListaAlmacenes() {
        return listaAlmacenes;
    }

    public void setListaAlmacenes(List<Almacen> listaAlmacenes) {
        this.listaAlmacenes = listaAlmacenes;
    }
    
}
