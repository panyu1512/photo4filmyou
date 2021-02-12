package managedbean.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.catalog.CatalogFacadeRemote;
import jpa.ItemJPA;


@Named("deleteItemBean")
@SessionScoped
public class DeleteItemMBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private CatalogFacadeRemote<ItemJPA> catalogFacadeRemote;

	public String deleteItem(String serialNumber)
	{
	
		catalogFacadeRemote.deleteItem(serialNumber);
		return "ListProductsItemsView";
	}

}
