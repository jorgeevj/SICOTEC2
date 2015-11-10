/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entidades.Compra;
import entidades.Cotizacion;
import entidades.Emppersona;
import entidades.Pedido;
import entidades.Tipo;
import entidades.Ubicacion;
import entidades.Venta;
import java.util.List;

/**
 *
 * @author Cesar
 */
public class EmpresaDTO {

    private Integer idempresa;
    private String nombre;
    private String ruc;
    private String email;
    private Integer tipo;
    private List<Tipo> tipoList;
    private List<Compra> compraList;
    private List<Emppersona> emppersonaList;
    private List<Pedido> pedidoList;
    private List<Venta> ventaList;
    private List<Cotizacion> cotizacionList;
    private int idemp;
    private List<UbicacionDTO> ubicacionList;
    private List<TelefonoDTO> telefonoList;
    //extras
    private List<EmppersonaDTO> emppersonaListDTO;
    private String[] tipoArray;
    private List<TipoDTO> tipoListDTO;
    private UbicacionDTO ubicacionDTO;
    private TelefonoDTO telefonoDTO;
    private int cantidadDirecciones;
    private int cantidadTelefonos;

    public EmpresaDTO(int id) {
        idemp = id;
    }

    public EmpresaDTO() {
        
    }

    public EmpresaDTO(Integer idempresa, String nombre, String ruc, String email) {
        this.idempresa = idempresa;
        this.nombre = nombre;
        this.ruc = ruc;
        this.email = email;
    }

    public EmpresaDTO(String nombre, String ruc, int idemp) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.idemp = idemp;
    }

    public EmpresaDTO(Integer idempresa, String nombre, String ruc, Integer tipo, List<Tipo> tipoList, List<Compra> compraList, List<Emppersona> emppersonaList, List<Pedido> pedidoList, List<Venta> ventaList, List<Cotizacion> cotizacionList) {
        this.idempresa = idempresa;
        this.nombre = nombre;
        this.ruc = ruc;
        this.tipo = tipo;
        this.tipoList = tipoList;
        this.compraList = compraList;
        this.emppersonaList = emppersonaList;
        this.pedidoList = pedidoList;
        this.ventaList = ventaList;
        this.cotizacionList = cotizacionList;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public List<Tipo> getTipoList() {
        return tipoList;
    }

    public void setTipoList(List<Tipo> tipoList) {
        this.tipoList = tipoList;
    }

    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    public List<Emppersona> getEmppersonaList() {
        return emppersonaList;
    }

    public void setEmppersonaList(List<Emppersona> emppersonaList) {
        this.emppersonaList = emppersonaList;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    public int getIdemp() {
        return idemp;
    }

    public void setIdemp(int idemp) {
        this.idemp = idemp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UbicacionDTO> getUbicacionList() {
        return ubicacionList;
    }

    public void setUbicacionList(List<UbicacionDTO> ubicacionList) {
        this.ubicacionList = ubicacionList;
    }

    public List<EmppersonaDTO> getEmppersonaListDTO() {
        return emppersonaListDTO;
    }

    public void setEmppersonaListDTO(List<EmppersonaDTO> emppersonaListDTO) {
        this.emppersonaListDTO = emppersonaListDTO;
    }

    public List<TelefonoDTO> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<TelefonoDTO> telefonoList) {
        this.telefonoList = telefonoList;
    }

   

    public UbicacionDTO getUbicacionDTO() {
        return ubicacionDTO;
    }

    public void setUbicacionDTO(UbicacionDTO ubicacionDTO) {
        this.ubicacionDTO = ubicacionDTO;
    }

    public TelefonoDTO getTelefonoDTO() {
        return telefonoDTO;
    }

    public void setTelefonoDTO(TelefonoDTO telefonoDTO) {
        this.telefonoDTO = telefonoDTO;
    }

    public int getCantidadDirecciones() {
        return cantidadDirecciones;
    }

    public void setCantidadDirecciones(int cantidadDirecciones) {
        this.cantidadDirecciones = cantidadDirecciones;
    }

    public int getCantidadTelefonos() {
        return cantidadTelefonos;
    }

    public void setCantidadTelefonos(int cantidadTelefonos) {
        this.cantidadTelefonos = cantidadTelefonos;
    }

    public String[] getTipoArray() {
        return tipoArray;
    }

    public void setTipoArray(String[] tipoArray) {
        this.tipoArray = tipoArray;
    }

    public List<TipoDTO> getTipoListDTO() {
        return tipoListDTO;
    }

    public void setTipoListDTO(List<TipoDTO> tipoListDTO) {
        this.tipoListDTO = tipoListDTO;
    }

}
