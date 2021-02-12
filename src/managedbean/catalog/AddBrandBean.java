package managedbean.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ejb.catalog.CatalogFacade;
import jpa.BrandJPA;
import managedbean.SessionBean;

@Named("addBrand")
@SessionScoped
public class AddBrandBean extends SessionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(AddBrandBean.class.getName());
	
	@EJB
	private CatalogFacade<BrandJPA> ejbCatalog;
	private String name;
	
	public AddBrandBean() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String addBrand()
	{
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		try
		{
			ejbCatalog.addBrand(getName());
			return "ListAllBrandsView";
		}catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR","Por favor, revise la marca introducida"));
			return "AddBrandView";
		}
	}

}
