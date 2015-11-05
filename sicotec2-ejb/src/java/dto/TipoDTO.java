/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;

/**
 *
 * @author Jorge
 */
public class TipoDTO {

    private Integer idtipo;

    private String nombre;

    private List<EmpresaDTO> empresaListDTO;

    public TipoDTO() {
    
    }

    public TipoDTO(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<EmpresaDTO> getEmpresaListDTO() {
        return empresaListDTO;
    }

    public void setEmpresaListDTO(List<EmpresaDTO> empresaListDTO) {
        this.empresaListDTO = empresaListDTO;
    }
    
    
}
