/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Cotipoitem;
import entidades.Empresa;
import entidades.Pealtipoitem;
import entidades.Pedido;
import entidades.Rol;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Cesar
 */
public class PedidoDTO {
    private Integer idpedido;
    private Date fecha;
    private Integer cantidad;
    private Empresa empresa;
    private Integer idEmpresa;
    private String nombreEmpresa;
    private List<Pealtipoitem> pealtipoitemList;
    
    public PedidoDTO() {
        
    }
    public PedidoDTO(Integer idpedido, Date fecha, Integer cantidad, Empresa empresa, List<Pealtipoitem> pealtipoitemList, Integer idEmpresa, String nombreEmpresa){
        this.idpedido = idpedido;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.empresa = empresa;
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.pealtipoitemList = pealtipoitemList;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<Pealtipoitem> getPealtipoitemList() {
        return pealtipoitemList;
    }

    public void setPealtipoitemList(List<Pealtipoitem> pealtipoitemList) {
        this.pealtipoitemList = pealtipoitemList;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    

}
