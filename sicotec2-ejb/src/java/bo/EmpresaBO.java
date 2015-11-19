/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.EmppersonaFacade;
import dao.EmpresaFacade;
import dao.PersonaFacade;
import dao.TelefonoFacade;
import dao.TipoFacade;
import dao.UbicacionFacade;
import dto.EmppersonaDTO;
import dto.EmpresaDTO;
import dto.PersonaDTO;
import dto.TelefonoDTO;
import dto.TipoDTO;
import dto.UbicacionDTO;
import entidades.Emppersona;
import entidades.EmppersonaPK;
import entidades.Empresa;
import entidades.Persona;
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
    private PersonaFacade personaFacade;
    @EJB
    private TelefonoFacade telefonoFacade;
    @EJB
    private UbicacionFacade ubicacionFacade;
    @EJB
    private EmppersonaFacade emppersonaFacade;

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
        empresaDTO.setUbicacionList(comverListUbicacionByListDTO(ubicacionFacade.findByIdEmpresa(empresa.getIdempresa())));
        empresaDTO.setEmppersonaListDTO(convertListEmpresapersonaByListDTO(emppersonaFacade.getPersonaByEmpresa(empresaDTO)));
        empresaDTO.setTelefonoList(convertListTelefonoByDTO(telefonoFacade.findByIdEmpresa(empresa.getIdempresa())));
        empresaDTO.setTipoListDTO(convertListTipoByListDTO(empresa.getTipoList()));
        empresaDTO.setCantidadDirecciones(empresaDTO.getUbicacionList().size());
        empresaDTO.setCantidadTelefonos(empresaDTO.getTelefonoList().size());
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
        dto.setIdempresa(ep.getEmppersonaPK().getIdempresa());
        dto.setIdpersona(ep.getEmppersonaPK().getIdpersona());
