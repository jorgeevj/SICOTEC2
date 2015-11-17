/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jc7
 */
public class RequerimientoDTO {
    
    private Integer idrequerimientos;
    private Integer cantidad;
    private List<LoteDTO> loteList;
    private List<PealtipoitemDTO> pealtipoitemList;

    public RequerimientoDTO(Integer idrequerimientos) {
        this.idrequerimientos = idrequerimientos;
        loteList=new ArrayList<>();
        pealtipoitemList=new ArrayList<>();
    }

    public RequerimientoDTO(Integer idrequerimientos, Integer cantidad) {
        this.idrequerimientos = idrequerimientos;
        this.cantidad = cantidad;
        loteList=new ArrayList<>();
        pealtipoitemList=new ArrayList<>();
    }

    public RequerimientoDTO() {
        loteList=new ArrayList<>();
        pealtipoitemList=new ArrayList<>();
    }

    public Integer getIdrequerimientos() {
        return idrequerimientos;
    }

    public void setIdrequerimientos(Integer idrequerimientos) {
        this.idrequerimientos = idrequerimientos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<LoteDTO> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<LoteDTO> loteList) {
        this.loteList = loteList;
    }

    public List<PealtipoitemDTO> getPealtipoitemList() {
        return pealtipoitemList;
    }

    public void setPealtipoitemList(List<PealtipoitemDTO> pealtipoitemList) {
        this.pealtipoitemList = pealtipoitemList;
    }
    
    
}
