package managedbean.profile;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import auth.UserLoggableInterface;
import auth.util.SessionManager;
import auth.util.Sha1;
import domain.Email;
import domain.Password;
import domain.Roles;
import managedbean.SessionBean;

@Named("loginBean")
@SessionScoped
public class LoginMBean extends SessionBean implements Serializable {

	
	private static final long serialVersionUID = 8360539836072273778L;

	private String email;
	private String password;
	
	public void setUser(UserLoggableInterface user) {
		this.user = user;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}


	public String login() {
		if (SessionManager.hasUser()) {
			
			return "/pages/catalog?faces-redirect=true";
		}

		Email email = new Email(this.email.trim());
		Password password = new Password(this.password.trim(), new Sha1());

		user = this.profileAdmin.login(email, password);
		if (user == null) {
			return null;
		}

		SessionManager.setUser(user);
		//TODO:redirect to different page...
		if (user.getRole() == Roles.Administrator) {
		
			return "/pages/catalog?faces-redirect=true";
		} else if (user.getRole() == Roles.Customer) {
			return "/pages/catalog?faces-redirect=true";
		} else {
			return null;
		}
	}

}
