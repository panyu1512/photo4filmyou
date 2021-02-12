package managedbean.profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import auth.UserLoggableInterface;
import auth.util.SessionManager;
import domain.RentStatus;
import domain.Roles;
import ejb.profile.ProfileFacadeRemote;
import ejb.rent.RentFacadeRemote;
import jpa.ItemJPA;
import jpa.RentJPA;

@Named("listAllRentsBean")
@SessionScoped
public class ListAllRentsMBean implements Serializable {

	private static final long serialVersionUID = 8360539836072274778L;

	@EJB
	private ProfileFacadeRemote<UserLoggableInterface> profileFacade;
	@EJB
	private RentFacadeRemote<UserLoggableInterface> rentFacade;
	
	protected Collection<RentJPA> rentList = new ArrayList<RentJPA>();
	protected Collection<RentJPA> notConfirmedRentList = new ArrayList<RentJPA>();

	public Collection<RentJPA> getAllRents() throws Exception
	{
		if(SessionManager.hasUser()) {
			if(SessionManager.getUser().getRole().equals(Roles.Administrator)) {
				listAllActiveRents();
			}else {
				listAllRentsByUser();
			}
		}
		return rentList;
	}
	
	public void onLoad() {
		if(SessionManager.hasUser()) {
			if(SessionManager.getUser().getRole().equals(Roles.Administrator)) {
				listAllActiveRents();
			}else {
				listAllRentsByUser();
			}
		}		
	}
	
	public void listAllRentsByUser()  {
		if(SessionManager.hasUser()) {
			try {
				this.notConfirmedRentList.clear();
				this.rentList =  (Collection<RentJPA>) profileFacade.listMyRents(SessionManager.getUser().getEmail());
				for(RentJPA rent : rentList) {
					if(rent.getStatus().equals(RentStatus.NotConfirmed)) {
						notConfirmedRentList.add(rent);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void listAllActiveRents()  {
		if(SessionManager.hasUser()) {
			try {				
				this.rentList =  (Collection<RentJPA>) profileFacade.listAllActiveRents();
				for(RentJPA rent : rentList) {
					if(rent.getStatus().equals(RentStatus.NotConfirmed)) {
						notConfirmedRentList.add(rent);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Collection<ItemJPA> listAllRentItems(Integer rentId) {
		return rentFacade.listAllRentItems(rentId);
	}
	

	public Collection<RentJPA> getRentList() {
		return rentList;
	}

	public void setRentList(Collection<RentJPA> rentList) {
		this.rentList = rentList;
	}

	public Collection<RentJPA> getNotConfirmedRentList() {
		return notConfirmedRentList;
	}

	public void setNotConfirmedRentList(Collection<RentJPA> notConfirmedRentList) {
		this.notConfirmedRentList = notConfirmedRentList;
	}
	
	
	
}
