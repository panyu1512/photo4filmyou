package ejb.media;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import auth.util.SessionManager;
import domain.Email;
import domain.ResponseStatus;
import ejb.catalog.CatalogFacadeBean;
import jpa.CustomerJPA;
import jpa.ProductJPA;
import jpa.ProductRatingJPA;
import jpa.QuestionJPA;
import jpa.ResponseJPA;
import jpa.UserJPA;

@Stateless
public class MediaFacadeBean<T> implements MediaFacade<T>, MediaFacadeRemote<T> {

	Logger logger = Logger.getLogger(CatalogFacadeBean.class.getName());

	// Persistence Unit Context
	@PersistenceContext(unitName = "PDS2_PhotoFilm4You")
	private EntityManager entman;

	@Resource
	private SessionContext context;

	@Override
	public Collection<QuestionJPA> listAllQuestions() {
		try {
			CriteriaBuilder cb = entman.getCriteriaBuilder();
			CriteriaQuery<QuestionJPA> cq = cb.createQuery(QuestionJPA.class);
			Root<QuestionJPA> rootEntry = cq.from(QuestionJPA.class);
			CriteriaQuery<QuestionJPA> all = cq.select(rootEntry);
			TypedQuery<QuestionJPA> allQuery = entman.createQuery(all);
			return allQuery.getResultList();
		} catch (PersistenceException e) {
			logger.severe(e.getMessage());
			context.setRollbackOnly();
			return null;
		}
	}

	@Override
	public void askQuestion(String title, String message) {
		CustomerJPA askedBy = (CustomerJPA) SessionManager.getUser();
		QuestionJPA entity = new QuestionJPA(title, message, askedBy);
		try {
			this.entman.persist(entity);
		} catch (PersistenceException exception) {
			logger.severe(exception.getMessage());
			context.setRollbackOnly();
		}
	}

	@Override
	public QuestionJPA getQuestion(int questionId) {
		QuestionJPA question;
		try {
			question = entman.find(QuestionJPA.class, questionId);
			return question;

		} catch (PersistenceException e) {
			logger.severe(e.getMessage());
			context.setRollbackOnly();
			return null;
		}
	}

	@Override
	public ResponseJPA getResponse(int questionId) {
		try {
			Query query = entman.createNamedQuery("Response.findByQuestionId");
			query.setParameter("questionId", questionId);
			return (ResponseJPA) query.getSingleResult();
		} catch (PersistenceException e) {
			logger.severe(e.getMessage());
			context.setRollbackOnly();
			return null;
		}
	}

	@Override
	public void answerQuestion(Integer questionId, String responseMessage) {
		QuestionJPA question = getQuestion(questionId);
		UserJPA answeredBy = (UserJPA) SessionManager.getUser();
		ResponseJPA response = new ResponseJPA(responseMessage, ResponseStatus.Pending, answeredBy);

		try {
			response.setQuestion(question);
			question.setResponse(response);
		} catch (PersistenceException exception) {
			logger.severe(exception.getMessage());
			context.setRollbackOnly();
		}
	}

	@Override
	public void acceptOrRejectResponse(int responseId, boolean isAccepted) {
		try {
			ResponseJPA response = entman.find(ResponseJPA.class, responseId);
			if (isAccepted) {
				response.setStatus(ResponseStatus.Approved);
			} else {
				response.setStatus(ResponseStatus.Rejected);
			}
		} catch (PersistenceException e) {
			logger.severe(e.getMessage());
			context.setRollbackOnly();
		}
	}

	@Override
	public void addImageToResponse(int responseId, byte[] image) {
		try {
			ResponseJPA response = entman.find(ResponseJPA.class, responseId);
			response.setAttachedImage(image);
		} catch (PersistenceException e) {
			logger.severe(e.getMessage());
			context.setRollbackOnly();
		}
	}

	@Override
	public void rateProduct(String customerEmail, int productId, int rate, String comment) {
		Email email = new Email(customerEmail);
		
		try {
			ProductJPA product = this.entman.find(ProductJPA.class, productId);
			
			CriteriaBuilder criteriaBuilder = this.entman.getCriteriaBuilder();

			CriteriaQuery<CustomerJPA> query = criteriaBuilder.createQuery(CustomerJPA.class);
			Root<CustomerJPA> root = query.from(CustomerJPA.class);

			ParameterExpression<Email> emailParameter = criteriaBuilder.parameter(Email.class);

			query.select(root).where(criteriaBuilder.equal(root.get("email"), emailParameter));

			CustomerJPA customer = this.entman.createQuery(query).setParameter(emailParameter, email).getSingleResult();
			
			ProductRatingJPA rating = new ProductRatingJPA(comment,rate,product,customer);
			this.entman.persist(rating);
		}catch(PersistenceException e) {
			logger.severe(e.getMessage());
			context.setRollbackOnly();
		}
		
	}

}
