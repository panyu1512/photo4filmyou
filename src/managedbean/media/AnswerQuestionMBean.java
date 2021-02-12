package managedbean.media;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import auth.UserLoggableInterface;
import ejb.media.MediaFacadeRemote;
import jpa.QuestionJPA;

@Named("answerQuestionBean")
@SessionScoped
public class AnswerQuestionMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private MediaFacadeRemote<UserLoggableInterface> answerQuestionFacade;
	private String message;
	
	public String answerQuestion() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
		        .getRequest();

		String questionId = request.getParameter("questionId");
		
		answerQuestionFacade.answerQuestion(new Integer(questionId), message);
		return "/pages/listAllQuestions?faces-redirect=true"; 
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
