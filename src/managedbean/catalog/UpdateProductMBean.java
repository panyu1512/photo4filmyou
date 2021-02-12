package managedbean.catalog;


import domain.MoneyV2;
import ejb.catalog.CatalogFacadeRemote;
import jpa.BrandJPA;
import jpa.CategoryJPA;
import jpa.ModelJPA;
import jpa.ProductJPA;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.math.BigDecimal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Named("updateProductBean")
@SessionScoped
public class UpdateProductMBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private CatalogFacadeRemote<ProductJPA> catalogFacadeRemote;
	


	private Integer id;
	

	private String name;
	private BigDecimal dailyPrice;
	private String description;
	
	private Integer cat;
	private List<SelectItem> listSelectCategories;
	Map<Integer, CategoryJPA> listCategories;
	
	private Integer model;
	private List<SelectItem> listSelectModels;
	Map<Integer, ModelJPA> listModels;
	
	private Integer brand;
	private List<SelectItem> listSelectBrands;
	Map<Integer, BrandJPA> listBrands;
	
	@PostConstruct
	public void init() {
		listSelectCategories = new ArrayList<SelectItem>();
		List<CategoryJPA> listCategoriesTemp = (List<CategoryJPA>) catalogFacadeRemote.listAllCatalogElements();
		listCategories = new HashMap<Integer, CategoryJPA>();
		for (CategoryJPA cat: listCategoriesTemp) {
			listSelectCategories.add(new SelectItem(cat.getId(), cat.getName()));
			listCategories.put(cat.getId(), cat);
		}
		
		listSelectModels = new ArrayList<SelectItem>();
		List<ModelJPA> listModelsTemp = (List<ModelJPA>) catalogFacadeRemote.listAllModels();
		listModels = new HashMap<Integer, ModelJPA>();
		for (ModelJPA model: listModelsTemp) {
			listSelectModels.add(new SelectItem(model.getId(), model.getName()));
			listModels.put(model.getId(), model);
		}		
		
		listSelectBrands = new ArrayList<SelectItem>();
		List<BrandJPA> listBrandsTemp = (List<BrandJPA>) catalogFacadeRemote.listAllBrands();
		listBrands = new HashMap<Integer, BrandJPA>();
		for (BrandJPA brand: listBrandsTemp) {
			listSelectBrands.add(new SelectItem(brand.getId(), brand.getName()));
			listBrands.put(brand.getId(), brand);
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(BigDecimal dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getCat() {
		return cat;
	}
	public void setCat(Integer cat) {
		this.cat = cat;
	}
	
	
	public List<SelectItem> getListSelectCategories() {
		return listSelectCategories;
	}
	public void setListSelectCategories(List<SelectItem> listSelectCategories) {
		this.listSelectCategories = listSelectCategories;
	}
	public Map<Integer, CategoryJPA> getListCategories() {
		return listCategories;
	}


	public void setListCategories(Map<Integer, CategoryJPA> listCategories) {
		this.listCategories = listCategories;
	}


	public Integer getModel() {
		return model;
	}


	public void setModel(Integer model) {
		this.model = model;
	}


	public List<SelectItem> getListSelectModels() {
		return listSelectModels;
	}


	public void setListSelectModels(List<SelectItem> listSelectModels) {
		this.listSelectModels = listSelectModels;
	}


	public Map<Integer, ModelJPA> getListModels() {
		return listModels;
	}


	public void setListModels(Map<Integer, ModelJPA> listModels) {
		this.listModels = listModels;
	}


	public Integer getBrand() {
		return brand;
	}


	public void setBrand(Integer brand) {
		this.brand = brand;
	}


	public List<SelectItem> getListSelectBrands() {
		return listSelectBrands;
	}


	public void setListSelectBrands(List<SelectItem> listSelectBrands) {
		this.listSelectBrands = listSelectBrands;
	}


	public Map<Integer, BrandJPA> getListBrands() {
		return listBrands;
	}


	public void setListBrands(Map<Integer, BrandJPA> listBrands) {
		this.listBrands = listBrands;
	}


	public String update(){
		
		try
		{
		
			MoneyV2 dailyPrice = new MoneyV2(this.dailyPrice);
			CategoryJPA categoryToSend = listCategories.get(this.cat);
			ModelJPA modelToSend = listModels.get(this.model);
			BrandJPA brandToSend = listBrands.get(this.brand);
			catalogFacadeRemote.updateProduct(id, name, categoryToSend,brandToSend, modelToSend, dailyPrice, description);
			return "listAllProductsView";
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR","Por favor, revise el producto introducido"));
			return "updateProductView";
		}
	}
}
