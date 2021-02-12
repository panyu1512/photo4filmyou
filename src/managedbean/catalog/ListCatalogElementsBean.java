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
import jpa.CategoryJPA;
import jpa.ModelJPA;
import managedbean.SessionBean;

@Named("listCatalogElements")
@SessionScoped
public class ListCatalogElementsBean extends SessionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(ListCatalogElementsBean.class.getName());
	
	@EJB
	private CatalogFacade ejb; 
	protected Collection<CategoryJPA> categorias = new ArrayList<CategoryJPA>();
	protected CategoryJPA categoria = new CategoryJPA();
	
	public Collection<CategoryJPA> ListCatelog()
	{

        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

		Collection<CategoryJPA> categoriasBBDD = (Collection<CategoryJPA>) ejb.listCatalogElements(categoria.getName());
		categorias = new ArrayList<CategoryJPA>();
		if (categoriasBBDD != null)
		{
			for (Iterator<CategoryJPA> it = categoriasBBDD.iterator(); it.hasNext();) 
			{
				
				CategoryJPA categoria = (CategoryJPA) it.next();
				
				if (categoria.getParent() == null)
				{
					CategoryJPA parentNull = new CategoryJPA();
					parentNull.setName("Sin parent");
					categoria.setParent(parentNull);
				}
				logger.info("id: " + categoria.getId());
				logger.info("id: " + categoria.getName());
				logger.info("id: " + categoria.getParent());
				
				
				categorias.add(categoria);	
			}
		}
		return categorias;
	}

	public CategoryJPA getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoryJPA categoria) {
		this.categoria = categoria;
	}

	public Collection<CategoryJPA> getCategorias() {
		return categorias;
	}

	public void setCategorias(Collection<CategoryJPA> categorias) {
		this.categorias = categorias;
	}

	public Collection<BrandJPA> getBrands() {
		return ejb.listAllBrands();
	}

	public Collection<ModelJPA> getModels() {
		return ejb.listAllModels();
	}

 }
