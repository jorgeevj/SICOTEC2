package Util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ricardo
 */
public class AutoListe implements PhaseListener{


    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext facesContext = pe.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();
        boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1) ? true : false;
        
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object usuario = sesion.getAttribute("usuario");
        
        
        if(!isLoginPage && usuario == null){
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("PrevPaage", url);
            
            nh.handleNavigation(facesContext, null, "login.xhtml");
            
        }else if(usuario!=null){
          if(isLoginPage){
              NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
              
              //Object prevPage = sesion.getAttribute("PrevPaage");
              nh.handleNavigation(facesContext, null, "index.xhtml");          
        }
    }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {        
     }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
