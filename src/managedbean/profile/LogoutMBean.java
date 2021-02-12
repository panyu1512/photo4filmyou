package managedbean.profile;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import auth.util.SessionManager;

@Named("logoutBean")
@SessionScoped
public class LogoutMBean implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -2726492057145396671L;

	public String logout()
    {
        SessionManager.logout();
        return "/pages/catalog?faces-redirect=true";
    }
}
