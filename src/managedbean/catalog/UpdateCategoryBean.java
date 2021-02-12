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
import jpa.CategoryJPA;
import managedbean.SessionBean;

@Named("updateCategory")
@SessionScoped
public class UpdateCategoryBean extends SessionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(UpdateCategoryBean.class.getName());

	@EJB
	private CatalogFacade<CategoryJPA> ejbCatalog;
	
	private int categoryId;
	private String name;
	private String parent;
	
	
	public UpdateCategoryBean() {
		logger.info("name: " + name);
	}

	

	public int getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getParent() {
		return parent;
	}



	public void setParent(String parent) {
		this.parent = parent;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String updateCategory()
	{
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		try
		{
			ejbCatalog.updateCategory(categoryId, name, parent);
			return "listCatalogElements";
		}catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR","Por favor, revise la categoria introducida"));
			return "UpdateCategoryView";
		}

	}

}
