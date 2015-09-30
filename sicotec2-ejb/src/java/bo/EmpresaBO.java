/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.EmpresaFacade;
import dto.EmpresaDTO;
import entidades.Empresa;
import javax.ejb.EJB;
import java.util.*;
import javax.ejb.*;
import javax.ejb.Stateless;

/**
 *
 * @author Cesar
 */
@Stateless
@LocalBean
public class EmpresaBO {
    @EJB
    private EmpresaFacade empresaFacade = new EmpresaFacade();
    
    public List<EmpresaDTO> getAllEmpresaBusqeuda(EmpresaDTO empresaDTO){
        List<Empresa> empresasList  = empresaFacade.getEmpresaBusqueda(empresaDTO);
        List<EmpresaDTO> dtoList = convertEntityToDTOList(empresasList);
        return dtoList;
    }
    
    public List<EmpresaDTO> convertEntityToDTOList(List<Empresa> empresaList){
        List<EmpresaDTO> empresaDTOList = new ArrayList<EmpresaDTO>();
        for(Empresa empresa : empresaList){
            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO = convertEntityToDTO(empresa);
            
            empresaDTOList.add(empresaDTO);
        }
        return empresaDTOList;
    }
    
    public EmpresaDTO convertEntityToDTO(Empresa empresa){
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setIdempresa(empresa.getIdempresa());
        empresaDTO.setNombre(empresa.getNombre());
        empresaDTO.setRuc(empresa.getRuc());
        empresaDTO.setTipo(empresa.getTipo());
        return empresaDTO;
    }
    
    public List<EmpresaDTO> getAllEmpresas(){
        List<Empresa> empresaList = empresaFacade.findAll();
        List<EmpresaDTO> empresaDtoList = new ArrayList<EmpresaDTO>();
        empresaDtoList = this.convertEntityToDTOList(empresaList);
        return empresaDtoList;
    }
    
}
