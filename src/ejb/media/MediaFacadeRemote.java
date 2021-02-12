package ejb.media;

import java.awt.Image;
import java.util.Collection;

import javax.ejb.Remote;

import jpa.QuestionJPA;
import jpa.ResponseJPA;

@Remote
public interface MediaFacadeRemote<T> {
	public void askQuestion(String title, String message);
	public void answerQuestion(Integer questionId, String response);
	public Collection<?> listAllQuestions();
	public QuestionJPA getQuestion(int questionId);
	public ResponseJPA getResponse(int questionId);
	public void rateProduct(String customerEmail, int productId, int rate, String comment);
	//public Collection<String> listAllProductComments();
	public void addImageToResponse(int responseId, byte[] image);
	public void acceptOrRejectResponse(int responseId, boolean isAccepted);
}
