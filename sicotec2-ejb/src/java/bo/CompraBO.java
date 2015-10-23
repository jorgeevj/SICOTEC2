/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.CompraFacade;
import dao.TipoitemFacade;
import dto.CompraDTO;
import entidades.Compra;
import java.util.ArrayList;
import java.util.Date;
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
    private CompraFacade compraFacade ;
     @EJB
    private AlmacenFacade almacenFacade = new AlmacenFacade();
     
    

    
    
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
            DTO.setEstado(compra.getEstado());
          
//            String nombreAlmacen = almacenFacade.getAlmacenById(DTO.getIdalmacen()).getNombre() ;
//            DTO.setNombreAlmacen(nombreAlmacen);
        
        return DTO;
    }
    public Compra convertDTOtoEntity(CompraDTO dto, int tipo){
        Compra entidad = new Compra();
        if(tipo == 0){
            entidad.setIdcompra(dto.getIdcompra());
        }
        entidad.setFecha(new Date());
        entidad.setIdempresa(dto.getIdempresa());
        entidad.setIdalmacen(dto.getIdalmacen());
        entidad.setSerie(dto.getSerie());
        entidad.setCorrelativo(dto.getCorrelativo());
        entidad.setEstado(dto.getEstado());
        entidad.setTotal(dto.getTotal());
       
        
        return entidad;
    }
    public List<CompraDTO> BuscarCompra(CompraDTO dto) {
       List<CompraDTO> listaDto = this.convertListEntityToDTO(compraFacade.buscarCompra(dto));
        return listaDto;
    }
    
    public Compra insertarNuevoCompra(CompraDTO dto ){
        Compra entidad = convertDTOtoEntity(dto , 1);
        entidad = compraFacade.agregarCompra(entidad);
        return entidad;
    }
}
