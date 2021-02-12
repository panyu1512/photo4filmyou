package managedbean.catalog;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ejb.catalog.CatalogFacade;
import jpa.CategoryJPA;
import managedbean.SessionBean;

@Named("addCategory")
@SessionScoped
public class AddCategoryBean extends SessionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CatalogFacade<CategoryJPA> ejbCatalog;
	
	private String name;
	private String parent;
	
	public AddCategoryBean() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String addCategory()
	{
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		try
		{
			ejbCatalog.addCategory(name, parent);
			return "listCatalogElements";
		}catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR","Por favor, revise la categoria introducida"));
			return "AddCategoryView";
		}
		
	}

}

