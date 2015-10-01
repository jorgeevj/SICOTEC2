/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.CompraFacade;
import dto.CompraDTO;
import entidades.Compra;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author jc7
 */
@Stateless
@LocalBean
public class CompraBO {
  
    @EJB
    private CompraFacade compraFacade = new CompraFacade();
    
    
    public List<CompraDTO> getAllCompras() {
        List<Compra> listaCompras       = compraFacade.findAll();
        List<CompraDTO> listaComprasDTO = convertListEntityToDTO(listaCompras);
        
        return listaComprasDTO;
    }
    
    public List<CompraDTO> convertListEntityToDTO(List<Compra> listaCompras){
        List<CompraDTO> listaDTO = new ArrayList<CompraDTO>();
        for(Compra usuario : listaCompras){
            CompraDTO DTO = new CompraDTO();
            
            DTO = convertEntityToDTO(usuario);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
    
    public CompraDTO convertEntityToDTO(Compra compra){
        CompraDTO DTO = new CompraDTO();
        
            DTO.setIdcompra(compra.getIdcompra());
            DTO.setCorrelativo(compra.getCorrelativo());
            DTO.setFecha(compra.getFecha());
            DTO.setIdalmacen(compra.getIdalmacen());
            DTO.setIdempresa(compra.getIdempresa());
            DTO.setTotal(compra.getTotal());
            DTO.setSerie(compra.getSerie());
        
        return DTO;
    }
}
