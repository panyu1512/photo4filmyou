package ejb.profile;

import java.util.Collection;

import javax.ejb.Local;

import auth.UserLoggableInterface;
import domain.*;
import jpa.CustomerJPA;
import jpa.RentJPA;




/**
 * Session EJB Local Interfaces
 */
@Local
public interface ProfileFacade<T> {
	
	boolean registerCustomer(NIF nif, String name, String surname, PhoneNumber phone, Location address, Password password, Email email);
	CustomerJPA updateCustomerData(Integer id, NIF nif, String name, String surname, PhoneNumber phone, Location address, Password password, Email email);
	UserLoggableInterface login(Email email, Password pwd);
	void logout();
	// boolean registerAdministrator(Email email, Password pwd);
	Collection<RentJPA> listMyRents(Email email);
	Collection<RentJPA> listAllActiveRents();
    
    
}
