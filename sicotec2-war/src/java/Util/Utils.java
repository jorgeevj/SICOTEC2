/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import dto.PermisoDTO;
import dto.UsuarioDTO;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class Utils {
    
    public void logOut(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        try {
              FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
          } catch (IOException ex) {
          }
    }
    
    public void mostrarNotificacion(RequestContext req, String mensaje, int tiempo){
        req.execute("Materialize.toast('"+mensaje+"', "+tiempo+")");
    }
}
