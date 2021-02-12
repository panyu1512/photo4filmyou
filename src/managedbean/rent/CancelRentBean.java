package managedbean.rent;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ejb.catalog.CatalogFacade;
import ejb.rent.RentFacadeRemote;
import managedbean.SessionBean;
import managedbean.catalog.DeleteCategoryBean;

@Named("cancelRent")
@SessionScoped
public class CancelRentBean extends SessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(CancelRentBean.class.getName());
	
	@EJB
	private RentFacadeRemote<?> rentFacadeEJB;
	private int id;
	private String pageFrom;
	
	public CancelRentBean () {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String cancelRent()
	{
		try
		{
			rentFacadeEJB.cancelRent(getId());
			if (pageFrom.equals("ShowrentView")) {
				return "ShowRentView";
			} else {
				return "listAllRentsView"; 
			}
		}catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR","Por favor, revise el modelo introducido"));
			return "cancelRentView";
		}

	}

	public String getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(String pageFrom) {
		this.pageFrom = pageFrom;
	}
	
	

}
