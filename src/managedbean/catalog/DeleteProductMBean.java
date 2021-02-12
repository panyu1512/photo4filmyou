package managedbean.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.catalog.CatalogFacadeRemote;
import jpa.ItemJPA;
import jpa.ProductJPA;


@Named("deleteProductBean")
@SessionScoped
public class DeleteProductMBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private CatalogFacadeRemote<ProductJPA> catalogFacadeRemote;

	public String deleteProduct(Integer id)
	{
	
		catalogFacadeRemote.deleteProduct(id);
		
		return "listCategoryProductsView";
	}

}
