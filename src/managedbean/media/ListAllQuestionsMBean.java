package managedbean.media;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import auth.UserLoggableInterface;
import auth.util.Container;
import auth.util.HttpManager;
import auth.util.SessionManager;
import ejb.media.MediaFacadeRemote;
import jpa.QuestionJPA;

@Named("listAllQuestionsBean")
@SessionScoped
public class ListAllQuestionsMBean implements Serializable {

	private static final long serialVersionUID = 8360539836072273778L;

	@EJB
	private MediaFacadeRemote<UserLoggableInterface> listAllQuestionsFacade;
	protected Collection<QuestionJPA> questionList = new ArrayList<QuestionJPA>();

	public Collection<QuestionJPA> getAllQuestions() throws Exception
	{
		listAllQuestions();
		return questionList;
	}
	
	public void onLoad() {
		listAllQuestions();
	}
	
	public void listAllQuestions()  {
		this.questionList =  (Collection<QuestionJPA>) listAllQuestionsFacade.listAllQuestions();
	}

	public Collection<QuestionJPA> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(Collection<QuestionJPA> questionList) {
		this.questionList = questionList;
	}
	
	
}
