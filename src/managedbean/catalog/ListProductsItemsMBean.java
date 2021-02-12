package managedbean.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.catalog.CatalogFacadeRemote;
import jpa.ItemJPA;
import managedbean.SessionBean;

@Named("listProductsItems")
@SessionScoped
public class ListProductsItemsMBean extends SessionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private CatalogFacadeRemote<ItemJPA> catalogFacadeRemote;
	
	public Collection<ItemJPA>getList(){
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		return this.catalogFacadeRemote.listAllProductItems();
	}
	
}

