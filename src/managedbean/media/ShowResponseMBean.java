package managedbean.media;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import auth.UserLoggableInterface;
import ejb.media.MediaFacadeRemote;
import jpa.ResponseJPA;

@Named("getResponseBean")
@ViewScoped
public class ShowResponseMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MediaFacadeRemote<UserLoggableInterface> showResponseFacade;
	private ResponseJPA response;
	private int questionId;

	public ResponseJPA getResponse(int questionId) throws Exception {
		showResponse(questionId);
		return response;
	}

	public void onLoad() {
		showResponse(questionId);
	}

	public void showResponse(int questionId) {
		this.response = (ResponseJPA) showResponseFacade.getResponse(questionId);
	}

	public ResponseJPA getResponse() {
		return response;
	}

	public void setResponse(ResponseJPA response) {
		this.response = response;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getAttachedImage() {
		return this.response.getAttachedImageAsBase64();
	}

}
