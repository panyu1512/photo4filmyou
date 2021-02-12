package managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;

import auth.UserLoggableInterface;
import auth.util.Container;
import auth.util.HttpManager;
import auth.util.SessionManager;
import ejb.profile.ProfileFacadeRemote;
import jpa.AdministratorJPA;
import jpa.CustomerJPA;
import managedbean.profile.LoginMBean;



public abstract class SessionBean implements Serializable  {
    private static final long serialVersionUID = 7360539836072273777L;

    Logger logger = Logger.getLogger(LoginMBean.class.getName());
    @EJB
    protected ProfileFacadeRemote<UserLoggableInterface> profileAdmin;
    protected UserLoggableInterface user;

    public UserLoggableInterface getUser() {
        return user;
    }
  
    public void checkUserIsNotAdministrator() throws IOException {
        if (SessionManager.isNotUserAdministrator()) {
            this.loginRedirect();
        }
    }

    public boolean userIsAdministrator(){
        if (user instanceof AdministratorJPA) {
            return true;
        }
        return false;
    }

    public boolean userIsCustomer(){
        if (user instanceof CustomerJPA) {
            return true;
        }
        return false;
    }

    protected void loginRedirect() throws IOException {
        SessionManager.logout();

        HttpManager httpManager = Container.getHttpManager();
        httpManager.redirect("/pages/catalog.xhtml");
    }
}
