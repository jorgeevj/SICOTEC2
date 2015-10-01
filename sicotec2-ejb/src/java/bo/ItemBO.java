/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.ItemFacade;
import dto.ItemDTO;
import entidades.Item;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author rikardo308
 */
@Stateless
@LocalBean
public class ItemBO {
    ItemFacade itemFacada = new ItemFacade();
    
    public List<ItemDTO> getAlliTems(){
        List<ItemDTO> items =  new ArrayList<ItemDTO>();
        List<Item> ent = itemFacada.findAll();
        
        items = convertListEntityToDTO(ent);
        
        return items;
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