//        dto.setEmpresa(new EmpresaDTO(ep.getEmpresa().getIdempresa(), ep.getEmpresa().getNombre(), ep.getEmpresa().getRuc(), ep.getEmpresa().getEmail()));
        dto.setPersona(personaBO.converEntityPersonaByDTO(personaFacade.find(ep.getEmppersonaPK().getIdpersona())));
        
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
        TipoDTO dto = new TipoDTO();
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

    public Empresa guardarEmpresa(EmpresaDTO e) {
        Empresa entidad=convertDTOByEntity(e);
        entidad = empresaFacade.guardaEmpresa(entidad);
        e.setIdempresa(entidad.getIdempresa());
        guardarDatosEmpresa(e);
        return entidad;
    }
    
    private void guardarDatosEmpresa(EmpresaDTO e){
    List<Emppersona> lep=convetListEmpPerDTOByListEntidad(e.getEmppersonaListDTO(),e.getIdempresa());
        for(Emppersona ep:lep){
        emppersonaFacade.create(ep);
        }
        List<Ubicacion> lub=convertListUbicByListDTO(e.getUbicacionList(),e.getIdempresa());
        for(Ubicacion ub:lub){
        ubicacionFacade.create(ub);
        }
        List<Telefono> tel=convertListTelfByListDTO(e.getTelefonoList(),e.getIdempresa());
        for(Telefono t:tel){
        telefonoFacade.create(t);
        }
    }

    public Empresa convertDTOByEntity(EmpresaDTO e) {
        Empresa entidad = new Empresa();
        entidad.setNombre(e.getNombre());
        entidad.setEmail(e.getEmail());
        entidad.setRuc(e.getRuc());
        List<Tipo> lt = new ArrayList<>();
        Tipo t = new Tipo();
        if(e.getTipoArray() != null){
            for (String et : e.getTipoArray()) {
                t = new Tipo();
                t.setIdtipo(Integer.parseInt(et));
                lt.add(t);
            }
        }
        entidad.setTipoList(lt);
        return entidad;
    }

    private List<Emppersona> convetListEmpPerDTOByListEntidad(List<EmppersonaDTO> listDTO,int idempresa) {
     List<Emppersona> ep=new ArrayList<>();
     for(EmppersonaDTO dto:listDTO){
         dto.setIdempresa(idempresa);
         ep.add(convertEmpPerDTOByEntidad(dto));
     }
    return ep;
    }

    private Emppersona convertEmpPerDTOByEntidad(EmppersonaDTO dto) {
     Emppersona entidad=new  Emppersona();
     EmppersonaPK pk=new EmppersonaPK(dto.getIdempresa(), dto.getIdpersona());
     entidad.setEmppersonaPK(pk);
     return entidad;
    }

    private List<Ubicacion> convertListUbicByListDTO(List<UbicacionDTO> listDTO,int idempresa) {
        List<Ubicacion> lub=new ArrayList<>();
        for(UbicacionDTO dto:listDTO){
            dto.setIdempresa(idempresa);
        lub.add(convertUbiDTOByEntidad(dto));
        }
    return lub;
    }

    private Ubicacion convertUbiDTOByEntidad(UbicacionDTO dto) {
        Ubicacion u=new Ubicacion();
        u.setIdempresa(new Empresa(dto.getIdempresa()));
        u.setNombre(dto.getNombre());
        u.setNumero(dto.getNumero());
        u.setCodDept(dto.getCodDept());
        u.setCodProv(dto.getCodProv());
        u.setCodDist(dto.getCodDist());
        u.setPrincipal(dto.getPrincipal());
       return u;
    }

    private List<Telefono> convertListTelfByListDTO(List<TelefonoDTO> telefonoList, Integer idempresa) {
      List<Telefono> lt=new ArrayList<>();
      for(TelefonoDTO dto:telefonoList){
          dto.setIdempresa(idempresa);
      lt.add(convertTelDTOByentidad(dto));
      }
    return lt;
    }

    private Telefono convertTelDTOByentidad(TelefonoDTO dto) {
      Telefono t=new Telefono();
      t.setIdempresa(new Empresa(dto.getIdempresa()));
      t.setIdtelefono(dto.getIdtelefono());
      t.setNumero(dto.getNumero());
      t.setOperador(dto.getOperador());
      t.setPrincipal(dto.getPrincipal());
      t.setTipo(dto.getTipo());
        return t;
    }
     public void guardarEditar(EmpresaDTO c) {
        Empresa entidad=convertDTOByEntity(c);
        entidad.setIdempresa(c.getIdempresa());
         empresaFacade.edit(entidad);
         //eliminamos las listas
         for(Emppersona ep:emppersonaFacade.getPersonaByEmpresa(c)){
             emppersonaFacade.remove(ep);
         }
         for(Ubicacion u:ubicacionFacade.findByIdEmpresa(c.getIdempresa())){
             ubicacionFacade.remove(u);
         }
         for(Telefono t:telefonoFacade.findByIdEmpresa(c.getIdempresa())){
             telefonoFacade.remove(t);
         }
         //guardamos las nuevas listas
         guardarDatosEmpresa(c);
        
    }
      public List<EmpresaDTO> empresaRucDuplicado(EmpresaDTO t){
        Empresa r=convertDTOByEntity(t);
        List<Empresa> lista=empresaFacade.getEmpresaDuplicadoRuc(r);
        List<EmpresaDTO> lista1=convertEntityToDTOList(lista);
        return lista1;
    }

    public List<EmppersonaDTO> getPersonaByEmpresa(EmpresaDTO EmpresaSelectDTO) {
    return convertListEmpresapersonaByListDTO(emppersonaFacade.getPersonaByEmpresa(EmpresaSelectDTO));
    }

    public List<UbicacionDTO> getUbicacioByEpresa(EmpresaDTO EmpresaSelectDTO) {
      return comverListUbicacionByListDTO(ubicacionFacade.findByIdEmpresa(EmpresaSelectDTO.getIdempresa()));
    }

    public List<TelefonoDTO> getTelefonoByEmpresa(EmpresaDTO EmpresaSelectDTO) {
       return convertListTelefonoByDTO(telefonoFacade.findByIdEmpresa(EmpresaSelectDTO.getIdempresa())); 
    }

    public List<TipoDTO> getTipoEmpresaByEmpresa(EmpresaDTO EmpresaSelectDTO) {
       return convertListTipoByListDTO(null);
    }
}
