package managedbean.catalog;

import ejb.catalog.CatalogFacadeRemote;
import jpa.ModelJPA;
import managedbean.SessionBean;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named("addModelBean")
@SessionScoped
public class AddModelMBean extends SessionBean implements Serializable{


	private static final long serialVersionUID = 1L;

	@EJB
	private CatalogFacadeRemote<ModelJPA> catalogFacadeRemote;

	private String name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String save() {
		
		
        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		try
		{
			if(!catalogFacadeRemote.addModel(name)) {
				return "falta view";
			}
			return "ListAllModelsView";
		}catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR","Por favor, revise el modelo introducido"));
			return "AddModelView";
		}
	}
	
}
