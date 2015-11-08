/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.ItemFacade;
import dto.ItemDTO;
import entidades.Movimientoitemvista;
import entidades.Item;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author rikardo308
 */
@Stateless
@LocalBean
public class ItemBO {
    @EJB
    ItemFacade itemFacade;
    
    public List<Movimientoitemvista> getAlliTems(){
        List<Movimientoitemvista> items = itemFacade.getAllItems();
        
        return items;
    }
    
    public boolean validarCodDuplicado(String cod){
        boolean tof = itemFacade.validateCod(cod);
        return tof;
    }
    
    public List<ItemDTO> convertListEntityToDTO(List<Item> listaItem){
        List<ItemDTO> listaDTO = new ArrayList<ItemDTO>();
        for(Item movimiento : listaItem){
            ItemDTO DTO = new ItemDTO();
            
            DTO = convertEntityToDTO(movimiento);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }

    public ItemDTO convertEntityToDTO(Item item){
        ItemDTO DTO = new ItemDTO();
            
        DTO.setIditem(item.getIditem());
        DTO.setEstado(item.getEstado());
        DTO.setOperatividad(item.getOperatividad());

        return DTO;
    }
}
