/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Movimiento;

import dto.DocumentoDTO;
import dto.MovimientoDTO;
import dto.TipomovimientoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author rikardo308
 */

@ManagedBean
@SessionScoped
public class SessionBeanMovimiento {
    private List<TipomovimientoDTO> listaTipoMovimiento = new ArrayList<TipomovimientoDTO>();
    private List<MovimientoDTO> listaMovimiento = new ArrayList<MovimientoDTO>();
    private List<DocumentoDTO> listaDocumento = new ArrayList<DocumentoDTO>();
    private ArrayList listaEstados = new ArrayList();
    
    private MovimientoDTO movimientoSeleccionado = new MovimientoDTO();
    /**
     * @return the listaTipoMovimiento
     */
    public List<TipomovimientoDTO> getListaTipoMovimiento() {
        return listaTipoMovimiento;
    }

    /**
     * @param listaTipoMovimiento the listaTipoMovimiento to set
     */
    public void setListaTipoMovimiento(List<TipomovimientoDTO> listaTipoMovimiento) {
        this.listaTipoMovimiento = listaTipoMovimiento;
    }

    /**
     * @return the listaMovimiento
     */
    public List<MovimientoDTO> getListaMovimiento() {
        return listaMovimiento;
    }

    /**
     * @param listaMovimiento the listaMovimiento to set
     */
    public void setListaMovimiento(List<MovimientoDTO> listaMovimiento) {
        this.listaMovimiento = listaMovimiento;
    }

    /**
     * @return the listaEstados
     */
    public ArrayList getListaEstados() {
        return listaEstados;
    }

    /**
     * @param listaEstados the listaEstados to set
     */
    public void setListaEstados(ArrayList listaEstados) {
        this.listaEstados = listaEstados;
    }

    /**
     * @return the listaDocumento
     */
    public List<DocumentoDTO> getListaDocumento() {
        return listaDocumento;
    }

    /**
     * @param listaDocumento the listaDocumento to set
     */
    public void setListaDocumento(List<DocumentoDTO> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    /**
     * @return the movimientoSeleccionado
     */
    public MovimientoDTO getMovimientoSeleccionado() {
        return movimientoSeleccionado;
    }

    /**
     * @param movimientoSeleccionado the movimientoSeleccionado to set
     */
    public void setMovimientoSeleccionado(MovimientoDTO movimientoSeleccionado) {
        this.movimientoSeleccionado = movimientoSeleccionado;
    }
    
}
