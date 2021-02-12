package managedbean.rent;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import auth.UserLoggableInterface;
import ejb.catalog.CatalogFacadeRemote;
import ejb.rent.RentFacadeRemote;
import jpa.ItemJPA;
import jpa.RentJPA;

@Named("addItemToRentBean")
@SessionScoped
public class AddItemToRentMBean implements Serializable {

	private static final long serialVersionUID = 8360539836072274778L;
	private final static Logger LOG = Logger.getLogger("managedbean.AddItemToRentMBean");

	@EJB
	private RentFacadeRemote<UserLoggableInterface> rentFacade;
	@EJB
	private CatalogFacadeRemote<UserLoggableInterface> catalogFacade;

	private Integer selectedRent;
	private Integer productId;
	private Collection<ItemJPA> availableItems;

	public String addItemToRent() {
		try {
			ItemJPA firstItem = availableItems.iterator().next();
			RentJPA rent = rentFacade.getRent(selectedRent);
			rentFacade.addItemToRent(selectedRent, firstItem.getSerialNumber());
			calculateAvailableItemsForRent(productId, rent.getFrom(), rent.getTo());	

			return "/pages/showProductInformationView.xhtml?productId=" + productId
					+ "&faces-redirect=true&dateSuccess=Producto añadido al alquiler";
		} catch (Exception e) {
			e.printStackTrace();
			return "/pages/showProductInformationView.xhtml?productId=\"+productId+\"&faces-redirect=true&dateError=Error al añadir el producto al alquiler";
		}

	}

	public void updateSelectedRent(AjaxBehaviorEvent event) {
		try {
			if(selectedRent != null) {
				RentJPA rent = rentFacade.getRent(selectedRent);
				calculateAvailableItemsForRent(productId, rent.getFrom(), rent.getTo());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void calculateAvailableItemsForRent(int productId, Date from, Date to) {
		if (selectedRent == null) {
			this.availableItems = null;
		} else {
			this.availableItems = catalogFacade.listProductAvailableItems(productId, from, to);
			for (ItemJPA item : availableItems) {
				System.out.println(item.getSerialNumber());
			}
		}
	}

	public Integer getSelectedRent() {
		return selectedRent;
	}

	public void setSelectedRent(Integer selectedRent) {
		this.selectedRent = selectedRent;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Collection<ItemJPA> getAvailableItems() {
		return availableItems;
	}

	public void setAvailableItems(Collection<ItemJPA> availableItems) {
		this.availableItems = availableItems;
	}

}
