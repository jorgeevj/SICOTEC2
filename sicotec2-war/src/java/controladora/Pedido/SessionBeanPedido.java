/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Pedido;

import dto.PedidoDTO;
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
    private String nombreEmpresaBuscar;
    private Date fechaInicioBuscar;
    private Date fechaTerminoBuscar;
    
    
    

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

    public String getNombreEmpresaBuscar() {
        return nombreEmpresaBuscar;
    }

    public void setNombreEmpresaBuscar(String nombreEmpresaBuscar) {
        this.nombreEmpresaBuscar = nombreEmpresaBuscar;
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
    
    
    
    
}
