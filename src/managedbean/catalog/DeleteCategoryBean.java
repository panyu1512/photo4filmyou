package managedbean.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.catalog.CatalogFacade;
import jpa.CategoryJPA;
import managedbean.SessionBean;

@Named("deleteCategory")
@SessionScoped
public class DeleteCategoryBean extends SessionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(DeleteCategoryBean.class.getName());

	@EJB
	private CatalogFacade<CategoryJPA> ejbCatalog;
	private int categoryId;
	
	
	public DeleteCategoryBean() {
		// TODO Auto-generated constructor stub
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public void deleteCategory(int categoryId)
	{
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		ejbCatalog.deleteCategory(categoryId);
	}

}
