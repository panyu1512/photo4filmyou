package managedbean.media;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import auth.UserLoggableInterface;
import ejb.media.MediaFacadeRemote;
import jpa.QuestionJPA;

@Named("getQuestionBean")
@SessionScoped
public class GetQuestionMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	@EJB
	private MediaFacadeRemote<UserLoggableInterface> getQuestionFacade;
	protected QuestionJPA question = new QuestionJPA();
	private int questionId;

	public QuestionJPA getQuestion(int questionId) throws Exception
	{
		showQuestion(questionId);
		return question;
	}
	
	public void onLoad()
	{
		showQuestion(questionId);
	}
	
	public void showQuestion(int questionId)  {
		this.question =  (QuestionJPA) getQuestionFacade.getQuestion(questionId);
	}

	public QuestionJPA getQuestion() {
		return question;
	}

	public void setQuestion(QuestionJPA question) {
		this.question = question;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
}
