package managedbean.catalog;

import ejb.catalog.CatalogFacadeRemote;
import jpa.ModelJPA;
import managedbean.SessionBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named("listAllModelsBean")
@SessionScoped
public class ListAllModelsMBean extends SessionBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private CatalogFacadeRemote<ModelJPA> catalogFacadeRemote;
	
	public Collection<ModelJPA> getList(){
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		return catalogFacadeRemote.listAllModels();
	}
}
