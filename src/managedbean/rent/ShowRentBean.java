package managedbean.rent;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.catalog.CatalogFacade;
import ejb.rent.RentFacadeRemote;
import jpa.RentJPA;
import managedbean.SessionBean;
import managedbean.catalog.ListCatalogElementsBean;

@Named("showRent")
@SessionScoped
public class ShowRentBean extends SessionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	Logger logger = Logger.getLogger(ShowRentBean.class.getName());
	
	@EJB
	private RentFacadeRemote<?> rentFacadeEJB;
	protected RentJPA rentShow = null;
	private Integer id;
	
	public ShowRentBean() {
		// TODO Auto-generated constructor stub
	}
	public RentJPA getRent()
	{	
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
        rentShow = (RentJPA) rentFacadeEJB.getRent(getId());

		return rentShow;
	}
	
	public RentJPA getRentShow() {
		return rentShow;
	}
	public void setRentShow(RentJPA rentShow) {
		this.rentShow = rentShow;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
