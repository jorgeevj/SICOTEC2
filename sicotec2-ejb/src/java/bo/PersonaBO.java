/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.PersonaFacade;
import dto.PersonaDTO;
import entidades.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Jorge
 */
@Stateless
@LocalBean
public class PersonaBO {
    @EJB
    private PersonaFacade personaFacade;

    public List<Persona> getAllPresonas() {
        
        return personaFacade.findAll();
    }
    public List<PersonaDTO> getAllPresonasDTO() {
        return convertListaPersonaByListDTO(personaFacade.findAll());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public PersonaDTO converEntityPersonaByDTO(Persona p) {
        PersonaDTO dto=new PersonaDTO();
        dto.setApellido(p.getApellido());
        dto.setDni(p.getDni());
        dto.setEmail(p.getEmail());
        dto.setIdpersona(p.getIdpersona());
        dto.setNombre(p.getNombre());
        return dto;
    }

    private List<PersonaDTO> convertListaPersonaByListDTO(List<Persona> findAll) {
        List<PersonaDTO> listDTO=new ArrayList<>();
        for(Persona p:findAll){
        listDTO.add(converEntityPersonaByDTO(p));
        }
        return listDTO;
    }
    //0 - UPDATE || 1 - INSERT
    public Persona convertDtoToEntity(PersonaDTO dto, int tipo){
        Persona entidad = new Persona();
        if(tipo == 0){
            entidad.setIdpersona(dto.getIdpersona());
        }
        entidad.setApellido(dto.getApellido());
        entidad.setDni(dto.getDni());
        entidad.setEmail(dto.getEmail());
        entidad.setNombre(dto.getNombre());
        return entidad;
    }
    public List<PersonaDTO> findPersona(PersonaDTO consultaPersona) {
        Persona entidad = this.convertDtoToEntity(consultaPersona, 0);
      return convertListaPersonaByListDTO(personaFacade.findPersona(entidad));
    }
    
    public List<PersonaDTO> buscarPersonasByFiltro(PersonaDTO personaDto){
        List<PersonaDTO> listaDTO = new ArrayList<PersonaDTO>();
        return listaDTO;
    }

    
}
