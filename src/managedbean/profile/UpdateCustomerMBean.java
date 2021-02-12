package managedbean.profile;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import auth.UserLoggableInterface;
import auth.util.SessionManager;
import auth.util.Sha1;
import domain.Email;
import domain.Location;
import domain.NIF;
import domain.Password;
import domain.PhoneNumber;
import ejb.profile.ProfileFacadeRemote;
import jpa.CustomerJPA;

@Named("updateCustomer")
@SessionScoped
public class UpdateCustomerMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ProfileFacadeRemote<CustomerJPA> profileFacade;

	private String name;
	private String surname;
	private String password;
	private String location;
	private String phone;
	private String email;
	private String nif;
	private String agree;
	private Integer id;

	public String prepareView() throws IOException {
		UserLoggableInterface user = SessionManager.getUser();
		if (user != null) {
			CustomerJPA customer = (CustomerJPA) user;
			this.id = customer.getId();
			this.location = customer.getAddress().getValue();
			this.phone = customer.getPhone().getValue();
			this.name = customer.getName();
			this.surname = customer.getSurname();
			this.nif = customer.getNif().getValue();
			this.email = customer.getEmail().getValue();
		}
		return "/pages/updateCustomer?faces-redirect=true";
	}

	public String update() throws IOException {

		Email email = new Email(this.email);
		Password password = new Password(this.password, new Sha1());
		PhoneNumber phone = new PhoneNumber(this.phone);
		NIF dni = new NIF(this.nif);
		Location address = new Location(this.location);

		CustomerJPA updated = profileFacade.updateCustomerData(id, dni, name, surname, phone, address, password, email);
		if (updated != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
					"Data updated for customer with email " + email.getValue()));
			return "/pages/updateCustomer?faces-redirect=true";
		} else {
			return null;
		}

	}

	public ProfileFacadeRemote<CustomerJPA> getProfileFacade() {
		return profileFacade;
	}

	public void setProfileFacade(ProfileFacadeRemote<CustomerJPA> profileFacade) {
		this.profileFacade = profileFacade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
