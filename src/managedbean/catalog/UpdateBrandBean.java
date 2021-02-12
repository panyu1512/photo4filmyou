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

@Named("updateBrand")
@SessionScoped
public class UpdateBrandBean extends SessionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(UpdateBrandBean.class.getName());

	@EJB
	private CatalogFacade<BrandJPA> ejbCatalog;
	private int brandId;
	private String name;
	
	
	public UpdateBrandBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String updateBrand()
	{
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		try
		{
			ejbCatalog.updateBrand(getBrandId(), getName());
			return "ListAllBrandsView";
		}catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR","Por favor, revise la marca introducida"));
			return "UpdateBrandView";
		}

	}
	
	
	
	
}
