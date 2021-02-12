package managedbean.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import managedbean.SessionBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import ejb.catalog.CatalogFacadeRemote;
import jpa.CategoryJPA;
import jpa.ModelJPA;
import jpa.ProductJPA;


@Named("listAllProducts")
@SessionScoped
public class ListAllProductsMBean extends SessionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CatalogFacadeRemote<ModelJPA> catalogFacadeRemote;
	
	private CategoryJPA category;

	public CategoryJPA getCategory() {
		return category;
	}

	public void setCategory(CategoryJPA category) {
		this.category = category;
	}
	

	public Collection<ProductJPA>getList(){
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		return catalogFacadeRemote.listAllProducts();
	}
}
