package managedbean.catalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.catalog.CatalogFacade;
import jpa.BrandJPA;
import managedbean.SessionBean;

@Named("listAllBrands")
@SessionScoped
public class ListAllBrandsBean extends SessionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(ListAllBrandsBean.class.getName());
	
	@EJB
	private CatalogFacade<BrandJPA> ejbCatalog; 
	protected Collection<BrandJPA> brands = new ArrayList<BrandJPA>();
	private String name;
	
	public ListAllBrandsBean() {
	}

	public Collection<BrandJPA> getBrands() {
		return brands;
	}

	public void setBrands(Collection<BrandJPA> brands) {
		this.brands = brands;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<BrandJPA> ListBrands()
	{
		logger.info("entramos en la lista");
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

		Collection<BrandJPA> brandBBDD = (Collection<BrandJPA>) ejbCatalog.listAllBrands();
		logger.info("salimos en la lista");
		brands = new ArrayList<BrandJPA>();
		if (brandBBDD != null)
		{
			for (Iterator<BrandJPA> it = brandBBDD.iterator(); it.hasNext();) 
			{
				BrandJPA brand = (BrandJPA) it.next();
				brands.add(brand);	
			}
		}
		
		return brands;
	}

}
