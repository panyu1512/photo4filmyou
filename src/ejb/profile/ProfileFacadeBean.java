package ejb.profile;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import auth.UserLoggableInterface;
import domain.Email;
import domain.Location;
import domain.NIF;
import domain.Password;
import domain.PhoneNumber;
import jpa.AdministratorJPA;
import jpa.CustomerJPA;
import jpa.RentJPA;

@Stateless
public class ProfileFacadeBean<T> implements ProfileFacadeRemote<T>, ProfileFacade<T> {

	Logger logger = Logger.getLogger(ProfileFacadeBean.class.getName());

	// Persistence Unit Context
	@PersistenceContext(unitName = "PDS2_PhotoFilm4You")
	private EntityManager manager;

	@Resource
	private SessionContext context;

	@Override
	public boolean registerCustomer(NIF nif, String name, String surname, PhoneNumber phone, Location address,
			Password password, Email email) {
		CustomerJPA entity = new CustomerJPA(nif, name, surname, phone, address, password, email);

		logger.info("*************** Registering customer with ID: " + entity.getEmail().getValue());

		try {
			this.manager.persist(entity);
		} catch (PersistenceException exception) {
			logger.severe(exception.getMessage());
			context.setRollbackOnly();
			return false;
		}

		return true;
	}

	@Override
	public CustomerJPA updateCustomerData(Integer id, NIF nif, String name, String surname, PhoneNumber phone,
			Location address, Password password, Email email) {
		CustomerJPA entity = this.manager.find(CustomerJPA.class, id);
		entity.setName(name);
		entity.setSurname(surname);
		entity.getNif().setValue(nif.getValue());
		entity.getEmail().setValue(email.getValue());
		if (password.getValue() != null && !password.getValue().isEmpty()) {
			entity.setPassword(password);
		}
		entity.setPhone(phone);
		entity.setAddress(address);

		logger.info("*************** Updating customer with ID: " + entity.getId());

		try {
			this.manager.persist(entity);
		} catch (PersistenceException exception) {
			logger.severe(exception.getMessage());
			context.setRollbackOnly();
			return entity;
		}

		return entity;

	}

	@Override
	public UserLoggableInterface login(Email email, Password pwd) {
		UserLoggableInterface user;

		user = this.loginAsAdministrator(email, pwd);
		if (user != null) {
			return user;
		}

		return this.loginAsCustomer(email, pwd);
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	public boolean existsUserWithEmail(Class<T> className, Email email) {

		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(className);
		Root<T> root = query.from(className);
		ParameterExpression<Email> emailParameter = criteriaBuilder.parameter(Email.class);
		query.select(root).where(criteriaBuilder.equal(root.get("email"), emailParameter));
		try {
			UserLoggableInterface user = (UserLoggableInterface) this.manager.createQuery(query)
					.setParameter(emailParameter, email).getSingleResult();
			if (user != null) {
				logger.info("***** Exists user " + user.getEmail());
				return true;
			}
		} catch (PersistenceException exception) {
			logger.info(exception.getMessage());
			return false;
		}

		logger.info("***** Dont exists user ");
		return false;

	}

	private AdministratorJPA loginAsAdministrator(Email email, Password pwd) {
		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();

		CriteriaQuery<AdministratorJPA> query = criteriaBuilder.createQuery(AdministratorJPA.class);
		Root<AdministratorJPA> root = query.from(AdministratorJPA.class);

		ParameterExpression<Email> emailParameter = criteriaBuilder.parameter(Email.class);
		ParameterExpression<Password> passwordParameter = criteriaBuilder.parameter(Password.class);

		query.select(root).where(criteriaBuilder.equal(root.get("email"), emailParameter),
				criteriaBuilder.equal(root.get("password"), passwordParameter));

		try {

			return this.manager.createQuery(query).setParameter(emailParameter, email)
					.setParameter(passwordParameter, pwd).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}

	private CustomerJPA loginAsCustomer(Email email, Password pwd) {
		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();

		CriteriaQuery<CustomerJPA> query = criteriaBuilder.createQuery(CustomerJPA.class);
		Root<CustomerJPA> root = query.from(CustomerJPA.class);

		ParameterExpression<Email> emailParameter = criteriaBuilder.parameter(Email.class);
		ParameterExpression<Password> passwordParameter = criteriaBuilder.parameter(Password.class);

		query.select(root).where(criteriaBuilder.equal(root.get("email"), emailParameter),
				criteriaBuilder.equal(root.get("password"), passwordParameter));

		try {
			return this.manager.createQuery(query).setParameter(emailParameter, email)
					.setParameter(passwordParameter, pwd).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}

	@Override
	public Collection<RentJPA> listMyRents(Email email) {
		Collection<RentJPA> listData = null;
		try {
			listData = this.manager.createQuery(
					"select r from RentJPA r WHERE r.customerJPA.id = (select c.id from CustomerJPA c where email = :email)")
					.setParameter("email", email).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	@Override
	public Collection<RentJPA> listAllActiveRents() {

		Date from = new Date();
		return this.manager.createNativeQuery(
				"SELECT * from pds2_photofilm4you.rent r where status = 'Confirmed' and :date between fromdate and todate",
				RentJPA.class).setParameter("date", from, TemporalType.DATE).getResultList();
	}

}
