package managedbean.media;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import auth.UserLoggableInterface;
import ejb.media.MediaFacadeRemote;

@Named("acceptOrRejectResponseBean")
@SessionScoped
public class AcceptRejectResponseMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private MediaFacadeRemote<UserLoggableInterface> acceptRejectFacade;
	private boolean isAccepted;
	private int responseId;

	public void acceptResponse(int responseId) {
		System.out.println("aceptando" + responseId);
		acceptRejectFacade.acceptOrRejectResponse(new Integer(responseId), true);
	}

	public void rejectResponse(int responseId) {
		System.out.println("rechazando" + responseId);
		acceptRejectFacade.acceptOrRejectResponse(new Integer(responseId), false);
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
}
