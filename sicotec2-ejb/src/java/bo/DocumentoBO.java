/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DocumentoFacade;
import dto.DocumentoDTO;
import entidades.Documento;
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
public class DocumentoBO {
    
    @EJB
    DocumentoFacade documentoFacade;
    
    public List<DocumentoDTO> getAllDocumentos(){
        List<DocumentoDTO> lista = new ArrayList<DocumentoDTO>();
        List<Documento> listaEntidad = documentoFacade.findAll();
        
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
    public List<DocumentoDTO> convertListEntityToDTO(List<Documento> listaDocumento){
        List<DocumentoDTO> listaDTO = new ArrayList<DocumentoDTO>();
        for(Documento documento : listaDocumento){
            DocumentoDTO DTO = new DocumentoDTO();
            
            DTO = convertEntityToDTO(documento);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
    
    public DocumentoDTO convertEntityToDTO(Documento documento){
        DocumentoDTO DTO = new DocumentoDTO();
            
        DTO.setIddocumento(documento.getIddocumento());
        DTO.setNombre(documento.getNombre());

        return DTO;
    }
}
