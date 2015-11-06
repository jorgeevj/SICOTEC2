/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.EmpresaFacade;
import dao.TipoFacade;
import dto.EmppersonaDTO;
import dto.EmpresaDTO;
import dto.PersonaDTO;
import dto.TelefonoDTO;
import dto.TipoDTO;
import dto.UbicacionDTO;
import entidades.Emppersona;
import entidades.Empresa;
import entidades.Telefono;
import entidades.Tipo;
import entidades.Ubicacion;
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
    private TipoFacade tipoFacade;

    @EJB
    private PersonaBO personaBO;

    @EJB
    private EmpresaFacade empresaFacade;

    public List<EmpresaDTO> getAllEmpresaBusqeuda(EmpresaDTO empresaDTO) {
        empresaDTO.setTipo(1);
        List<Empresa> empresasList = empresaFacade.getEmpresaBusqueda(empresaDTO);
        List<EmpresaDTO> dtoList = convertEntityToDTOList(empresasList);
        return dtoList;
    }

    public List<EmpresaDTO> getEmpresasProveedoras() {
        List<Empresa> empresasList = empresaFacade.getEmpresasProveedoras();
        List<EmpresaDTO> dtoList = convertEntityToDTOList(empresasList);
        return dtoList;
    }

    public List<EmpresaDTO> getEmpresasClientes() {
        List<Empresa> empresasList = empresaFacade.getEmpresasClientes();
        List<EmpresaDTO> dtoList = convertEntityToDTOList(empresasList);
        return dtoList;
    }

    public List<EmpresaDTO> convertEntityToDTOList(List<Empresa> empresaList) {
        List<EmpresaDTO> empresaDTOList = new ArrayList<EmpresaDTO>();
        EmpresaDTO empresaDTO;
        for (Empresa empresa : empresaList) {
            
            empresaDTO = convertEntityToDTO(empresa);

            empresaDTOList.add(empresaDTO);
        }
        return empresaDTOList;
    }

    public EmpresaDTO convertEntityToDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setIdempresa(empresa.getIdempresa());
        empresaDTO.setNombre(empresa.getNombre());
        empresaDTO.setRuc(empresa.getRuc());
        empresaDTO.setEmail(empresa.getEmail());
        empresaDTO.setUbicacionList(comverListUbicacionByListDTO(empresa.getUbicacionList()));
        empresaDTO.setEmppersonaListDTO(convertListEmpresapersonaByListDTO(empresa.getEmppersonaList()));
        empresaDTO.setTelefonoList(convertListTelefonoByDTO(empresa.getTelefonoList()));
        empresaDTO.setTipoListDTO(convertListTipoByListDTO(empresa.getTipoList()));
        empresaDTO.setCantidadDirecciones(empresa.getUbicacionList().size());
        empresaDTO.setCantidadTelefonos(empresa.getTelefonoList().size());
        return empresaDTO;
    }

    public List<EmpresaDTO> getAllEmpresas() {
        List<Empresa> empresaList = empresaFacade.findAll();
        List<EmpresaDTO> empresaDtoList = new ArrayList<EmpresaDTO>();
        empresaDtoList = this.convertEntityToDTOList(empresaList);
        return empresaDtoList;
    }

    private List<UbicacionDTO> comverListUbicacionByListDTO(List<Ubicacion> ubicacionList) {
        List<UbicacionDTO> listDTO = new ArrayList<>();
        for (Ubicacion u : ubicacionList) {
            listDTO.add(convertUbicacionByDTO(u));
        }
        return listDTO;
    }

    private UbicacionDTO convertUbicacionByDTO(Ubicacion u) {
        UbicacionDTO dto = new UbicacionDTO();
        dto.setCodDept(u.getCodDept());
        dto.setCodDist(u.getCodDist());
        dto.setCodProv(u.getCodProv());
        dto.setIdempresa(u.getIdempresa().getIdempresa());
        dto.setEmpresa(new EmpresaDTO(u.getIdempresa().getIdempresa()));
        dto.setIdubicacion(u.getIdubicacion());
        dto.setNombre(u.getNombre());
        dto.setNumero(u.getNumero());
        dto.setPrincipal(u.getPrincipal());
        return dto;
    }

    private List<EmppersonaDTO> convertListEmpresapersonaByListDTO(List<Emppersona> emppersonaList) {
        List<EmppersonaDTO> listDTO = new ArrayList<>();
        for (Emppersona ep : emppersonaList) {
            listDTO.add(convetEmppersonaByDTO(ep));
        }
        return listDTO;

    }

    private EmppersonaDTO convetEmppersonaByDTO(Emppersona ep) {
        EmppersonaDTO dto = new EmppersonaDTO();
        dto.setIdempresa(ep.getEmpresa().getIdempresa());
        dto.setIdpersona(ep.getPersona().getIdpersona());
        dto.setEmpresa(new EmpresaDTO(ep.getEmpresa().getIdempresa(), ep.getEmpresa().getNombre(), ep.getEmpresa().getRuc(), ep.getEmpresa().getEmail()));
        dto.setPersona(new PersonaDTO(ep.getPersona().getIdpersona(),ep.getPersona().getNombre(),ep.getPersona().getApellido(),ep.getPersona().getDni(),ep.getPersona().getEmail()));
        return dto;

    }

    private List<TelefonoDTO> convertListTelefonoByDTO(List<Telefono> telefonoList) {
        List<TelefonoDTO> listDTO = new ArrayList<>();
        for (Telefono t : telefonoList) {
            listDTO.add(convertTelefonoByDTO(t));
        }
        return listDTO;
    }

    private TelefonoDTO convertTelefonoByDTO(Telefono t) {
        TelefonoDTO dto = new TelefonoDTO();
        dto.setIdempresa(t.getIdempresa().getIdempresa());
        dto.setIdtelefono(t.getIdtelefono());
        dto.setNumero(t.getNumero());
        dto.setOperador(t.getOperador());
        dto.setPrincipal(t.getPrincipal());
        dto.setTipo(t.getTipo());
        return dto;
    }

    public List<EmpresaDTO> findByConsulta(EmpresaDTO dto) {

        return convertEntityToDTOList(empresaFacade.getEmpresaBusqueda(dto));
    }

    private List<TipoDTO> convertListTipoByListDTO(List<Tipo> tipoList) {
        List<TipoDTO> listDTO = new ArrayList<>();
        for (Tipo t : tipoList) {
            listDTO.add(convertTipoByDTO(t));
        }
        return listDTO;
    }

    private TipoDTO convertTipoByDTO(Tipo t) {
        TipoDTO dto=new TipoDTO();
        dto.setIdtipo(t.getIdtipo());
        dto.setNombre(t.getNombre());
    return dto;
    }

    public List<TipoDTO> getALLTipos() {
      return convertListTipoByListDTO(tipoFacade.findAll());
    }

    public List<PersonaDTO> getAllPersonas() {
    return personaBO.getAllPresonasDTO();
    }

}
