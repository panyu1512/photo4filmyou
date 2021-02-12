
package managedbean.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import domain.ItemStatus;
import ejb.catalog.CatalogFacadeRemote;
import jpa.CategoryJPA;
import jpa.ModelJPA;
import jpa.ProductJPA;

@Named("updateItemBean")
@SessionScoped
public class UpdateProductItemMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CatalogFacadeRemote<ModelJPA> catalogFacadeRemote;

	private String serialNumber;
	private ItemStatus status;
	
	
	private Integer product;
	private List<SelectItem> listSelectProducts;
	Map<Integer, ProductJPA> listProducts;
	
	@PostConstruct
	public void init() {
		listSelectProducts = new ArrayList<SelectItem>();
		List<ProductJPA> listProductsTemp = (List<ProductJPA>) catalogFacadeRemote.listAllProducts();
		listProducts = new HashMap<Integer, ProductJPA>();
		for (ProductJPA product: listProductsTemp) {
			listSelectProducts.add(new SelectItem(product.getId(), product.getName()));
			listProducts.put(product.getId(), product);
		}
	}	
	

	public Integer getProduct() {
		return product;
	}


	public void setProduct(Integer product) {
		this.product = product;
	}


	public List<SelectItem> getListSelectProducts() {
		return listSelectProducts;
	}


	public void setListSelectProducts(List<SelectItem> listSelectProducts) {
		this.listSelectProducts = listSelectProducts;
	}


	public Map<Integer, ProductJPA> getListProducts() {
		return listProducts;
	}


	public void setListProducts(Map<Integer, ProductJPA> listProducts) {
		this.listProducts = listProducts;
	}


	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public ItemStatus getStatus() {
		return status;
	}
	public void setStatus(ItemStatus status) {
		this.status = status;
	}
	
	public String update() {
		try
		{
			ProductJPA productToSend = listProducts.get(this.product);
			catalogFacadeRemote.updateProductItem(productToSend, serialNumber, status);
			return "listProductItemView";
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR","Por favor, revise la unidad introducida"));
			return "updateProductItemView";
		}

	}
}


