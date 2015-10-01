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
    private String serie;
    private String correlativo;
    private Integer idalmacen;
    private String nombreAlmacen;
    private Empresa idEmpresa;
    private Integer empresaId;
    private String nombreEmpresa;
    private List<Pealtipoitem> pealtipoitemList;
    
    public PedidoDTO() {
        
    }
    public PedidoDTO(Integer idpedido, Date fecha,Integer idalmacen,String correlativo, String nombreAlmacen ,String serie, Empresa empresa, List<Pealtipoitem> pealtipoitemList, Empresa idEmpresa,Integer empresaId ,  String nombreEmpresa){
        this.idpedido = idpedido;
        this.fecha = fecha;
        this.serie = serie;
        this.correlativo = correlativo;
        this.idalmacen = idalmacen;
        this.nombreAlmacen = nombreAlmacen;
        this.idEmpresa = idEmpresa;
        this.empresaId = empresaId;
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

    public List<Pealtipoitem> getPealtipoitemList() {
        return pealtipoitemList;
    }

    public void setPealtipoitemList(List<Pealtipoitem> pealtipoitemList) {
        this.pealtipoitemList = pealtipoitemList;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }


    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public Integer getIdalmacen() {
        return idalmacen;
    }

    public void setIdalmacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }
    

}
