package managedbean.catalog;


import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import auth.util.SessionManager;
import ejb.catalog.CatalogFacade;
import ejb.media.MediaFacadeRemote;
import ejb.profile.ProfileFacadeRemote;
import jpa.CustomerJPA;
import jpa.ProductJPA;


@Named("showProductInformationBean")
@SessionScoped
public class ShowProductInformationMBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@EJB
	private CatalogFacade<ProductJPA> catalogFacade;
	
	@EJB
	private MediaFacadeRemote<ProductJPA> mediaFacadeRemote;
	
	@EJB
	private ProfileFacadeRemote<CustomerJPA> profileFacadeRemote;
	
	private ProductJPA product;
	private int productId;
	private String comment;
	private Integer rate;

	public void onLoad() {
		this.product = this.catalogFacade.getProductInformation(productId);
	}
	
	public String registerRate() {
		String email="";
		if (SessionManager.hasUser()) {
			email = SessionManager.getUser().getEmail().getValue();
		}
		
		mediaFacadeRemote.rateProduct(email, productId, rate, comment);
		
		return "/pages/catalog?faces-redirect=true";
	}

	public ProductJPA getProduct() {
		return this.product;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProduct(ProductJPA product) {
		this.product = product;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	
	
}
