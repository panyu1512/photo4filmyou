package managedbean.media;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import auth.UserLoggableInterface;
import auth.util.SessionManager;
import auth.util.Sha1;
import domain.Email;
import domain.Password;
import domain.Roles;
import ejb.media.MediaFacadeRemote;
import ejb.profile.ProfileFacadeRemote;
import managedbean.profile.LoginMBean;

@Named("askQuestionBean")
@SessionScoped
public class AskQuestionMBean implements Serializable {

	private static final long serialVersionUID = 8360539836072273777L;
	
	Logger logger = Logger.getLogger(LoginMBean.class.getName());

	@EJB
	private MediaFacadeRemote<UserLoggableInterface> media;
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
	
	public String askQuestion() {
		media.askQuestion(title, message);
		
		return "/pages/listAllQuestions?faces-redirect=true";
	}
	
}
