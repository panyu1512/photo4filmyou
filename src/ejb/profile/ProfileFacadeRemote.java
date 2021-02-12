package ejb.profile;

import java.util.Collection;

import javax.ejb.Remote;

import auth.UserLoggableInterface;
import domain.*;
import jpa.CustomerJPA;
import jpa.RentJPA;

/**
 * Session EJB Remote Interfaces
 */
@Remote
public interface ProfileFacadeRemote<T> {
	
	boolean registerCustomer(NIF nif, String name, String surname, PhoneNumber phone, Location address, Password password, Email email);
	CustomerJPA updateCustomerData(Integer id, NIF nif, String name, String surname, PhoneNumber phone, Location address, Password password, Email email);
	UserLoggableInterface login(Email email, Password pwd);
	void logout();
	//boolean registerAdministrator(Email email, Password pwd);
	boolean existsUserWithEmail(Class<T> className, Email email);	
	Collection<RentJPA> listMyRents(Email email);
	Collection<RentJPA> listAllActiveRents();
}
